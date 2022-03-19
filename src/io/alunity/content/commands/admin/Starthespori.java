package io.alunity.content.commands.admin;

import io.alunity.content.commands.Command;
import io.alunity.content.worldevent.WorldEventContainer;
import io.alunity.content.worldevent.impl.HesporiWorldEvent;
import io.alunity.model.entity.player.Player;

public class Starthespori extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        WorldEventContainer.getInstance().startEvent(new HesporiWorldEvent());
        player.sendMessage("Hespori will start soon.");
    }
}
