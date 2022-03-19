package io.alunity.content.commands.helper;

import java.util.Optional;

import io.alunity.content.commands.Command;
import io.alunity.content.commands.punishment.PunishmentCommand;
import io.alunity.model.entity.player.Player;

/**
 * Forces a given player to log out.
 * 
 * @author Emiel
 */
public class CcMute extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		new PunishmentCommand(commandName, input).parse(c);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Mutes a player from the help cc.");
	}

	@Override
	public String getFormat() {
		return PunishmentCommand.getFormat(getCommand());
	}

}
