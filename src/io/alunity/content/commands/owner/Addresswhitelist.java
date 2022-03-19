package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.net.login.RS2LoginProtocol;

public class Addresswhitelist extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        RS2LoginProtocol.ADDRESS_WHITELIST.add(input);
        player.sendMessage("Add character to address whitelist: " + input);
    }
}
