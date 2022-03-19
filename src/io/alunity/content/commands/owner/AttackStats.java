package io.alunity.content.commands.owner;

import java.util.Optional;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class AttackStats extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        player.setPrintAttackStats(!player.isPrintAttackStats());
        player.sendMessage("Combat attack messages are now " + Misc.booleanToString(player.isPrintAttackStats()) + ".");
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Prints out combat attack stats while in combat.");
    }
}
