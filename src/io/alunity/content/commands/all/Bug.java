package io.alunity.content.commands.all;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.util.discord.Discord;

import java.util.Optional;

public class Bug extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (input == null) {
			c.sendMessage("Please redo your message.");
			return;
		}
		Discord.writeBugMessage(c.getDisplayName() + ": " + input);
		c.sendMessage("Your bug report has been sent to the staff.");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Report a bug");
	}
}
