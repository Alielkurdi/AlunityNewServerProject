package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

/**
 * Open a specific interface.
 * 
 * @author Emiel
 *
 */
public class Interface extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		try {
			int a = Integer.parseInt(input);
			c.getPA().showInterface(a);
		} catch (Exception e) {
			c.sendMessage("::interface ####");
		}
	}
}
