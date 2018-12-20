package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.TreeMap;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object i = Integer.valueOf(42);
        String s = (String)i;

    }

    public void methodThrowsNullPointerException() {
        System.out.println(new TreeMap<String, Integer>().put(null, null));
    }

    public static void main(String[] args) {

        VeryComplexClass vc = new VeryComplexClass();
       // vc.methodThrowsClassCastException();
        vc.methodThrowsNullPointerException();
    }
}
