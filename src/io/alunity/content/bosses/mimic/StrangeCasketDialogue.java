package io.alunity.content.bosses.mimic;

import io.alunity.model.Items;
import io.alunity.model.entity.player.Player;

public class StrangeCasketDialogue {

    public static void open(Player player) {
        if (!player.getItems().playerHasItem(Items.MIMIC)) {
            player.sendMessage("You seem to be missing The Mimic casket.");
            return;
        }
        player.getDH().sendStatement("The Mimic has... found you!", "Will you fight now?");
        player.nextChat = 2000;
    }
}
