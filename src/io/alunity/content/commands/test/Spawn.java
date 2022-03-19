package io.alunity.content.commands.test;

import java.util.Optional;

import io.alunity.content.ItemSpawner;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

public class Spawn extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        ItemSpawner.open(player);
    }

    public Optional<String> getDescription() {
        return Optional.of("Opens an interface to spawn items.");
    }
}
