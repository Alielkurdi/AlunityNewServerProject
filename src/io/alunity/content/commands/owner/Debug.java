package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

/**
 * Show the current position.
 * 
 * @author Emiel
 *
 */
public class Debug extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		if (player.debugMessage) {
			player.debugMessage = false;
			player.sendMessage("Debug Messages Disabled.");
		} else {
			player.debugMessage = true;
			player.sendMessage("Debug Messages Enabled.");
		}
		
	}
}
