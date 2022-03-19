package io.alunity.sql;

import java.sql.Connection;

@FunctionalInterface
public interface SqlQuery<T> {

    T execute(DatabaseManager context, Connection connection) throws Exception;

}
