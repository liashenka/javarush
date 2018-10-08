package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream byteArrayoutputStream = new ByteArrayOutputStream();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        //Nummern
        for (int i = 0; i < 3 ; i++) {
            byteArrayoutputStream.write (48 + random.nextInt(10));
        }

        //Kleine Buchstaben
        for (int i = 0; i < 3; i++) {
            byteArrayoutputStream.write(65 + random.nextInt(26));
        }

        //Große Buchstaben
        for (int i = 0; i < 2 ; i++) {
            byteArrayoutputStream.write(97 + random.nextInt(26));
        }

        return byteArrayoutputStream;
    }
}
