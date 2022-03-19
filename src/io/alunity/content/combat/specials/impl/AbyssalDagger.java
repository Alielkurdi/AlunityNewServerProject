package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.core.HitDispatcher;
import io.alunity.content.combat.specials.Special;
import io.alunity.model.CombatType;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;

public class AbyssalDagger extends Special {

	public AbyssalDagger() {
		super(5.0, 1.25, 0.85, new int[] { 13265, 13267, 13269, 13271 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(3300);
		player.gfx0(1283);
		if (target instanceof NPC) {
			HitDispatcher.getHitEntity(player, target).playerHitEntity(CombatType.MELEE, SecondSpecialHit.ABYSSAL_DAGGER_HIT_2);
		} else if (target instanceof Player) {
			HitDispatcher.getHitEntity(player, target).playerHitEntity(CombatType.MELEE, SecondSpecialHit.ABYSSAL_DAGGER_HIT_2);
		}
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
