package com.ecom.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        properties = new Properties();
        String configPath = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
        try (FileInputStream file = new FileInputStream(configPath)) {
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties from " + configPath, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
