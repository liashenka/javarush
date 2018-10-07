package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("/home/mitry/text.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        try {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            StringWriter writer = new StringWriter();
            writer.write(new String(buffer));

            return writer;

        } catch (Exception e) {
            return new StringWriter();
        }
    }
}
