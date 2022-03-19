package io.alunity.content.commands.punishment.impl;

import io.alunity.Server;
import io.alunity.content.commands.punishment.OnlinePlayerPCP;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.save.PlayerAddresses;
import io.alunity.punishments.PunishmentType;
import io.alunity.util.dateandtime.TimeSpan;

public class NetMute extends OnlinePlayerPCP {
    @Override
    public String name() {
        return "netmute";
    }

    @Override
    public boolean requiresDuration() {
        return true;
    }

    @Override
    public void add(Player staff, Player player, TimeSpan duration) {
        PlayerAddresses addresses = player.getValidAddresses();
        Server.getPunishments().add(PunishmentType.NET_MUTE, duration, addresses.getIp());
        if (addresses.getMac() != null)
            Server.getPunishments().add(PunishmentType.NET_MUTE, duration, addresses.getMac());
        if (addresses.getUUID() != null)
            Server.getPunishments().add(PunishmentType.NET_MUTE, duration, addresses.getUUID());
        staff.sendMessage("Muted all '{}' addresses for {}.", player.getDisplayNameFormatted(), duration);
        player.sendMessage("You've been muted by {} for {}.", staff.getDisplayNameFormatted(), duration.toString());
    }

    @Override
    public void remove(Player staff, Player player) {
        PlayerAddresses addresses = player.getValidAddresses();
        Server.getPunishments().removeWithMessage(staff, PunishmentType.NET_MUTE, addresses.getIp());
        if (addresses.getMac() != null)
            Server.getPunishments().removeWithMessage(staff, PunishmentType.NET_MUTE, addresses.getMac());
        else
            staff.sendMessage("No valid mac to unmute.");
        if (addresses.getUUID() != null && player.getUUID().length() > 0)
            Server.getPunishments().removeWithMessage(staff, PunishmentType.NET_MUTE, addresses.getUUID());
        else
            staff.sendMessage("No valid uuid to unmute.");
    }
}
