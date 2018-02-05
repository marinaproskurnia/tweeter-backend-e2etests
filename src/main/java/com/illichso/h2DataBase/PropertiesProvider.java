package com.illichso.h2DataBase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesProvider {

    private static final String E2E_TESTS_PROPERTIES_FILE_PLACEHOLDER = "/config.properties";
    private static final String APPLICATION_PROPERTIES_FILE_PLACEHOLDER = "/application.properties";
    private static Properties sqlQueriesProperties;
    private static Properties applicationProperties;

    private static Properties getProperties(Properties properties, String propertiesFilePlaceholder) {
        InputStream inputStream = PropertiesProvider.class.getResourceAsStream(propertiesFilePlaceholder);
        if (properties == null) {
            synchronized (PropertiesProvider.class) {
                if (properties == null) {
                    properties = new Properties();
                }
            }
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return properties;
    }

    static String getQuery(String query) {
        return getProperties(sqlQueriesProperties, E2E_TESTS_PROPERTIES_FILE_PLACEHOLDER).getProperty(query);
    }

    static String getApplicationProperty(String property) {
        return getProperties(applicationProperties, APPLICATION_PROPERTIES_FILE_PLACEHOLDER).getProperty(property);
    }
}
