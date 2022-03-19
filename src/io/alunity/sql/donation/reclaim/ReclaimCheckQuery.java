package io.alunity.sql.donation.reclaim;

import io.alunity.sql.DatabaseManager;
import io.alunity.sql.SqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReclaimCheckQuery implements SqlQuery<Boolean> {

    private final String oldAccountUsername;

    public ReclaimCheckQuery(String oldAccountUsername) {
        this.oldAccountUsername = oldAccountUsername;
    }

    @Override
    public Boolean execute(DatabaseManager context, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM reclaimed_donations WHERE claimed = ?");
        statement.setString(1, oldAccountUsername.toLowerCase());
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

}
