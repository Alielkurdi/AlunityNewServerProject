package io.alunity.content.commands.all;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.util.discord.Discord;

import java.util.Optional;

public class Suggestion extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (input == null) {
			c.sendMessage("Please redo your message.");
			return;
		}
		Discord.writeSuggestionMessage(c.getDisplayName() + ": " + input);
		c.sendMessage("Your suggestion has been sent to the staff.");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Suggestion that we add or change something");
	}
}
