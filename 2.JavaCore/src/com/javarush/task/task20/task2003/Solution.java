package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static Properties prop = new Properties();
    public static String fileName;

    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileName = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName);

        this.load(inputStream);
        inputStream.close();

        for (Map.Entry<String, String> p: properties.entrySet()) {
            System.out.println(p.getKey() + " " + p.getValue());
        }
        //implement this method - реализуйте этот метод
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        prop.clear();
        for (Map.Entry<String, String> pair : properties.entrySet()) {
            prop.put(pair.getKey(), pair.getValue());
        }

        prop.store(outputStream, ""); //
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        prop.load(inputStream); // in prop befindet sich Object stat String

        for (Map.Entry<Object, Object> pair : prop.entrySet()) {
            properties.put((String) pair.getKey(), (String) pair.getValue());

        }
    }

    public static void main(String[] args) throws Exception {

        Solution o = new Solution();
        o.fillInPropertiesMap();

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        o.save(fileOutputStream);
        fileOutputStream.close();
    }
}
