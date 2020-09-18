package com.hamza.restassured.starter.utils;

import java.util.Properties;

public class DefaultConfig {
    Properties configFile;

    public DefaultConfig(String configFileLocation) {
        configFile = new java.util.Properties();
        try {
            configFile.load(this.getClass().getClassLoader().getResourceAsStream(configFileLocation));

        } catch (Exception ex) {
            System.out.println("Failed loading the config file.");
            ex.printStackTrace();
        }
    }


    public String getProperty(String key) {
        return this.configFile.getProperty(key);
    }

}
