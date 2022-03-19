package io.alunity.content.dialogue.types;


import io.alunity.content.dialogue.DialogueAction;
import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueExpression;
import io.alunity.content.dialogue.DialogueObject;
import io.alunity.model.definitions.NpcDef;
import io.alunity.model.entity.player.Player;

public class NpcDialogue extends DialogueObject {

    /**
     * This array contains the child id where the dialogue
     * statement starts for npc and item dialogues.
     */
    private static final int[] NPC_DIALOGUE_ID = {
            4885,
            4890,
            4896,
            4903
    };

    private final int npcId;
    private final String[] text;
    private final DialogueExpression expression;

    public NpcDialogue(DialogueBuilder context, int npcId, DialogueExpression expression, String[] text) {
        super(context, true);
        this.npcId = npcId;
        this.text = text;
        this.expression = expression;
    }

    @Override
    public void send(Player player) {
        int startDialogueChildId = NPC_DIALOGUE_ID[text.length - 1];
        int headChildId = startDialogueChildId - 2;
        player.getPA().sendNpcHeadOnInterface(npcId, headChildId);
        player.getPA().sendInterfaceAnimation(headChildId, expression.getAnimation());
        player.getPA().sendString(startDialogueChildId - 1, NpcDef.forId(npcId) != null ? NpcDef.forId(npcId).getName().replaceAll("_", " ") : "");
        for (int i = 0; i < text.length; i++) {
            player.getPA().sendString(startDialogueChildId + i, text[i]);
        }
        player.getPA().sendChatboxInterface(startDialogueChildId - 3);
    }

    @Override
    public void handleAction(Player player, DialogueAction action) {
        if (action == DialogueAction.CLICK_TO_CONTINUE) {
            getContext().sendNextDialogue();
        }
    }

}
