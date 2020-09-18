package com.hamza.restassured.starter.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass extends AbstractBaseClass {

    protected static RequestSpecBuilder requestSpecBuilder = null;
    protected static RequestSpecification requestSpecification = null;

    @BeforeClass(alwaysRun = true)
    @Parameters({"env"})
    public void baseSetup(@Optional("null") String env) {
        initializeTest(env);

        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
    }

}
