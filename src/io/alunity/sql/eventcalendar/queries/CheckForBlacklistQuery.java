package io.alunity.sql.eventcalendar.queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.alunity.content.event.eventcalendar.ChallengeParticipant;
import io.alunity.sql.DatabaseManager;
import io.alunity.sql.DatabaseTable;
import io.alunity.sql.SqlQuery;
import io.alunity.sql.eventcalendar.tables.EventCalendarBlacklistTable;

public class CheckForBlacklistQuery implements SqlQuery<Boolean> {

    private static final DatabaseTable TABLE = new EventCalendarBlacklistTable();

    private final ChallengeParticipant participant;

    public CheckForBlacklistQuery(ChallengeParticipant participant) {
        this.participant = participant;
    }

    @Override
    public Boolean execute(DatabaseManager context, Connection connection) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from " + TABLE.getName()
             + " where " + EventCalendarBlacklistTable.IP_ADDRESS + "='" + participant.getIpAddress() + "' "
             + " or " + EventCalendarBlacklistTable.MAC_ADDRESS + "='" + participant.getMacAddress() + "'");
        return rs.next();
    }
}
