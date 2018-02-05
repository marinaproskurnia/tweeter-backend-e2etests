package com.illichso.h2DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.illichso.h2DataBase.PropertiesProvider.getQuery;

class DataBaseConnectionInitiator {

    private static final String DB_DRIVER_PLACEHOLDER = "e2etest.dbDriver";
    private static final String DB_CONNECTION_PLACEHOLDER = "e2etest.dbConnection";
    private static final String DB_USER_PLACEHOLDER = "e2etest.dbUser";
    private static final String DB_PASSWORD_PLACEHOLDER = "e2etest.dbPassword";

    static Connection getDBConnection() {
        Connection connection = null;
        try {
            String dbDriver = getQuery(DB_DRIVER_PLACEHOLDER);
            Class.forName(dbDriver);
        } catch (ClassNotFoundException exception) {
            exception.getMessage();
        }
        try {
            String dbConnection = getQuery(DB_CONNECTION_PLACEHOLDER);
            String dbUser = getQuery(DB_USER_PLACEHOLDER);
            String dbPassword = getQuery(DB_PASSWORD_PLACEHOLDER);
            connection = DriverManager.getConnection(dbConnection, dbUser,
                    dbPassword);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException exception) {
            exception.getMessage();
        }
        return connection;
    }
}
