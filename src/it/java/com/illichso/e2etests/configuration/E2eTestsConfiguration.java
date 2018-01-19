package com.illichso.e2etests.configuration;

import com.illichso.e2etests.RequestBody;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@EnableAutoConfiguration
//@ComponentScan
@PropertySource("classpath:application.properties")
public class E2eTestsConfiguration {

//    public static void main(String[] args) {
//        ApplicationContext ctx = SpringApplication.run(E2eTestsConfiguration.class, args);
//
//        System.out.println("================> " + ctx.getBean(RequestBody.class).getText());
//    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

    @Bean
    public RequestBody requestBody() {
        return new RequestBody();
    }
}
