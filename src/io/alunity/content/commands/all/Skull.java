package io.alunity.content.commands.all;

import java.util.Optional;

import io.alunity.Configuration;
import io.alunity.content.commands.Command;
import io.alunity.content.itemskeptondeath.ItemsKeptOnDeathInterface;
import io.alunity.model.entity.player.Player;

/**
 * Changes the password of the player.
 * 
 * @author Emiel
 *
 */
public class Skull extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.getPosition().inWild()) {
			c.sendMessage("You cannot use this command in the wilderness.");
			return;
		}
		c.isSkulled = true;
		c.skullTimer = Configuration.SKULL_TIMER;
		c.headIconPk = 0;
		c.getPA().requestUpdates();
		c.sendMessage("You are now skulled.");
		ItemsKeptOnDeathInterface.refreshIfOpen(c);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Puts a skull above your head..");
	}
}
