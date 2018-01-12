package com.illichso.h2DataBase;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataBaseHandler {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE";
    //"jdbc:h2:~/test";
    // ("jdbc:h2:/data/test;AUTO_SERVER=TRUE");
    //"jdbc:h2:tcp://localhost/~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static void createPostsTable() {
        String dropTableQuery = "DROP TABLE if exists posts";
        String createTableQuery = "CREATE TABLE posts ( POST_ID varchar(50), POST_TEXT varchar(50))";
        Connection connection = getDBConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(dropTableQuery);
            statement.execute(createTableQuery);
            connection.commit();
            connection.close();
//            statement.close();
//            connection.close();
//            PreparedStatement dropPreparedStatement = connection.prepareStatement(dropTableQuery);
//            PreparedStatement createPreparedStatement = connection.prepareStatement(createTableQuery);
//            createPreparedStatement.executeUpdate();
//            createPreparedStatement.close();

//            Class.forName(DB_DRIVER);
//            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "test", "");
//            statement = connection.createStatement();
//            statement.executeUpdate("DROP TABLE posts");
//            statement.executeUpdate("CREATE TABLE posts ( POST_ID varchar(50), POST_TEXT varchar(50))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertIntoPostsTable(long id, String text) {
        String insertTableQuery = "INSERT INTO posts(POST_ID, POST_TEXT) VALUES ('"
                + id
                + "','"
                + text
                + "')";
        Connection connection = getDBConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(insertTableQuery);
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteByPostIdFromPostsTable(long id) {
        String deleteTableQuery = "DELETE FROM posts WHERE POST_ID = " + "'" + id + "'";
        Connection connection = getDBConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(deleteTableQuery);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void selectFromPostsTable() {
        String selectTableQuery = "select * from posts";
        Connection connection = getDBConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectTableQuery);
            while (resultSet.next()) {
                System.out.println("POST_ID " + " POST_TEXT" + "\n" + resultSet.getInt("POST_ID") + "   " + resultSet.getString("POST_TEXT"));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createUsersTable() {
        String dropTableQuery = "DROP TABLE if exists USERS";
        String createTableQuery = "CREATE TABLE USERS ( USER_ID varchar(50), USER_TEXT varchar(50))";
        Connection connection = getDBConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(dropTableQuery);
            statement.execute(createTableQuery);
            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
