package io.alunity.content.commands.test;

import io.alunity.content.combat.Hitmark;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

import java.util.Optional;

public class Hit extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        String[] args = input.split(" ");
        if (args.length == 0) {
            player.sendMessage("Usage: ::hit 98");
            return;
        }

        int damage = Integer.parseInt(args[0]);
        player.appendDamage(null, damage, Hitmark.HIT);
    }

    public Optional<String> getDescription() {
        return Optional.of("Damage your player");
    }
}
