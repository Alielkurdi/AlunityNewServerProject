package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.Hitmark;
import io.alunity.content.combat.formula.MagicMaxHit;
import io.alunity.content.combat.specials.Special;
import io.alunity.content.skills.Skill;
import io.alunity.model.CombatType;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerAssistant;
import io.alunity.util.Misc;

public class SaradominSword extends Special {

	public SaradominSword() {
		super(10.0, 1.0, 1.1, new int[] { 11838 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(1132);
		if (damage.getAmount() > 0) {
			int damage2 = MagicMaxHit.magiMaxHit(player) + (1 + Misc.random(15));
			player.getDamageQueue().add(new Damage(target, damage2, 2, player.playerEquipment, Hitmark.HIT, CombatType.MAGE));
			player.getPA().addXpDrop(new PlayerAssistant.XpDrop(damage2, Skill.ATTACK.getId()));
		}
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
