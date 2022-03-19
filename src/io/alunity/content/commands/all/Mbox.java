package io.alunity.content.commands.all;

import java.util.Optional;

import io.alunity.content.commands.Command;
import io.alunity.content.item.lootable.LootableInterface;
import io.alunity.model.entity.player.Player;

/**
 * Open the mbox in the default web browser.
 * 
 * @author Alunity
 */
public class Mbox extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		LootableInterface.openInterface(c);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens the loot table interface.");
	}

}
