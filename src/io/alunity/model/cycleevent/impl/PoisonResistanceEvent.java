package io.alunity.model.cycleevent.impl;

import io.alunity.model.cycleevent.Event;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.Health;
import io.alunity.model.entity.HealthStatus;

public class PoisonResistanceEvent extends Event<Entity> {

	public PoisonResistanceEvent(Entity attachment, int ticks) {
		super("poison_resistance_event", attachment, ticks);
	}

	@Override
	public void execute() {
		if (attachment == null) {
			super.stop();
			return;
		}
		super.stop();

		Health health = attachment.getHealth();
		health.removeNonsusceptible(HealthStatus.POISON);
	}

}
