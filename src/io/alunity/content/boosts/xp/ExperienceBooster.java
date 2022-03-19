package io.alunity.content.boosts.xp;

import io.alunity.content.boosts.BoostType;
import io.alunity.content.boosts.Booster;
import io.alunity.content.boosts.PlayerSkillWrapper;

public abstract class ExperienceBooster implements Booster<PlayerSkillWrapper> {

    @Override
    public BoostType getType() {
        return BoostType.EXPERIENCE;
    }

}
