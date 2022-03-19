package io.alunity.content.combat.specials.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.specials.Special;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;

public class DragonWarhammer extends Special {

	int pReduction;
	double npcReduction;

	public DragonWarhammer() {
		super(5.0, 1.00, 1.50, new int[] { 13576 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(1378);
		player.gfx0(1292);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {
		if (damage.getAmount() > 0) {
			pReduction = 3;
			npcReduction = .3;
		} else if (damage.getAmount() == 0) {
			pReduction = 20;
			npcReduction = .05;
		}
			if (target instanceof Player) {
				Player playerTarget = ((Player) target);
				if (playerTarget.playerLevel[1] > 0) {
					playerTarget.playerLevel[1] -= ((Player) target).playerLevel[1] / pReduction;
					playerTarget.getPA().refreshSkill(1);
				}
			} else {
				NPC npc = ((NPC) target);
				if (player.debugMessage) {
					player.sendMessage("Dragon warhammer, npc defence before: " + npc.getDefence());
				}
				npc.lowerDefence(npcReduction);
				if (player.debugMessage) {
					player.sendMessage("Dragon warhammer, npc defence after: " + npc.getDefence());
				}
			}

	}

}
