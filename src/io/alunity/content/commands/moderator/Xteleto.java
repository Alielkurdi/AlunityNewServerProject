package io.alunity.content.commands.moderator;

import java.util.Optional;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerHandler;
import io.alunity.model.entity.player.Right;

/**
 * Teleport to a given player.
 * 
 * @author Emiel
 */
public class Xteleto extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(input);
		if (optionalPlayer.isPresent()) {
			Player c2 = optionalPlayer.get();
			if (!c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
				if (c2.getPosition().inClanWars() || c2.getPosition().inClanWarsSafe()) {
					c.sendMessage("@cr10@This player is currently at the pk district.");
					return;
				}
			}

			if (c.getInstance() != c2.getInstance()) {
				c.getAttributes().set("OTHER_INSTANCE", c2);
				c.getDH().sendDialogues(-500, -1);
			} else
				c.getPA().movePlayer(c2.getX(), c2.getY(), c2.heightLevel);
		} else {
			c.sendMessage(input + " is not line. You can only teleport to online players.");
		}
	}
}
