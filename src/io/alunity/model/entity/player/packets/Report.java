package io.alunity.model.entity.player.packets;

import io.alunity.model.entity.player.PacketType;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class Report implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		String player = Misc.longToReportPlayerName(c.inStream.readQWord2()).replaceAll("_", " ");
		byte rule = (byte) c.inStream.readUnsignedByte();
	}

}