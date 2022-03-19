package io.alunity.content.combat.effects.damageeffect.impl.bolts;

import io.alunity.Configuration;
import io.alunity.content.combat.Damage;
import io.alunity.content.combat.effects.damageeffect.DamageBoostingEffect;
import io.alunity.content.combat.range.RangeData;
import io.alunity.model.Items;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class OnyxBoltSpecial implements DamageBoostingEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		int change = Misc.random((int) (damage.getAmount() * 1.25));
		damage.setAmount(change);
		RangeData.createCombatGraphic(defender, 753, false);
		attacker.getHealth().increase(change / 4);
		attacker.getPA().refreshSkill(3);
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		if (defender.getDefinition().getName() == null) {
			return;
		}
		if (Misc.linearSearch(Configuration.UNDEAD_NPCS, defender.getNpcId()) != -1) {
			return;
		}
		RangeData.createCombatGraphic(defender, 753, false);
		attacker.getHealth().increase(damage.getAmount() / 4);
		attacker.getPA().refreshSkill(3);
	}

	@Override
	public boolean isExecutable(Player operator) {
		return RangeData.boltSpecialAvailable(operator, Items.ONYX_BOLTS_E, Items.ONYX_DRAGON_BOLTS_E);
	}

	@Override
	public double getMaxHitBoost(Player attacker, Entity defender) {
		return 0.25;
	}

}
