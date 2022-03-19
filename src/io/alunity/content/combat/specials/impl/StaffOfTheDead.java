package io.alunity.content.combat.specials.impl;

import io.alunity.Server;
import io.alunity.content.combat.Damage;
import io.alunity.content.combat.specials.Special;
import io.alunity.model.cycleevent.impl.StaffOfTheDeadEvent;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.player.Player;

public class StaffOfTheDead extends Special {

	public StaffOfTheDead() {
		super(10.0, 1.0, 1.0, new int[] { 11791, 12904, 22296 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.gfx100(1228);
		player.startAnimation(7083);
		Server.getEventHandler().stop(player, "staff_of_the_dead");
		Server.getEventHandler().submit(new StaffOfTheDeadEvent(player));
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {
		if (damage.getAmount() > 1) {
			player.gfx0(1229);
			damage.setAmount(damage.getAmount() / 2);
		}
	}

}
