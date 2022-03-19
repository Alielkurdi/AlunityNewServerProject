package io.alunity.content.bosses.zulrah;

import io.alunity.model.cycleevent.CycleEvent;
import io.alunity.model.entity.player.Player;

public abstract class ZulrahStage extends CycleEvent {

	protected Zulrah zulrah;

	protected Player player;

	public ZulrahStage(Zulrah zulrah, Player player) {
		this.zulrah = zulrah;
		this.player = player;
	}

}
