package io.alunity.content.combat.effects.damageeffect.impl.bolts;

import java.util.Optional;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.effects.damageeffect.DamageBoostingEffect;
import io.alunity.content.combat.range.RangeData;
import io.alunity.model.Items;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.HealthStatus;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;

public class EmeraldBoltSpecial implements DamageBoostingEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		RangeData.createCombatGraphic(defender, 752, true);
		defender.getHealth().proposeStatus(HealthStatus.POISON, 5, Optional.empty());
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		if (defender.getDefinition().getName() == null) {
			return;
		}
		defender.getHealth().proposeStatus(HealthStatus.POISON, 5, Optional.empty());
		RangeData.createCombatGraphic(defender, 752, true);
	}

	@Override
	public boolean isExecutable(Player operator) {
		return RangeData.boltSpecialAvailable(operator, Items.EMERALD_BOLTS_E, Items.EMERALD_DRAGON_BOLTS_E);
	}

	@Override
	public double getMaxHitBoost(Player attacker, Entity defender) {
		return 0;
	}

}
