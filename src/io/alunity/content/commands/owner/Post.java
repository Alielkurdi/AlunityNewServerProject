package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.content.tradingpost.Listing;
import io.alunity.model.entity.player.Player;

public class Post extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Listing.openPost(c, false);
	}
}
