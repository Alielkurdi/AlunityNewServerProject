package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.range.RangeData;
import io.alunity.content.combat.specials.Special;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;

public class Ballista extends Special {

	public Ballista() {
		super(6.5, 1.25, 1.25, new int[] { 19478, 19481 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.usingBow = true;
		player.startAnimation(7222);
		if (player.playerAttackingIndex > 0 && target instanceof Player) {
			RangeData.fireProjectilePlayer(player, (Player) target, 50, 70, RangeData.getRangeProjectileGFX(player), 43, 31, 37, 10);
		} else if (player.npcAttackingIndex > 0 && target instanceof NPC) {
			RangeData.fireProjectileNpc(player, (NPC) target, 50, 70, RangeData.getRangeProjectileGFX(player), 43, 31, 37, 10);
		}
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}
}
