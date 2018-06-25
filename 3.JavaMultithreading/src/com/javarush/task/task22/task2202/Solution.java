package com.javarush.task.task22.task2202;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if(string == null || string.isEmpty()) throw new TooShortStringException();

            String[] arrs = string.split(" ");
            if(arrs.length < 5) throw new TooShortStringException();
            String result = arrs[1] + " " + arrs[2] + " " + arrs[3] + " " + arrs[4];

        return result;
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
