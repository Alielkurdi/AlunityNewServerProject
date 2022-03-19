package io.alunity.content.displayname;

import io.alunity.Server;
import io.alunity.content.Censor;
import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueExpression;
import io.alunity.content.dialogue.DialogueOption;
import io.alunity.model.Items;
import io.alunity.model.Npcs;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerHandler;
import io.alunity.model.entity.player.lock.CompleteLock;
import io.alunity.net.login.LoginReturnCode;
import io.alunity.net.login.RS2LoginProtocol;
import io.alunity.sql.displayname.CheckDisplayNameNotTakenSqlQuery;
import io.alunity.sql.displayname.SetDisplayNameSqlQuery;
import io.alunity.sql.etc.HiscoresUpdateQuery;
import io.alunity.util.logging.player.ChangeDisplayNameLog;

import java.sql.SQLException;
import java.util.function.Consumer;

public class ChangeDisplayName {

    public static boolean clickChangeNameItem(Player player, int itemId) {
        if (itemId != Items.NAME_CHANGE_SCROLL)
            return false;
        if (Server.getConfiguration().isDisplayNamesDisabled()) {
            new DialogueBuilder(player).setNpcId(Npcs.ACCOUNT_GUIDE)
                    .npc("Display names are disabled at the moment.")
                    .send();
            return true;
        }
        start(player);
        return true;
    }

    private static void start(Player player) {
        if (!Server.getConfiguration().getServerState().isSqlEnabled()) {
            new DialogueBuilder(player).setNpcId(Npcs.ACCOUNT_GUIDE)
                    .npc("This is not available at the moment.")
            .send();
            return;
        }

        DialogueBuilder correct = new DialogueBuilder(player).setNpcId(Npcs.ACCOUNT_GUIDE)
                .npc("What would you like your new display name to be?", "@red@It's case sensitive!")
                .exit(ChangeDisplayName::enterNewDisplayName);

        DialogueBuilder incorrect = new DialogueBuilder(player).setNpcId(Npcs.ACCOUNT_GUIDE)
                .npc("Wrong! Please read the warning carefully.")
                .exit(ChangeDisplayName::start);

        new DialogueBuilder(player).setNpcId(Npcs.ACCOUNT_GUIDE)
                .npc("You've used a name change scroll.", "This will allow you to set your display name.", "@blu@You will continue to login with '@pur@" + player.getLoginName() + "@blu@'.")
                .npc("@red@WARNING@bla@: when logging in, donating, and voting", "enter your @blu@login name@bla@!")
                .npc("Now tell me, what name do you use when", "logging in, donating, and voting?")
                .option("What do you enter when logging in, donating, voting?",
                        new DialogueOption("You enter your display name!", plr -> incorrect.send()),
                        new DialogueOption("You enter your login name!", plr -> correct.send()))
                .send();
    }

    private static void enterNewDisplayName(Player plr) {
        plr.getPA().sendEnterString("Change Display Name", (ChangeDisplayName::trySetDisplayName));
    }

    private static void trySetDisplayName(Player player, String name) {
        if (isInvalid(player, name))
            return;

        Consumer<Player> change = playr -> {
            new DialogueBuilder(playr).setNpcId(Npcs.ACCOUNT_GUIDE)
                    .npc("We're attempting to set your new display name!", "Please wait.")
                    .send();

            if (player.hitDatabaseRateLimit(true)) {
                player.getPA().closeAllWindows();
                return;
            }

            Server.getDatabaseManager().exec((context, connection) -> {
                boolean available;

                try {
                    available = new CheckDisplayNameNotTakenSqlQuery(name).execute(context, connection);
                } catch (SQLException throwables) {
                    sendErrorDialogue(playr);
                    throwables.printStackTrace();
                    return null;
                }

                if (!available) {
                    playr.addQueuedAction(plr -> new DialogueBuilder(plr).setNpcId(Npcs.ACCOUNT_GUIDE)
                            .npc(DialogueExpression.SAD, "That name is already taken, try again.")
                            .exit(ChangeDisplayName::enterNewDisplayName)
                            .send());
                } else {
                    playr.addQueuedAction(plr -> setDisplayName(plr, name));
                }
                return null;
            });
        };

        new DialogueBuilder(player).option("Set display name to '@blu@" + name + "@bla@'?",
                    new DialogueOption("Yes, set display name to '@blu@" + name + "@bla@'.", change),
                    new DialogueOption("No, that's not right!", ChangeDisplayName::start)
                ).send();
    }

    private static boolean isInvalid(Player player, String name) {
        boolean valid = RS2LoginProtocol.checkUsername(name) == LoginReturnCode.SUCCESS;
        if (!valid || !RS2LoginProtocol.isValidNewName(name) || Censor.isCensoredName(name)) {
            new DialogueBuilder(player).setNpcId(Npcs.ACCOUNT_GUIDE)
                    .npc(DialogueExpression.ANGER_1, "That name is invalid, please try again.")
                    .exit(ChangeDisplayName::enterNewDisplayName)
                    .send();
            return true;
        }

        return false;
    }

    private static void setDisplayName(Player player, String name) {
        if (isInvalid(player, name))
            return;

        if (!player.getItems().playerHasItem(Items.NAME_CHANGE_SCROLL)) {
            new DialogueBuilder(player).setNpcId(Npcs.ACCOUNT_GUIDE)
                    .npc(DialogueExpression.ANGER_1, "You need a name change scroll to change your name.")
            .send();
            return;
        }

        player.lock(new CompleteLock());
        String oldDisplayName = player.getDisplayName();

        Server.getDatabaseManager().exec(((context, connection) -> {
            try {
                boolean successful = false;

                try {
                    successful = new SetDisplayNameSqlQuery(player.getLoginName(), name).execute(context, connection);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if (!successful) {
                    sendErrorDialogue(player);
                } else {
                    player.addQueuedAction(plr -> {
                        player.setDisplayName(name);
                        plr.setUpdateRequired(true);
                        plr.setAppearanceUpdateRequired(true);
                        Server.getLogging().write(new ChangeDisplayNameLog(player, oldDisplayName, player.getDisplayName()));

                        new DialogueBuilder(plr).setNpcId(Npcs.ACCOUNT_GUIDE)
                                .npc(DialogueExpression.HAPPY, "Your display name was set to '" + name + "'.", "Thanks for supporting the server!")
                                .send();
                        plr.getItems().deleteItem(Items.NAME_CHANGE_SCROLL, 1);

                        if (plr.clan != null)
                            plr.clan.updateDisplayName(player);

                        HiscoresUpdateQuery.reinsertForNewDisplayName(player, oldDisplayName);
                        PlayerHandler.nonNullStream().forEach(it -> it.getFriendsList().updateDisplayName(player, oldDisplayName));
                    });
                }
            } finally {
                player.addQueuedAction(Player::unlock);
            }
            return null;
        }));
    }

    private static void sendErrorDialogue(Player player) {
        player.addQueuedAction(plr -> {
            new DialogueBuilder(plr).setNpcId(Npcs.ACCOUNT_GUIDE)
                    .npc(DialogueExpression.SAD, "We couldn't process your name change request.", "Please try again.")
                    .exit(ChangeDisplayName::start)
                    .send();
            plr.unlock();
        });
    }
}
