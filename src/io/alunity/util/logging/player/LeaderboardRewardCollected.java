package io.alunity.util.logging.player;

import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;
import io.alunity.util.logging.PlayerLog;

import java.util.Set;

public class LeaderboardRewardCollected extends PlayerLog {

    private final GameItem gameItem;

    public LeaderboardRewardCollected(Player player, GameItem gameItem) {
        super(player);
        this.gameItem = gameItem;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("leaderboard_rewards");
    }

    @Override
    public String getLoggedMessage() {
        return "Collected " + gameItem;
    }
}
