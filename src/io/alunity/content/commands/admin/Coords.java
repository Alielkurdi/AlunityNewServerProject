package io.alunity.content.commands.admin;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

public class Coords extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.sendMessage("<col=ff0000>Coords: X="+c.absX+" Y="+c.absY+" Z="+c.heightLevel);
	}

}
