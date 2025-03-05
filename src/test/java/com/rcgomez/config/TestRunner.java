package com.rcgomez.config;

import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestRunner {

    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    private static String baseUrl;

    @BeforeSuite
    public void setUpEnvironment() {
        loadProperties();
        baseUrl = getConfigVariable("url.base");
    }

    private void loadProperties(){
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Error loading properties file");
        }
    }

    private String getConfigVariable(String key) {
        return PROPERTIES.getProperty(key);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
