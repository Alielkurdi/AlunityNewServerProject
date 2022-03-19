package io.alunity.content.combat.effects.damageeffect.impl;

import java.util.Optional;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.effects.damageeffect.DamageEffect;
import io.alunity.model.entity.HealthStatus;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class ToxicStaffOfTheDeadEffect implements DamageEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		defender.getHealth().proposeStatus(HealthStatus.VENOM, damage.getAmount(), Optional.of(defender));
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		defender.getHealth().proposeStatus(HealthStatus.VENOM, damage.getAmount(), Optional.of(attacker));
	}

	@Override
	public boolean isExecutable(Player operator) {
		return operator.getItems().isWearingItem(12904, Player.playerWeapon) && operator.getToxicStaffOfTheDeadCharge() > 0 && Misc.random(5) == 1;
	}

}
