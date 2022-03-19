package io.alunity.sql.eventcalendar.queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.alunity.content.event.eventcalendar.ChallengeParticipant;
import io.alunity.sql.DatabaseManager;
import io.alunity.sql.SqlQuery;
import io.alunity.sql.eventcalendar.tables.EventCalendarParticipantsTable;

public class HasPlayerAlreadyParticipatedQuery implements SqlQuery<Boolean> {

    private static final EventCalendarParticipantsTable TABLE = new EventCalendarParticipantsTable();

    private final ChallengeParticipant participant;

    public HasPlayerAlreadyParticipatedQuery(ChallengeParticipant participant) {
        this.participant = participant;
    }

    @Override
    public Boolean execute(DatabaseManager context, Connection connection) throws SQLException {
        String dayCondition = EventCalendarParticipantsTable.ENTRY_DAY + "=" + participant.getEntryDay();
        ResultSet rs = connection.createStatement().executeQuery("select * from " + TABLE.getName() + " where "
                + dayCondition +  " and " + EventCalendarParticipantsTable.USERNAME + "='" + participant.getUsername() + "'"
                + " or " + dayCondition + " and " + EventCalendarParticipantsTable.MAC_ADDRESS + "='" + participant.getMacAddress() + "'"
                + " or " + dayCondition + " and " + EventCalendarParticipantsTable.IP_ADDRESS + "='" + participant.getIpAddress() + "'");
        return rs.next();
    }

}
