package io.alunity.content.boosts.other;

import io.alunity.content.boosts.BoostType;
import io.alunity.content.boosts.Booster;
import io.alunity.model.entity.player.Player;

public abstract class GenericBoost implements Booster<Player> {
    @Override
    public BoostType getType() {
        return BoostType.GENERIC;
    }
}
