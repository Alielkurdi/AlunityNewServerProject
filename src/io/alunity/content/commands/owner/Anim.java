package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

public class Anim extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		int id = Integer.parseInt(input);
		player.startAnimation(id);
		player.sendMessage("Playing animation: " + id);
	}

}
