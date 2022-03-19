package io.alunity.util.logging.player;

import java.util.List;
import java.util.Set;

import io.alunity.content.referral.ReferralSource;
import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;
import io.alunity.util.logging.PlayerLog;

public class ReferralLog extends PlayerLog {

    private final ReferralSource source;
    private final String qualifier;
    private final List<GameItem> rewards;

    public ReferralLog(Player player, ReferralSource source, String qualifier, List<GameItem> rewards) {
        super(player);
        this.source = source;
        this.qualifier = qualifier;
        this.rewards = rewards;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("referrals");
    }

    @Override
    public String getLoggedMessage() {
        return "Received referral rewards: " + source + ", " + qualifier + ", " + rewards;
    }
}
