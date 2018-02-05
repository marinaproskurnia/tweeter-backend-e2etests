package com.illichso.h2DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.illichso.h2DataBase.DataBaseConnectionInitiator.getDBConnection;
import static com.illichso.h2DataBase.PropertiesProvider.getApplicationProperty;
import static com.illichso.h2DataBase.PropertiesProvider.getQuery;

public class DataBaseTablesInitiator {

    private static final String DROP_USERS_TABLE_QUERY = "e2etest.dropUsersTableQuery";
    private static final String CREATE_USERS_TABLE_QUERY = "e2etest.createUsersTableQuery";
    private static final String DROP_POSTS_TABLE_QUERY = "e2etest.dropPostsTableQuery";
    private static final String CREATE_POSTS_TABLE_QUERY = "e2etest.createPostsTableQuery";
    private static final String POST_MAX_LENGTH = "post-max-length";

    public static void createDbTablesForE2eTests() {
        createUsersDbTable();
        createPostsDbTable();
    }

    private static void createUsersDbTable() {
        String dropUsersTableQuery = getQuery(DROP_USERS_TABLE_QUERY);
        String createUsersTableQuery = getQuery(CREATE_USERS_TABLE_QUERY);
        createDbTable(dropUsersTableQuery, createUsersTableQuery);
    }

    private static void createPostsDbTable() {
        String dropPostsTableQuery = getQuery(DROP_POSTS_TABLE_QUERY);
        String createPostsTableQueryBeforeReplacement = getQuery(CREATE_POSTS_TABLE_QUERY);
        String createTableQueryWithPostIdLength = getCreateTableQueryWithPostIdLength(createPostsTableQueryBeforeReplacement);
        createDbTable(dropPostsTableQuery, createTableQueryWithPostIdLength);
    }

    private static void createDbTable(String dropTableQuery, String createTableQuery) {
        try {
            Connection connection = getDBConnection();
            Statement statement = connection.createStatement();
            statement.execute(dropTableQuery);
            statement.execute(createTableQuery);
            connection.commit();
            connection.close();
        } catch (SQLException exception) {
            exception.getMessage();
        }
    }

    private static String getCreateTableQueryWithPostIdLength(String createTableQuery) {
        String postIdLength = getApplicationProperty(POST_MAX_LENGTH);
        return createTableQuery.replace("?", postIdLength);
    }
}
