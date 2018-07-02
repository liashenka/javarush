package com.javarush.task.task22.task2212;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона

+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {

        if(telNumber == null){
            return false;
        }

        if(telNumber.matches("^\\+\\d{12}")) {return true;}

        if(telNumber.matches("^\\+\\d{2}\\(\\d{3}\\)\\d{7}")) {return true;}

        if(telNumber.matches("^\\+\\d{8}-\\d{2}-\\d{2}")) {return true;}

        if(telNumber.matches("^\\d{6}-\\d{4}")) {return true;}

        return false;
    }

    public static void main(String[] args) {


    }
}
