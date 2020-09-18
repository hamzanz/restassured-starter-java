package com.hamza.restassured.starter.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtil {
    public static String readFileToString(String fileName){

        String result = "";
        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while(line != null){
                sb.append(line);
                line = reader.readLine();
            }
            result = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();

        }finally{

            try {
                if(reader != null){
                    reader.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }
}
