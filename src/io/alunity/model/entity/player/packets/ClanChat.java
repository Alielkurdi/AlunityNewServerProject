package io.alunity.model.entity.player.packets;

import io.alunity.model.entity.player.PacketType;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

/**
 * Chat
 **/
public class ClanChat implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		String textSent = Misc.longToPlayerName2(c.getInStream().readLong());
		textSent = textSent.replaceAll("_", " ");
	}
}