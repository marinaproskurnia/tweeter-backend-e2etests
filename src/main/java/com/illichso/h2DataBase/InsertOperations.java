package com.illichso.h2DataBase;

import com.illichso.model.entity.Post;
import com.illichso.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.illichso.h2DataBase.DataBaseConnectionInitiator.getDBConnection;

public class InsertOperations {

    @Autowired
    private SqlQueriesProvider sqlQueriesProvider;

    public void insertRecordIntoUsersTable(User user) {
        String name = user.getName();
        long id = user.getId();
        try {
            Connection connection = getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQueriesProvider.getInsertRecordIntoUsersTableQuery());
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException exception) {
            exception.getMessage();
        }
    }

    public void insertRecordIntoPostsTable(Post post) {
        long postId = post.getId();
        String postText = post.getText();
        long userId = post.getUser().getId();
        try {
            Connection connection = getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQueriesProvider.getInsertRecordIntoPostsTableQuery());
            preparedStatement.setLong(1, postId);
            preparedStatement.setString(2, postText);
            preparedStatement.setLong(3, userId);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
