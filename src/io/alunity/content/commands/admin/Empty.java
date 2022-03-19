package io.alunity.content.commands.admin;

import io.alunity.Server;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.util.logging.player.EmptyInventoryLog;

/**
 * Empty the inventory of the player.
 * 
 * @author Emiel
 */
public class Empty extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Server.getLogging().write(new EmptyInventoryLog(c, c.getItems().getInventoryItems()));
		c.getPA().removeAllItems();
		c.sendMessage("You empty your inventory.");
	}
}
