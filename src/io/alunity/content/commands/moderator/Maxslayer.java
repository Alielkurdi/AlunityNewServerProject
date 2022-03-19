package io.alunity.content.commands.moderator;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerHandler;

public class Maxslayer extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Optional<Player> op = PlayerHandler.nonNullStream().filter(Objects::nonNull).max(Comparator.comparing(client -> client.getSlayer().getPoints()));
		op.ifPresent(player -> c.sendMessage("Highest slayer points: "+ player.getDisplayName() +" - "+player.getSlayer().getPoints()));
	}

}
