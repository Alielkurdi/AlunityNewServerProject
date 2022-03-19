package io.alunity.content.skills.slayer;

import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.model.AmountInput;
import io.alunity.model.Npcs;
import io.alunity.model.entity.player.Player;

public class SelectTaskLengthDialogue extends DialogueBuilder {

    private final Task task;

    public SelectTaskLengthDialogue(Player player, Task task) {
        super(player);
        this.task = task;
        setNpcId(Npcs.NIEVE);
        npc("You have been assigned " + task.getFormattedName() + ".", "It costs 30 points to cancel task in the Rewards tab!", "Or choose an easier task and lose your streak.",
                "Choose an amount to slay between 5 and 35.").exit(plr -> plr.getPA().sendEnterAmount("Enter amount of " + task.getPrimaryName() + " to slay (5-35)", getAmountInputHandler()));
    }

    private AmountInput getAmountInputHandler() {
        return (player, amount) -> {
            if (amount >= 5 && amount <= 35) {
                player.getSlayer().setAmountToSlay(amount);
            } else {
                player.start(new DialogueBuilder(player).setNpcId(Npcs.NIEVE).npc("You have to choose between 5 and 35.").exit(plr -> plr.start(new SelectTaskLengthDialogue(plr, task))));
            }
        };
    }
}
