package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.specials.Special;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;

public class TentacleWhip extends Special {

	public TentacleWhip() {
		super(5.0, 1.25, 1.1, new int[] { 12006 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(1658);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {
		if (target instanceof NPC) {
			((NPC) target).gfx100(341);
			((NPC) target).freezeTimer = 5;
		} else if (target instanceof Player) {
			Player t = ((Player) target);
			t.gfx100(341);
			t.freezeTimer = 5;
		}
	}

}
