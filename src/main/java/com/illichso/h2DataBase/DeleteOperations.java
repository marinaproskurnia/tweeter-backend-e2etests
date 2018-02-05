package com.illichso.h2DataBase;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static com.illichso.h2DataBase.DataBaseConnectionInitiator.getDBConnection;

public class DeleteOperations {

    @Autowired
    private SqlQueriesProvider sqlQueriesProvider;

    public void deleteFromUsersTable() {
        String query = sqlQueriesProvider.getDeleteFromUsersTableQuery();
        deleteFromDbTable(query);
    }

    public void deleteFromPostsTable() {
        String query = sqlQueriesProvider.getDeleteFromPostsTableQuery();
        deleteFromDbTable(query);
    }

    public void deleteRecordFromPostsTable(long id) {
        try {
            Connection connection = getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQueriesProvider.getDeleteRecordFromPostsTableQuery());
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException exception) {
            exception.getMessage();
        }
    }

    private void deleteFromDbTable(String deleteFromDbTableQuery) {
        try {
            Connection connection = getDBConnection();
            Statement statement = connection.createStatement();
            statement.execute(deleteFromDbTableQuery);
            connection.commit();
            connection.close();
        } catch (SQLException exception) {
            exception.getMessage();
        }
    }
}
