package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

/**
 * Show the current position.
 * 
 * @author Emiel
 *
 */
public class Pos extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		player.sendMessage("Current coordinates x: " + player.absX + " y:" + player.absY + " h:" + player.heightLevel);
		System.out.println("new Position(" + player.absX + ", " + player.absY + ", " + player.heightLevel + "), ");
	}
}
