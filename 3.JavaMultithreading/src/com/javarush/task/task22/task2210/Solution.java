package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        getTokens("level22.lesson13.task01", ".");

    }
    public static String [] getTokens(String query, String delimiter) {

        String s = query;
        String delimit = delimiter;

        StringTokenizer tokenizer = new StringTokenizer(s, delimit);
        ArrayList<String> list = new ArrayList<>();


        while (tokenizer.hasMoreTokens()){

            String token = tokenizer.nextToken();
            list.add(token);
        }

        String[] result = new String[list.size()];
        for (int i = 0; i < list.size() ; i++) {
            result[i] = list.get(i);
        }


        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        return result;
    }
}
