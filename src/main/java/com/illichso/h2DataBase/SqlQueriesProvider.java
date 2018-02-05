package com.illichso.h2DataBase;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class SqlQueriesProvider {

    @Value("${e2etest.insertRecordIntoUsersTableQuery}")
    private String insertRecordIntoUsersTableQuery;

    @Value("${e2etest.insertRecordIntoPostsTableQuery}")
    private String insertRecordIntoPostsTableQuery;

    @Value("${e2etest.deleteRecordFromPostsTableQuery}")
    private String deleteRecordFromPostsTableQuery;

    @Value("${e2etest.selectCountFromPostsTableQuery}")
    private String selectCountFromPostsTableQuery;

    @Value("${e2etest.selectPostIdFromPostsTableQuery}")
    private String selectPostIdFromPostsTableQuery;

    @Value("${e2etest.selectUserIdFromUsersTableQuery}")
    private String selectUserIdFromUsersTableQuery;

    @Value("${e2etest.deleteFromUsersTableQuery}")
    private String deleteFromUsersTableQuery;

    @Value("${e2etest.deleteFromPostsTableQuery}")
    private String deleteFromPostsTableQuery;
}
