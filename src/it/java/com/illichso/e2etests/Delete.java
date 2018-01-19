package com.illichso.e2etests;

import com.illichso.e2etests.configuration.E2eTestsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

//@Configuration
@ContextConfiguration(classes = {E2eTestsConfiguration.class})
public class Delete {

    @Value("${baseUri}")
    private String baseUri;

    @Autowired
    private RequestBody requestBody;

    public static void main(String[] args) {
        Delete d = new Delete();
        System.out.println("Yahoo:" + d.requestBody);
    }
}
