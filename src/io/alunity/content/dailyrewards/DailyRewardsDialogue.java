package io.alunity.content.dailyrewards;

import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueExpression;
import io.alunity.content.dialogue.DialogueOption;
import io.alunity.model.entity.player.Player;

public class DailyRewardsDialogue extends DialogueBuilder {

    public static final int DAILY_REWARDS_NPC = 6517;
    private static final DialogueOption[] OPTIONS = {
            new DialogueOption("What are you doing here?", DailyRewardsDialogue::whatAreYouDoingHere),
            new DialogueOption("How often can I claim these rewards?", DailyRewardsDialogue::howOftenCanIClaim),
            new DialogueOption("View daily rewards", p -> p.getDailyRewards().openInterface())
    };

    public DailyRewardsDialogue(Player player) {
        super(player);
        setNpcId(DAILY_REWARDS_NPC);
        player(DialogueExpression.LAUGH_1, "Wow, are you a wizard?!");
        npc(DialogueExpression.ANNOYED, "Some say wizard, some say merchant.", "How can I help you?");
        option(OPTIONS);
    }

    private static void howOftenCanIClaim(Player player) {
        player.start(new DialogueBuilder(player)
                .setNpcId(DAILY_REWARDS_NPC)
                .player("How often can I claim these rewards?")
                .npc("You can claim them once every day, and only", "once per person. This means you can't", "claim rewards on alts. Choose wisely!")
                .option(OPTIONS));
    }

    private static void whatAreYouDoingHere(Player player) {
        player.start(new DialogueBuilder(player)
            .setNpcId(DAILY_REWARDS_NPC)
            .player("What are you doing here?")
            .npc("I'm responsible for rationing the", "daily login rewards.", "Fancy a look?")
            .option(new DialogueOption("Sure!", p -> p.getDailyRewards().openInterface()),
                    new DialogueOption("No thanks", p -> p.getDialogueBuilder().end())));
    }
}
