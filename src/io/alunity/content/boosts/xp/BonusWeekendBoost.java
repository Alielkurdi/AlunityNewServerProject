package io.alunity.content.boosts.xp;

import io.alunity.content.bonus.DoubleExperience;
import io.alunity.content.boosts.PlayerSkillWrapper;

public class BonusWeekendBoost extends ExperienceBooster {
    @Override
    public String getDescription() {
        return "+50% XP Bonus Weekend";
    }

    @Override
    public boolean applied(PlayerSkillWrapper playerSkillWrapper) {
        return DoubleExperience.isDoubleExperience();
    }
}
