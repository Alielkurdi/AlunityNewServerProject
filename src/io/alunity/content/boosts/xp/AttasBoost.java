package io.alunity.content.boosts.xp;

import io.alunity.content.boosts.PlayerSkillWrapper;
import io.alunity.content.bosses.hespori.Hespori;
import io.alunity.util.Misc;

public class AttasBoost extends ExperienceBooster {
    @Override
    public String getDescription() {
        return "+50% XP (" + Misc.cyclesToDottedTime((int) Hespori.ATTAS_TIMER) + ")";
    }

    @Override
    public boolean applied(PlayerSkillWrapper playerSkillWrapper) {
        return Hespori.ATTAS_TIMER > 0;
    }
}
