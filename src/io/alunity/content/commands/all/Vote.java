package io.alunity.content.commands.all;

import java.util.Optional;

import io.alunity.Configuration;
import io.alunity.content.commands.Command;
import io.alunity.content.vote_panel.VotePanelInterface;
import io.alunity.model.entity.player.Player;


public class Vote extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		VotePanelInterface.openInterface(c, true);
		c.getPA().sendFrame126(Configuration.VOTE_LINK, 12000);
		c.sendMessage("@bla@[@blu@VOTE@bla@] You may also use @red@::vpanel@bla@ to open the vote panel.");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens a web page where you can vote for rewards");
	}

}
