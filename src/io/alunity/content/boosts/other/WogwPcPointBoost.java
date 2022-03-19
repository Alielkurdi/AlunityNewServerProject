package io.alunity.content.boosts.other;

import io.alunity.content.wogw.Wogw;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class WogwPcPointBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "+5 PC Points (" + Misc.cyclesToDottedTime((int) Wogw.PC_POINTS_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Wogw.PC_POINTS_TIMER > 0;
    }
}
