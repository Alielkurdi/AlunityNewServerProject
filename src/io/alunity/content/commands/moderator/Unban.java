package io.alunity.content.commands.moderator;

import io.alunity.content.commands.Command;
import io.alunity.content.commands.punishment.PunishmentCommand;
import io.alunity.model.entity.player.Player;

public class Unban extends Command {
	@Override
	public void execute(Player c, String commandName, String input) {
		new PunishmentCommand(commandName, input).parse(c);
	}

	@Override
	public String getFormat() {
		return PunishmentCommand.getFormat(getCommand());
	}
}
