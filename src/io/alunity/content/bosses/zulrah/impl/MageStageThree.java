package io.alunity.content.bosses.zulrah.impl;

import io.alunity.content.bosses.zulrah.Zulrah;
import io.alunity.content.bosses.zulrah.ZulrahLocation;
import io.alunity.content.bosses.zulrah.ZulrahStage;
import io.alunity.model.CombatType;
import io.alunity.model.cycleevent.CycleEventContainer;
import io.alunity.model.entity.player.Player;

public class MageStageThree extends ZulrahStage {

	public MageStageThree(Zulrah zulrah, Player player) {
		super(zulrah, player);
	}

	@Override
	public void execute(CycleEventContainer container) {
		if (container.getOwner() == null || zulrah == null || zulrah.getNpc() == null || zulrah.getNpc().isDead() || player == null || player.isDead
				|| zulrah.getInstancedZulrah() == null) {
			container.stop();
			return;
		}
		zulrah.getNpc().setFacePlayer(true);
		if (zulrah.getNpc().totalAttacks > 5) {
			player.getZulrahEvent().changeStage(4, CombatType.RANGE, ZulrahLocation.WEST);
			zulrah.getNpc().totalAttacks = 0;
			container.stop();
			return;
		}
	}

}
