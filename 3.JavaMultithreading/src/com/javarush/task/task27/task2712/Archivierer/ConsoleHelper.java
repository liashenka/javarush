package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static String message;
    private static int readInt;

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        while (true) {
            try {
                message = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                try {
                    message = reader.readLine();
                } catch (IOException e1){

                }
            }
        }
        return message;
    }

    public static int readInt() throws IOException {
        try {
            readInt = Integer.parseInt(readString());
        } catch (NumberFormatException e){
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            readInt = Integer.parseInt(readString());
        }
        return readInt;
    }
}
