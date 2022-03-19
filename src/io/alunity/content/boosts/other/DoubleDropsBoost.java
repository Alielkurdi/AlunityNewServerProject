package io.alunity.content.boosts.other;

import io.alunity.Configuration;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class DoubleDropsBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "+100% Drop Rate (" + Misc.cyclesToDottedTime((int) Configuration.DOUBLE_DROPS_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Configuration.DOUBLE_DROPS_TIMER > 0;
    }
}
