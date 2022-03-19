package io.alunity.content.commands.admin;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

/**
 * Open the banking interface.
 * 
 * @author Emiel
 */
public class Bank extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.getPA().c.itemAssistant.openUpBank();
		c.inBank = true;
	}
}
