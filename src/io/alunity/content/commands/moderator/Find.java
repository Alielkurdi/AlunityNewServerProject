package io.alunity.content.commands.moderator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerHandler;
import io.alunity.util.Misc;

/**
 * Find all other online accounts of a given player or IP.
 * 
 * @author Emiel
 */
public class Find extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (Misc.isIPv4Address(input)) {
			System.out.println("Recognized input as IP");
			List<Player> players = PlayerHandler.stream().filter(Objects::nonNull).filter(p -> p.connectedFrom.equals(input)).collect(Collectors.toList());
			if (players.size() > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append("Players connected from ").append(input).append(": ");
				for (Player player : players) {
					sb.append(player.getDisplayName()).append(", ");
				}
				c.sendMessage(sb.substring(0, sb.length() - 2));
			} else {
				c.sendMessage("No players online with ip " + input);
			}
		} else {
			Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(input);
			if (optionalPlayer.isPresent()) {
				Player c2 = optionalPlayer.get();
				List<Player> players = PlayerHandler.stream().filter(Objects::nonNull).filter(p -> p.connectedFrom.equals(c2.connectedFrom)).collect(Collectors.toList());
				if (players.size() > 0) {
					StringBuilder sb = new StringBuilder();
					sb.append("Online accounts of ").append(input).append(": ");
					for (Player player : players) {
						sb.append(player.getDisplayName()).append(", ");
					}
					c.sendMessage(sb.substring(0, sb.length() - 2));
				} else {
					c.sendMessage("No other players online with the same ip");
				}
			} else {
				c.sendMessage(input + " is not line.");
			}

		}
	}
}
