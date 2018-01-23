package com.illichso.configuration;

import com.illichso.e2etests.configuration.E2eTestsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(E2eTestsConfiguration.class)
public class AppConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
