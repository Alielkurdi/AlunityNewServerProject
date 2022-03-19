package io.alunity.content.boosts.xp;

import io.alunity.content.boosts.PlayerSkillWrapper;
import io.alunity.content.wogw.Wogw;
import io.alunity.util.Misc;

public class WogwBoost extends ExperienceBooster {
    @Override
    public String getDescription() {
        return "+50% XP Rate (" + Misc.cyclesToDottedTime((int) Wogw.EXPERIENCE_TIMER) + ")";
    }

    @Override
    public boolean applied(PlayerSkillWrapper playerSkillWrapper) {
        return Wogw.EXPERIENCE_TIMER > 0;
    }
}
