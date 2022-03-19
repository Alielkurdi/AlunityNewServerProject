package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.Items;
import io.alunity.model.entity.player.Player;
import io.alunity.model.items.ImmutableItem;

/**
 * @author Arthur Behesnilian 1:26 PM
 */
public class Cash extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        player.getInventory().addToInventory(new ImmutableItem(Items.COINS, Integer.MAX_VALUE));
        player.sendMessage("You spawn a stack of cash.");
    }

}
