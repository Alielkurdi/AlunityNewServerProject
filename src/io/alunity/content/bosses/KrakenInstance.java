package io.alunity.content.bosses;

import io.alunity.content.instances.InstanceConfiguration;
import io.alunity.content.instances.impl.LegacySoloPlayerInstance;
import io.alunity.model.entity.player.Boundary;
import io.alunity.model.entity.player.Player;

/**
 * 
 * @author Grant_ | www.rune-server.ee/members/grant_ | 12/5/19
 *
 */
public class KrakenInstance extends LegacySoloPlayerInstance {

	public KrakenInstance(Player player, Boundary boundary) {
		super(InstanceConfiguration.CLOSE_ON_EMPTY_RESPAWN, player, boundary);
	}

	@Override
	public void onDispose() { }
}
