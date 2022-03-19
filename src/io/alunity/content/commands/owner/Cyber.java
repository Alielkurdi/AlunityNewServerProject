package io.alunity.content.commands.owner;

import io.alunity.Configuration;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

/**
 * Toggle the Cyber Monday sale on or off.
 * 
 * @author Emiel
 *
 */
public class Cyber extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Configuration.CYBER_MONDAY = !Configuration.CYBER_MONDAY;
		String status = Configuration.CYBER_MONDAY ? "On" : "Off";
		c.sendMessage("Cyber monday: " + status);
	}
}
