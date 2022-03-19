package io.alunity.content.dialogue.impl;

import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueExpression;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;

public class MonkChaosAltarDialogue extends DialogueBuilder {

    public MonkChaosAltarDialogue(Player player, NPC npc) {
        super(player);
        setNpcId(npc.getNpcId());
        optimistic("I'll un-note your bones for 5k each!");
        exit(player1 -> player.getPA().closeAllWindows());
    }

    private void optimistic(String...text) {
        npc(DialogueExpression.SPEAKING_CALMLY, text);
    }

}
