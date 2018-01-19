package com.illichso;

import com.illichso.configuration.AppConfiguration;
import com.illichso.h2DataBase.DataBaseHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@PropertySource(value = {"classpath:application.properties"})
@Import(AppConfiguration.class)
public class Application {

    public static void main(String[] args) {
        run(Application.class, args);
        DataBaseHandler.createPostsTable();
        DataBaseHandler.createUsersTable();
    }
}
