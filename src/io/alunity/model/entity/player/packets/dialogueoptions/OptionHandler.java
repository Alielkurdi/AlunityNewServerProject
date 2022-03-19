package io.alunity.model.entity.player.packets.dialogueoptions;

import io.alunity.content.bosses.mimic.MimicInstance;
import io.alunity.content.lootbag.LootingBag;
import io.alunity.model.entity.player.Player;

public class OptionHandler {

    public static void handleOptions(Player player, int option) {
        switch (player.dialogueAction) {
            case 2001:
                if (option == 1) {
                    new MimicInstance().enter(player);
                } else {
                    player.getPA().closeAllWindows();
                }
                break;
            case LootingBag.ITEM_ON_ITEM_DIALOGUE_ID:
                if (option == 4) {
                    player.getPA().sendEnterAmount(LootingBag.DEPOSIT_INTERFACE_ID);
                } else {
                    player.getLootingBag().handleClickItem(player.getLootingBag().getSelectedItem(),
                            option == 1 ? 1 : option == 2 ? 5 : option == 3 ? 10 : Integer.MAX_VALUE);
                }
                break;
            case LootingBag.OPTIONS_DIALOGUE_ID:
                player.getLootingBag().setUseAction(LootingBag.LootingBagUseAction.values()[option - 1]);
                player.getPA().closeAllWindows();
                break;

            // Select xp mode
            case 2647:
                if (option == 1) {
                    player.getDH().sendDialogues(3647, 311);
                } else if (option == 2) {
                    player.getDH().sendDialogues(5647, 311);
                } else if (option == 3) {
                    player.getDH().sendDialogues(4647, 311);
                }
                break;
        }
    }

}
