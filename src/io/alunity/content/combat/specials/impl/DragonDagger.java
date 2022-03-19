package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.core.HitDispatcher;
import io.alunity.content.combat.specials.Special;
import io.alunity.model.CombatType;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.player.Player;

public class DragonDagger extends Special {

	public DragonDagger() {
		super(2.5, 1.15, 1.15, new int[] { 1215, 1231, 5680, 5698 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.gfx100(252);
		player.startAnimation(1062);
		HitDispatcher.getHitEntity(player, target).playerHitEntity(CombatType.MELEE, SecondSpecialHit.DRAGON_DAGGER_HIT_2);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
