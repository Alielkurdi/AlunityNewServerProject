package io.alunity.model.cycleevent.impl;

import io.alunity.model.cycleevent.Event;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.Health;
import io.alunity.model.entity.HealthStatus;

public class VenomResistanceEvent extends Event<Entity> {

	public VenomResistanceEvent(Entity attachment, int ticks) {
		super("venom_resistance_event", attachment, ticks);
	}

	@Override
	public void execute() {
		super.stop();
		if (attachment == null) {
			return;
		}
		Health health = attachment.getHealth();
		health.removeNonsusceptible(HealthStatus.VENOM);
	}

}
