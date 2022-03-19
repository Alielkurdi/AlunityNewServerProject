package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.content.worldevent.WorldEventContainer;
import io.alunity.model.entity.player.Player;

public class Triggerworldevent extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        WorldEventContainer.getInstance().setTriggerImmediateEvent(true);
        player.sendMessage("Triggering next world event, please allow up to 30 seconds.");
    }
}
