package io.alunity.model.entity.player.mode.group;

import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueOption;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.mode.group.log.GimDropItemLog;
import io.alunity.model.entity.player.mode.group.log.GimWithdrawItemLog;

import java.util.List;
import java.util.stream.Collectors;

public class GroupIronmanLogsInterface {

    public static void open(Player player) {
        GroupIronmanRepository.getGroupForOnline(player).ifPresentOrElse(g -> {
            new DialogueBuilder(player).option(
                    new DialogueOption("Bank Withdraw Logs", plr -> {
                        List<String> lines = g.getWithdrawItemLog().stream().map(GimWithdrawItemLog::toString).collect(Collectors.toList());
                        plr.getPA().openQuestInterfaceNew("Bank Withdraw Logs", lines);
                    }),
                    new DialogueOption("Item Drop Logs", plr -> {
                        List<String> lines = g.getDropItemLog().stream().map(GimDropItemLog::toString).collect(Collectors.toList());
                        plr.getPA().openQuestInterfaceNew("Item Drop Logs", lines);
                    }),
                    DialogueOption.nevermind()
            ).send();
        }, () -> player.sendMessage("You're not in a Group Ironman group."));
    }

}
