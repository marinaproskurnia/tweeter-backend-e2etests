package com.illichso.e2etests.configuration;

import com.illichso.e2etests.steps.components.RequestBody;
import com.illichso.e2etests.steps.components.RequestBodyForExistingUser;
import com.illichso.e2etests.steps.implementation.AssertStepsImplementation;
import com.illichso.e2etests.steps.implementation.GeneralStepsImplementation;
import com.illichso.e2etests.steps.implementation.HttpRequestStepsImplementation;
import com.illichso.h2DataBase.DeleteOperations;
import com.illichso.h2DataBase.InsertOperations;
import com.illichso.h2DataBase.SelectOperations;
import com.illichso.h2DataBase.SqlQueriesProvider;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@PropertySource(value = {"classpath:config.properties", "classpath:application.properties"})
public class E2eTestsConfiguration {

    @Bean(name = "requestBodyForNewUser")
    public RequestBody requestBody() {
        return new RequestBody();
    }

    @Bean(name = "requestBodyForExistingUser")
    public RequestBodyForExistingUser requestBodyForExistingUser() {
        return new RequestBodyForExistingUser();
    }

    @Bean
    public InsertOperations insertOperations() {
        return new InsertOperations();
    }

    @Bean
    public DeleteOperations deleteOperations() {
        return new DeleteOperations();
    }

    @Bean
    public SelectOperations selectOperations() {
        return new SelectOperations();
    }

    @Bean
    public SqlQueriesProvider sqlQueriesProvider() {
        return new SqlQueriesProvider();
    }

    @Bean
    public HttpRequestStepsImplementation httpRequestStepsImplementation() {
        return new HttpRequestStepsImplementation();
    }

    @Bean
    public AssertStepsImplementation assertStepsImplementation() {
        return new AssertStepsImplementation();
    }

    @Bean
    public GeneralStepsImplementation stepsImplementation() {
        return new GeneralStepsImplementation();
    }
}
