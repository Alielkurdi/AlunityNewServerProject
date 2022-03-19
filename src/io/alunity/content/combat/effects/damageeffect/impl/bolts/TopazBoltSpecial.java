package io.alunity.content.combat.effects.damageeffect.impl.bolts;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.effects.damageeffect.DamageBoostingEffect;
import io.alunity.content.combat.range.RangeData;
import io.alunity.model.Items;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class TopazBoltSpecial implements DamageBoostingEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		int change = Misc.random(damage.getAmount());
		damage.setAmount(change);
		RangeData.createCombatGraphic(defender, 757, false);

		if (attacker.playerAttackingIndex > 0) {
			defender.playerLevel[6] -= 2;
			defender.getPA().refreshSkill(6);
			defender.sendMessage("Your magic has been lowered!");
		}
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		if (defender.getDefinition().getName() == null) {
			return;
		}
		RangeData.createCombatGraphic(defender, 757, false);
	}

	@Override
	public boolean isExecutable(Player operator) {
		return RangeData.boltSpecialAvailable(operator, Items.TOPAZ_BOLTS_E, Items.TOPAZ_DRAGON_BOLTS_E);
	}

	@Override
	public double getMaxHitBoost(Player attacker, Entity defender) {
		return 0;
	}

}