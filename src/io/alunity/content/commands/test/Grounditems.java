package io.alunity.content.commands.test;

import io.alunity.Server;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

import java.util.Optional;

public class Grounditems extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
       int amount = Integer.parseInt(input);
       Server.itemHandler.createGroundItem(player, 4151, player.getX(), player.getY(), player.getHeight(), amount);
    }

    public Optional<String> getDescription() {
        return Optional.of("Add ground items under you [::grounditems 20]");
    }
}
