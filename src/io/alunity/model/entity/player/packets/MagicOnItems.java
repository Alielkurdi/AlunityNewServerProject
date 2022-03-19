package io.alunity.model.entity.player.packets;

import java.util.Objects;

import io.alunity.Server;
import io.alunity.model.entity.player.PacketType;
import io.alunity.model.entity.player.Player;
import io.alunity.model.multiplayersession.MultiplayerSessionFinalizeType;
import io.alunity.model.multiplayersession.MultiplayerSessionStage;
import io.alunity.model.multiplayersession.MultiplayerSessionType;
import io.alunity.model.multiplayersession.duel.DuelSession;

/**
 * Magic on items
 **/
public class MagicOnItems implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		if (c.getMovementState().isLocked() || c.getLock().cannotInteract(c))
			return;
		if (c.isFping()) {
			/**
			 * Cannot do action while fping
			 */
			return;
		}
		c.interruptActions();
		int slot = c.getInStream().readSignedWord();
		int itemId = c.getInStream().readSignedWordA();
		int junk = c.getInStream().readSignedWord();
		int spellId = c.getInStream().readSignedWordA();

		c.usingMagic = true;
		DuelSession duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
		if (Objects.nonNull(duelSession) && duelSession.getStage().getStage() > MultiplayerSessionStage.REQUEST
				&& duelSession.getStage().getStage() < MultiplayerSessionStage.FURTHER_INTERATION) {
			c.sendMessage("Your actions have declined the duel.");
			duelSession.getOther(c).sendMessage("The challenger has declined the duel.");
			duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
			return;
		}
		if (c.getBankPin().requiresUnlock()) {
			c.getBankPin().open(2);
			return;
		}
		if (c.getInterfaceEvent().isActive()) {
			c.sendMessage("Please finish what you're doing.");
			return;
		}
		c.getPA().magicOnItems(itemId, slot, spellId);
		c.usingMagic = false;
		c.debug(c.getLoginName() + " - Magic on item spellId: " + spellId);
	}

}
