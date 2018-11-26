package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        StorageStrategy strategy1 = new HashMapStorageStrategy();
        StorageStrategy strategy2 = new OurHashMapStorageStrategy();
        FileStorageStrategy strategy3 = new FileStorageStrategy();
        OurHashBiMapStorageStrategy strategy4 = new OurHashBiMapStorageStrategy();
        HashBiMapStorageStrategy strategy5 = new HashBiMapStorageStrategy();
        DualHashBidiMapStorageStrategy strategy6 = new DualHashBidiMapStorageStrategy();
        testStrategy(strategy1, 100);
        testStrategy(strategy2, 100);
        testStrategy(strategy3, 100);
        testStrategy(strategy4, 100);
        testStrategy(strategy5, 100);
        testStrategy(strategy6, 100);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for (String string : strings)
            result.add(shortener.getId(string));

        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for (Long key : keys) {
            result.add(shortener.getString(key));
        }

        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        System.out.println(strategy.getClass().getSimpleName());

        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date beforTestGetIds = new Date();
        Set<Long> idsSet = getIds(shortener, strings);
        Date afterTestGetIds = new Date();
        Helper.printMessage(Long.toString(afterTestGetIds.getTime() - beforTestGetIds.getTime()));

        Date beforTestGetStrings = new Date();
        Set<String> stringSet = getStrings(shortener, idsSet);
        Date afterTestGetStrings = new Date();
        Helper.printMessage(Long.toString(afterTestGetStrings.getTime() - beforTestGetStrings.getTime()));

        if(strings.equals(stringSet))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }

}
