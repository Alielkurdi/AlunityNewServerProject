package io.alunity.content.boosts.other;

import io.alunity.content.bosses.hespori.Hespori;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class GolparBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "x2 Bonus Loot (" + Misc.cyclesToDottedTime((int) Hespori.GOLPAR_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Hespori.GOLPAR_TIMER > 0;
    }
}
