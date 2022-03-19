package io.alunity.content.boosts.other;

import io.alunity.content.bosses.hespori.Hespori;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class KeldaBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "x2 Larren's Keys (" + Misc.cyclesToDottedTime((int) Hespori.KELDA_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Hespori.KELDA_TIMER > 0;
    }
}
