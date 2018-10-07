package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        

        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        byte[] buffer = new byte[text.length()];

        randomAccessFile.seek(number);
        randomAccessFile.read(buffer, 0, text.length());

        String check = convertByteToString(buffer);
        System.out.println(check);
        randomAccessFile.seek(randomAccessFile.length());

        if (check.equals(text)) {
            randomAccessFile.write("true".getBytes());
        } else {
            randomAccessFile.write("false".getBytes());
        }
    }

    private static String convertByteToString(byte[] bt) {
        return new String(bt);
    }

}
