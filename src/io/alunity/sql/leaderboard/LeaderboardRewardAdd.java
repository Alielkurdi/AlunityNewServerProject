package io.alunity.sql.leaderboard;

import io.alunity.content.leaderboards.LeaderboardReward;
import io.alunity.content.leaderboards.LeaderboardUtils;
import io.alunity.content.leaderboards.RewardEntry;
import io.alunity.sql.DatabaseManager;
import io.alunity.sql.SqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LeaderboardRewardAdd implements SqlQuery<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(LeaderboardRewardAdd.class);

    private final List<RewardEntry> entries;

    public LeaderboardRewardAdd(List<RewardEntry> entries) {
        this.entries = entries;
    }

    @Override
    public Boolean execute(DatabaseManager context, Connection connection) throws SQLException {
        if (LeaderboardUtils.rewards.isEmpty()) {
            return false;
        }
        PreparedStatement insert = connection.prepareStatement("INSERT INTO leaderboards_collection VALUES(?, ?, ?, 0)");
        for (RewardEntry entry: entries) {
            for (LeaderboardReward reward : LeaderboardUtils.rewards) {
                if (entry.getPeriod().equals(reward.getPeriod())
                        && entry.getPlace() == reward.getPlace()
                        && entry.getEntry().getType() == reward.getType()) {
                    insert.setString(1, entry.getEntry().getLoginName());
                    insert.setInt(2, reward.getItem().getId());
                    insert.setInt(3, reward.getItem().getAmount());
                    insert.addBatch();
                    logger.debug("Adding reward {} for {}", reward, entry);
                }
            }
        }
        insert.executeBatch();
        return true;
    }
}
