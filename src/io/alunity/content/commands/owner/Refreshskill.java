package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

public class Refreshskill extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.getPA().refreshSkill(Integer.parseInt(input));
	}

}
