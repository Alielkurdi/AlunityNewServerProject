package io.alunity.content.bosses.zulrah.impl;

import io.alunity.content.bosses.zulrah.Zulrah;
import io.alunity.content.bosses.zulrah.ZulrahLocation;
import io.alunity.content.bosses.zulrah.ZulrahStage;
import io.alunity.model.CombatType;
import io.alunity.model.cycleevent.CycleEventContainer;
import io.alunity.model.entity.player.Player;

public class MeleeStageSix extends ZulrahStage {

	public MeleeStageSix(Zulrah zulrah, Player player) {
		super(zulrah, player);
	}

	@Override
	public void execute(CycleEventContainer container) {
		if (container.getOwner() == null || zulrah == null || zulrah.getNpc() == null || zulrah.getNpc().isDead() || player == null || player.isDead
				|| zulrah.getInstancedZulrah() == null) {
			container.stop();
			return;
		}
		if (zulrah.getNpc().totalAttacks > 1 && zulrah.getNpc().attackTimer == 9) {
			player.getZulrahEvent().changeStage(7, CombatType.RANGE, ZulrahLocation.EAST);
			zulrah.getNpc().totalAttacks = 0;
			zulrah.getNpc().setFacePlayer(true);
			container.stop();
			return;
		}
	}

}
