package com.javarush.task.task35.task3509;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution<T extends Collection> {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<>();
        for (T t : elements) {
            list.add((T) t);
        }

        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        HashSet<T> hashSet = new HashSet<>();
        for (T t : elements) {
            hashSet.add((T) t);
        }
        return hashSet;
    }

    public static<K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        if(keys.size() != values.size()) throw new IllegalArgumentException();
        HashMap<K, V> hashMap = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i), values.get(i));
        }

        return hashMap;
    }
}
