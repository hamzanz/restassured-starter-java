package com.hamza.restassured.starter.utils;

import com.google.gson.GsonBuilder;
import com.hamza.restassured.starter.enums.Environment;
import com.hamza.restassured.starter.models.post.PostModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestDataReader {

    private static String testdataPath;

    public static void initializeTestDataReader(Environment env) {
        testdataPath = System.getProperty("user.dir") + "/src/test/resources/testdata";
        switch (env) {
            case PROD:
                testdataPath = testdataPath.concat("/prod");
                break;
            default:
                testdataPath = testdataPath.concat("/qa");
        }
    }

    public static <T> T[] getTestData(String testDataFile, Class<T[]> classType) throws FileNotFoundException {
        String dataFilePath = testdataPath.concat("/" + testDataFile );
        GsonBuilder gsonBuilder = new GsonBuilder();

        FileReader reader = new FileReader(dataFilePath);
        BufferedReader bufferedReader = new BufferedReader(reader);

        T[] data = gsonBuilder.create().fromJson(bufferedReader, classType);

        return data;
    }

//    public static PostModel[] getTestData(String testDataFile) throws FileNotFoundException {
//        String dataFilePath = testdataPath.concat("/" + testDataFile );
//        GsonBuilder gsonBuilder = new GsonBuilder();
//
//        FileReader reader = new FileReader(dataFilePath);
//        BufferedReader bufferedReader = new BufferedReader(reader);
//
//        PostModel[] data = gsonBuilder.create().fromJson(bufferedReader,  PostModel[].class);
//
//        return data;
//    }

}
