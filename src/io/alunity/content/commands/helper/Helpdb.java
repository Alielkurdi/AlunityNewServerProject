package io.alunity.content.commands.helper;

import io.alunity.content.commands.Command;
import io.alunity.content.help.HelpDatabase;
import io.alunity.model.entity.player.Player;

/**
 * Opens an interface containing all help tickets.
 * 
 * @author Emiel
 */
public class Helpdb extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		HelpDatabase.getDatabase().openDatabase(c);
	}
}
