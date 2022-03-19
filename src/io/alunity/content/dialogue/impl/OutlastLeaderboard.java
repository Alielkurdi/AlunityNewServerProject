package io.alunity.content.dialogue.impl;

import io.alunity.Server;
import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueOption;
import io.alunity.content.tournaments.OutlastLeaderboardType;
import io.alunity.content.tournaments.OutlastRecentWinner;
import io.alunity.content.tournaments.OutlastLeaderboardEntry;
import io.alunity.model.entity.player.Player;
import io.alunity.sql.outlast.OutlastLeaderboardGetTop;
import io.alunity.sql.outlast.OutlastRecentWinnersGetRecent;
import io.alunity.util.Misc;

import java.util.List;
import java.util.stream.Collectors;

public class OutlastLeaderboard {

    private static final int LEADER_BOARD_OBJECT_ID = 7127;
    private static final int RECENT_WINNER_OBJECT_ID = 27780;
    private static final int LEADERBOARD_DISPLAY_LIMIT = 10;

    public static boolean handleInteraction(Player player, int objectId, int option) {
        if (objectId == LEADER_BOARD_OBJECT_ID) {
            if (option == 1) {
                DialogueOption[] options = new DialogueOption[OutlastLeaderboardType.values().length];
                for (int i = 0; i < options.length; i++) {
                    OutlastLeaderboardType it = OutlastLeaderboardType.values()[i];
                    options[i] = new DialogueOption(it.getDisplayName(), plr -> openLeaderboard(plr, it));
                }

                new DialogueBuilder(player).option(options).send();
            } else {
                if (option == 2)
                    openLeaderboard(player, OutlastLeaderboardType.WINS);
                if (option == 3)
                    openLeaderboard(player, OutlastLeaderboardType.KILLS);
                if (option == 4)
                    openLeaderboard(player, OutlastLeaderboardType.KDR);
            }

            return true;
        } else if (objectId == RECENT_WINNER_OBJECT_ID) {
            openRecentWinners(player);
            return true;
        }

        return false;
    }

    public static void openLeaderboard(Player player, OutlastLeaderboardType type) {
        if (player.hitDatabaseRateLimit(true))
            return;

        Server.getDatabaseManager().exec((context, connection) -> {
            List<OutlastLeaderboardEntry> entries = new OutlastLeaderboardGetTop(type, LEADERBOARD_DISPLAY_LIMIT)
                    .execute(context, connection);
            List<String> text = entries.stream().map(it -> it.format(type)).collect(Collectors.toList());
            List<String> numberedText = Misc.toNumberedText(text);
            player.addQueuedAction(plr -> player.getPA().openQuestInterface(type.getDisplayName(), numberedText));
            return null;
        });
    }

    public static void openRecentWinners(Player player) {
        if (player.hitDatabaseRateLimit(true))
            return;

        Server.getDatabaseManager().exec((context, connection) -> {
            List<OutlastRecentWinner> recentWinners = new OutlastRecentWinnersGetRecent(LEADERBOARD_DISPLAY_LIMIT).execute(context, connection);
            List<String> text = recentWinners.stream().map(OutlastRecentWinner::format).collect(Collectors.toList());
            List<String> numberedText = Misc.toNumberedText(text);
            player.addQueuedAction(plr -> player.getPA().openQuestInterface("Recent Winners", numberedText));
            return null;
        });
    }

}
