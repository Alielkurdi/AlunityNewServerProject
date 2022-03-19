package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.specials.Special;
import io.alunity.model.Items;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.player.Player;

public class GraniteMaulSpecialAttack extends Special {

	public GraniteMaulSpecialAttack() {
		super(5.0, 1, 1, new int[] {Items.GRANITE_MAUL, Items.GRANITE_MAUL_OR });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {

	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
