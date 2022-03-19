package io.alunity.content.commands.all;

import io.alunity.content.commands.Command;
import io.alunity.content.wildwarning.WildWarning;
import io.alunity.model.entity.player.Player;

import java.util.Optional;

/**
 * Show the current position.
 * 
 * @author Noah
 *
 */
public class Wild extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		WildWarning.sendWildWarning(player, null);
	}
	@Override
	public Optional<String> getDescription() {
		return Optional.of("Open the wild warning interface.");
	}
}
