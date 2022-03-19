package io.alunity.content.commands.all;

import java.util.Optional;

import io.alunity.Configuration;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

/**
 * Opens the highscores in the default web browser.
 * 
 * @author Emiel
 */
public class Highscores extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.getPA().sendString(Configuration.HISCORES_LINK, 12000);

	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens a webpage with the highscores");
	}

}
