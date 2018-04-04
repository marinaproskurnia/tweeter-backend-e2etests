package com.illichso.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CustomContainer implements EmbeddedServletContainerCustomizer {

    @Value("${server.port}")
    private int serverPort;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(serverPort);
    }
}
