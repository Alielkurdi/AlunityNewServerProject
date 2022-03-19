package io.alunity.util.logging.player;

import io.alunity.content.achievement.Achievements;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;
import io.alunity.util.logging.PlayerLog;

import java.util.Set;

public class ClaimAchievementLog extends PlayerLog {

    private final Achievements.Achievement achievement;

    public ClaimAchievementLog(Player player, Achievements.Achievement achievement) {
        super(player);
        this.achievement = achievement;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("claimed_achievement");
    }

    @Override
    public String getLoggedMessage() {
        return Misc.replaceBracketsWithArguments("Claimed achievement {}", achievement);
    }
}
