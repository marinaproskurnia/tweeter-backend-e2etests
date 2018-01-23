package com.illichso.e2etests.configuration;

import com.illichso.e2etests.RequestBody;
import com.illichso.e2etests.StepsImplementation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@PropertySource(value = {"classpath:config.properties"})
public class E2eTestsConfiguration {

    @Bean
    public RequestBody requestBody() {
        return new RequestBody();
    }

    @Bean
    public StepsImplementation stepsImplementation() {
        return new StepsImplementation();
    }
}
