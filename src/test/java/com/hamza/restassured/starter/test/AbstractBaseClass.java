package com.hamza.restassured.starter.test;

import com.hamza.restassured.starter.enums.Environment;
import com.hamza.restassured.starter.utils.DefaultConfig;
import com.hamza.restassured.starter.utils.TestDataReader;
import org.apache.log4j.Logger;

public abstract class AbstractBaseClass {
    protected String baseURL;
    protected Environment environment;
    protected DefaultConfig config;
    protected static final String userDirectory = System.getProperty("user.dir");
    protected static Logger logger;
    protected static String API_BASE_PATH;

    public AbstractBaseClass(){
        logger = Logger.getLogger(AbstractBaseClass.class.getName());

    }

    protected void initializeTest(String env) {
        logger.info("Initializing API test");
        setEnvironment(env);
        config = getConfig(environment);
        baseURL = config.getProperty("domainUrl");
        //Initialize test data reader
        TestDataReader.initializeTestDataReader(environment);

        logger.info("Base url set to - " + baseURL);
    }

    protected DefaultConfig getConfig(Environment env) {
        String configPath;
        switch (env) {
            case PROD:
                configPath = "config/prod.cfg";
                environment = Environment.PROD;
                break;

            default:
                configPath = "config/qa.cfg";
                environment = Environment.QA;
                break;
        }

        logger.info("Retrieve config file for the environment - " + env);
        return new DefaultConfig(configPath);
    }

    private void setEnvironment(String env) {
        if(env == null){
            this.environment = Environment.QA;
        }else if(env.equalsIgnoreCase("prod")){
            this.environment = Environment.PROD;
        }else{
            this.environment = Environment.QA;
        }
    }

}
