package io.alunity.content.bosses.mimic;

import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueOption;
import io.alunity.model.entity.player.Player;

public class MimicCasket {

    public static void open(Player player) {
//        if (Boundary.isIn(player, Boundary.MIMIC_LAIR)) {
//            player.sendMessage("It'd be bad taste to try opening that in here.");
//            return;
//        }

        player.start(new DialogueBuilder(player)
            .statement("The box won't budge, you have to unlock it by defeating The Mimic!", "Would you like to teleport to The Mimic Chest?")
            .option(
                    new DialogueOption("Yes, teleport to the Mimic Chest.", plr -> plr.getPA().spellTeleport(1645, 3570, 1, false)),
                    new DialogueOption("No, stay here.", plr -> plr.getPA().closeAllWindows())
            )
        );
    }
}
