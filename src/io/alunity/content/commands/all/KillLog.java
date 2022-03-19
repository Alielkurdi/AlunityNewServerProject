package io.alunity.content.commands.all;

import java.util.Optional;

import io.alunity.content.combat.stats.MonsterKillLog;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

public class KillLog extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        MonsterKillLog.openInterface(player);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Opens the kill log.");
    }

}
