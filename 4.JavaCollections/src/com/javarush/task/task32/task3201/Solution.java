package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        System.out.println(fileName);
        System.out.println(number);
        System.out.println(text);

        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");

        if(number > randomAccessFile.length()) {
            randomAccessFile.seek(randomAccessFile.length());
        } else {
            randomAccessFile.seek(number);
        }

        randomAccessFile.write(text.getBytes());
        randomAccessFile.close();

    }
}
