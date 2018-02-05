package com.illichso.h2DataBase;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.illichso.h2DataBase.DataBaseConnectionInitiator.getDBConnection;

public class SelectOperations {

    @Autowired
    private SqlQueriesProvider sqlQueriesProvider;

    public long countRecordsFromPostsTable(String name, String text) {
        String query = sqlQueriesProvider.getSelectCountFromPostsTableQuery();
        return selectRecordsFromPostsTable(query, name, text);
    }

    public long selectPostIdFromPostsDbTable(String name, String text) {
        String query = sqlQueriesProvider.getSelectPostIdFromPostsTableQuery();
        return selectRecordsFromPostsTable(query, name, text);
    }

    private long selectRecordsFromPostsTable(String selectQuery, String name, String text) {
        long result = 0;
        try {
            Connection connection = getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, text);
            result = getValueFromFirstResultSetColumn(preparedStatement);
            connection.close();
            return result;
        } catch (SQLException exception) {
            exception.getMessage();
        }
        return result;
    }

    public long selectUserIdFromUsersDbTable(String name) {
        long result = 0;
        String query = sqlQueriesProvider.getSelectUserIdFromUsersTableQuery();
        try {
            Connection connection = getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            result = getValueFromFirstResultSetColumn(preparedStatement);
            connection.close();
            return result;
        } catch (SQLException exception) {
            exception.getMessage();
        }
        return result;
    }

    private long getValueFromFirstResultSetColumn(PreparedStatement preparedStatement) throws SQLException {
        long result = 0;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = resultSet.getLong(1);
        }
        return result;
    }
}
