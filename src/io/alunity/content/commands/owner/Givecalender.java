package io.alunity.content.commands.owner;

import java.util.Optional;

import io.alunity.content.commands.Command;
import io.alunity.content.event.eventcalendar.EventChallenge;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerHandler;

/**
 * Show the current position.
 * 
 * @author Emiel
 *
 */
public class Givecalender extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(input);
		if (optionalPlayer.isPresent()) {
			Player c2 = optionalPlayer.get();
			c.sendMessage("You have entered @red@ "+ c2.getDisplayName() +" into the calender entry.");
			c2.sendMessage("You have been given 1 raid count.");
            c2.getEventCalendar().progress(EventChallenge.COMPLETE_X_RAIDS, 1);
		} else {
			c.sendMessage("user is offline. You can only teleport online players.");
		}
	}
}