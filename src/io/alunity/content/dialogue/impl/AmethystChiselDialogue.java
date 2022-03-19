package io.alunity.content.dialogue.impl;

import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueOption;
import io.alunity.model.entity.player.Player;

public class AmethystChiselDialogue extends DialogueBuilder {


    public AmethystChiselDialogue(Player player) {
        super(player);
        setNpcId(5810);
                    npc("Please select which Amethyst chisel", "method you would like to do.")
                    .option(new DialogueOption("Bolt Tips.", p -> amethystBoltMethod(player)),
                            new DialogueOption("Arrow Tips.", p -> amethystArrowMethod(player)),
                            new DialogueOption("Javelin Heads", p -> amethystJavelinMethod(player)

                            ));

    }

    public void amethystBoltMethod(Player player ) {
        player.boltTips = true;
        player.arrowTips = false;
        player.javelinHeads = false;
        player.sendMessage("Your Amethyst method is now bolt tips!");
        player.getPA().closeAllWindows();
        return;
    }
    public void amethystArrowMethod(Player player ) {
        player.boltTips = false;
        player.arrowTips = true;
        player.javelinHeads = false;
        player.sendMessage("Your Amethyst method is now arrow tips!");
        player.getPA().closeAllWindows();
        return;
    }
    public void amethystJavelinMethod(Player player ) {
        player.boltTips = false;
        player.arrowTips = false;
        player.javelinHeads = true;
        player.sendMessage("Your Amethyst method is now javelin heads!");
        player.getPA().closeAllWindows();
        return;
    }

}
