package io.alunity.content.commands.owner;

import io.alunity.Server;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.punishments.PunishmentType;

public class ManualUnNetMute extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        Server.getPunishments().removeWithMessage(player, PunishmentType.NET_MUTE, input);
    }
}
