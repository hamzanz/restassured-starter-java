package com.hamza.restassured.starter.utils;

public class SchemaReaderUtil {

    public static String readJsonSchemaRelativeToModels(String relativePath){

        String jsonString;

        String fileLocation = System.getProperty("user.dir") + "/src/main/java/com/hamza/restassured/starter/models" + relativePath;
        jsonString = FileReaderUtil.readFileToString(fileLocation);

        return jsonString;
    }
}
