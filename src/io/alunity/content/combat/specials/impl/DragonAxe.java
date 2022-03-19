package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.specials.Special;
import io.alunity.content.skills.Skill;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.player.Player;

public class DragonAxe extends Special {
	public DragonAxe() {
		super(10.0, 1.0, 1.0, new int[] { 6739 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.forcedChat("Chop chop!");
		player.gfx0(479);
		player.startAnimation(2876);
		player.playerLevel[Skill.WOODCUTTING.getId()] = player.getLevelForXP(player.playerXP[Skill.WOODCUTTING.getId()]) + 3;
		player.getPA().refreshSkill(Skill.WOODCUTTING.getId());
	}


	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}
}
