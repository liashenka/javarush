package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null)
            return 0;

        Set<Character> uniqueSubString = new HashSet<>();
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!uniqueSubString.contains(Character.valueOf(s.charAt(i))))
                uniqueSubString.add(s.charAt(i));
            else {
                if (maxLength < uniqueSubString.size())
                    maxLength = uniqueSubString.size();
                uniqueSubString.clear();
                i--;
            }
        }
        if (maxLength < uniqueSubString.size())
            maxLength = uniqueSubString.size();
        return maxLength;
    }
}
