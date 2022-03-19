package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.net.login.LoginThrottler;

public class Clearloginattempts extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		LoginThrottler.clear();
		c.sendMessage("Cleared all login attempts.");
	}

}
