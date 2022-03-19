package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.content.itemskeptondeath.ItemsKeptOnDeathInterface;
import io.alunity.model.entity.player.Player;

import java.util.Optional;

/**
 * Removes the skull from a player
 *
 * @author Arthur Behesnilian 12:53 PM
 */
public class RemoveSkull extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {

        player.isSkulled = false;
        player.skullTimer = 0;
        player.headIconPk = -1;
        player.getPA().requestUpdates();
        player.sendMessage("You are now un-skulled.");
        ItemsKeptOnDeathInterface.refreshIfOpen(player);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Removes skull above your head..");
    }
}
