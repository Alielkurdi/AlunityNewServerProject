package io.alunity.content.bosses.zulrah.impl;

import io.alunity.content.bosses.zulrah.Zulrah;
import io.alunity.content.bosses.zulrah.ZulrahLocation;
import io.alunity.content.bosses.zulrah.ZulrahStage;
import io.alunity.model.CombatType;
import io.alunity.model.cycleevent.CycleEventContainer;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.npc.NPCSpawning;
import io.alunity.model.entity.player.Player;

public class SpawnZulrahStageZero extends ZulrahStage {

	public SpawnZulrahStageZero(Zulrah zulrah, Player player) {
		super(zulrah, player);
	}

	@Override
	public void execute(CycleEventContainer container) {
		if (container.getOwner() == null || zulrah == null || player == null || player.isDead || zulrah.getInstancedZulrah() == null) {
			container.stop();
			player.getZulrahEvent().setStarting(false);
			return;
		}
		int cycle = container.getTotalTicks();
		int start = 2;
		if (cycle == start) {
			player.getPA().sendScreenFade("", -1, 4);
			player.getPA().movePlayer(2268, 3069, zulrah.getInstancedZulrah().getHeight());
			zulrah.getInstancedZulrah().add(player);
		}
		if (cycle == start + 1) {
			player.getZulrahEvent().setStarting(false);
		}
		if (cycle == start + 5) {
			NPC npc = NPCSpawning.spawnNpc(player, 2042, 2266, 3072, zulrah.getInstancedZulrah().getHeight(), -1, 41, false, false);
			zulrah.getInstancedZulrah().add(npc);
			if (npc == null) {
				player.sendMessage("Something went wrong, please contact staff.");
				container.stop();
				return;
			}
			zulrah.setNpc(npc);
			npc.setFacePlayer(false);
			npc.facePlayer(player.getIndex());
			npc.startAnimation(5073);
		}
		if (cycle == start + 10) {
			zulrah.changeStage(1, CombatType.RANGE, ZulrahLocation.NORTH);
			container.stop();
		}
	}

}
