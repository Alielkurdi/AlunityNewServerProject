package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.core.HitDispatcher;
import io.alunity.content.combat.range.RangeData;
import io.alunity.content.combat.specials.Special;
import io.alunity.model.CombatType;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;

public class MagicShortBow extends Special {

	public MagicShortBow() {
		super(6.0, 1.43, 1.00, new int[] { 859, 861 });
	}

	public MagicShortBow(double cost) {
		super(cost, 1.15, 1.05, new int[] { 12788 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.bowSpecShot = 1;
		player.getItems().deleteArrow();
		player.getItems().deleteArrow();
		player.startAnimation(1074);
		if (target instanceof NPC && player.npcAttackingIndex > 0) {
			RangeData.fireProjectileNpc(player, (NPC) target, 50, 70, 249, 43, 31, 37, 10);
			RangeData.fireProjectileNpc(player, (NPC) target, 50, 70, 249, 43, 31, 53, 10);
			HitDispatcher.getHitEntity(player, target).playerHitEntity(CombatType.RANGE, SecondSpecialHit.MAGIC_SHORTBOW_HIT_2);
		} else if (target instanceof Player && player.playerAttackingIndex > 0) {
			RangeData.fireProjectilePlayer(player, (Player) target, 50, 70, 249, 43, 31, 37, 10);
			RangeData.fireProjectilePlayer(player, (Player) target, 50, 70, 249, 43, 31, 53, 10);
			HitDispatcher.getHitEntity(player, target).playerHitEntity(CombatType.RANGE, SecondSpecialHit.MAGIC_SHORTBOW_HIT_2);
		}
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
