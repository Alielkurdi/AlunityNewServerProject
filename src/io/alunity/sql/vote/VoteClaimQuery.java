package io.alunity.sql.vote;

import io.alunity.Server;
import io.alunity.model.entity.player.Player;
import io.alunity.sql.DatabaseManager;
import io.alunity.sql.SqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VoteClaimQuery implements SqlQuery<List<VoteRecord>> {

    private final Player player;
    private final LocalDateTime now = LocalDateTime.now();

    public VoteClaimQuery(Player player) {
        this.player = player;
    }

    @Override
    public List<VoteRecord> execute(DatabaseManager context, Connection connection) throws Exception {
        List<VoteRecord> voteRecords = new ArrayList<>();

        PreparedStatement select = connection.prepareStatement(
                "SELECT * FROM votes WHERE username = ? AND claimed = 0 AND voted_on != -1",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        select.setString(1, player.getLoginNameLower());
        ResultSet rs = select.executeQuery();

        while (rs.next()) {
            rs.updateInt("claimed", 1);
            rs.updateRow();

            int siteId = rs.getInt("site_id");
            var timestamp = rs.getInt("voted_on");

            voteRecords.add(new VoteRecord(siteId, new Timestamp(timestamp), false));
        }

        // Check if they've voted within 12 hours on this site already
        Server.getDatabaseManager().executeImmediate((context1, connection1) -> {
            for (VoteRecord voteRecord : voteRecords) {
                Boolean claimed = new VoteThrottlerCheckQuery(player, voteRecord.getSiteId()).execute(context1, connection1);
                if (claimed != null && claimed) {
                    voteRecord.setThrottled(true);
                }
            }

            return null;
        });

        // Throttle all votes on the same site after one
        HashSet<Integer> voteSites = new HashSet<>();
        for (VoteRecord voteRecord : voteRecords) {
            if (voteRecord.isThrottled())
                continue;
            if (voteSites.contains(voteRecord.getSiteId())) {
                voteRecord.setThrottled(true);
                continue;
            }
            voteSites.add(voteRecord.getSiteId());
        }

        return voteRecords;
    }

}
