package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpeedTest {

    @Test
    public void testHashMapStorage() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);

        Set<String> origStrings = IntStream.range(0, 10000)
                .mapToObj(i -> Helper.generateRandomString())
                .collect(Collectors.toSet());

        Set<Long> ids = new HashSet<>();

        long timeForGettingIds1 = getTimeForGettingIds(shortener1, origStrings, ids);
        long timeForGettingIds2 = getTimeForGettingIds(shortener2, origStrings, ids);

        Assert.assertTrue(timeForGettingIds1 > timeForGettingIds2);

        Set<String> resultStrings = new HashSet<>();

        long timeForGettingStrings1 = getTimeForGettingStrings(shortener1, ids, resultStrings);
        long timeForGettingStrings2 = getTimeForGettingStrings(shortener2, ids, resultStrings);

        Assert.assertEquals(timeForGettingStrings1, timeForGettingStrings2, 30f);
    }

    public long getTimeForGettingIds(final Shortener shortener, Set<String> strings, Set<Long> ids) {
        long startTime = new Date().getTime();
        for (String s : strings) {
            Long id = shortener.getId(s);
            ids.add(id);
        }
        long endTime = new Date().getTime();
        return endTime - startTime;
    }

    public long getTimeForGettingStrings(final Shortener shortener, Set<Long> ids, Set<String> strings) {
        long startTime = new Date().getTime();
        for (long id : ids) {
            String s = shortener.getString(id);
            strings.add(s);
        }
        long endTime = new Date().getTime();
        return endTime - startTime;
    }
}
