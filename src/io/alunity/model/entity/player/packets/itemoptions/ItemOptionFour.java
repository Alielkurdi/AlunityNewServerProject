package io.alunity.model.entity.player.packets.itemoptions;

import java.util.Objects;
import java.util.Optional;

import io.alunity.Server;
import io.alunity.content.combat.magic.SanguinestiStaff;
import io.alunity.content.items.Degrade;
import io.alunity.content.items.Degrade.DegradableItem;
import io.alunity.content.items.pouch.RunePouch;
import io.alunity.content.teleportation.TeleportTablets;
import io.alunity.model.entity.player.PacketType;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.Right;
import io.alunity.model.multiplayersession.MultiplayerSessionFinalizeType;
import io.alunity.model.multiplayersession.MultiplayerSessionStage;
import io.alunity.model.multiplayersession.MultiplayerSessionType;
import io.alunity.model.multiplayersession.duel.DuelSession;
import io.alunity.util.Misc;

/**
 * Item Click 3 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 *         Proper Streams
 */

public class ItemOptionFour implements PacketType {


	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		if (c.getMovementState().isLocked())
			return;
		c.interruptActions();
		int itemId11 = c.getInStream().readSignedWordBigEndianA();
		int itemId1 = c.getInStream().readSignedWordA();
		int itemId = c.getInStream().readSignedWordA();

		if (c.debugMessage) {
			c.sendMessage(String.format("ItemClick[item=%d, option=%d, interface=%d, slot=%d]", itemId, 4, -1, -1));
		}

		if (c.getLock().cannotClickItem(c, itemId))
			return;
		if (!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		if (c.getInterfaceEvent().isActive()) {
			c.sendMessage("Please finish what you're doing.");
			return;
		}
		if (c.getBankPin().requiresUnlock()) {
			c.getBankPin().open(2);
			return;
		}
		if (RunePouch.isRunePouch(itemId)) {
			c.getRunePouch().emptyBagToInventory();
			return;
		}
		TeleportTablets.operate(c, itemId);
		DuelSession duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
		if (Objects.nonNull(duelSession) && duelSession.getStage().getStage() > MultiplayerSessionStage.REQUEST
				&& duelSession.getStage().getStage() < MultiplayerSessionStage.FURTHER_INTERATION) {
			c.sendMessage("Your actions have declined the duel.");
			duelSession.getOther(c).sendMessage("The challenger has declined the duel.");
			duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
			return;
		}
		Optional<DegradableItem> d = DegradableItem.forId(itemId);
		if (d.isPresent()) {
			Degrade.checkPercentage(c, itemId);
			return;
		}
		if (SanguinestiStaff.clickItem(c, itemId, 4)) {
			return;
		}
		switch (itemId) {

		default:
			if (c.getRights().isOrInherits(Right.OWNER)) {
				Misc.println("[DEBUG] Item Option #4-> Item id: " + itemId);
			}
			break;
		}

	}

}
