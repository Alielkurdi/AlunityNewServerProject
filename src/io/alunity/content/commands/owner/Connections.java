package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.net.ChannelHandler;

public class Connections extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
       player.sendMessage("There are currently {} active connections.", "" + ChannelHandler.getActiveConnections());
    }
}
