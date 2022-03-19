package io.alunity.content.commands.owner;

import io.alunity.content.bosses.Cerberus;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

/**
 * Update the shops.
 * 
 * @author Emiel
 *
 */
public class Startcerberus extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		Cerberus.init(player);
	}
}
