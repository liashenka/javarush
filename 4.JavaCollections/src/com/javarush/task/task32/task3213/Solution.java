package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        try {
            StringWriter writer = new StringWriter();

            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < line.length(); i++) {
                char ch = (char) ((int) line.charAt(i) + key);
                sb.append(ch);
            }
            br.close();
            return sb.toString();
            
        } catch (Exception e) {
            return "";
        }
    }
}
