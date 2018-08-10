package com.javarush.task.task26.task2611;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread currentThread = Thread.currentThread();
            while (!currentThread.isInterrupted()) {

                for (int i = 1; i < 6; i++) {

                    map.put(String.valueOf(i), "Some text for " + i);
                    Thread.sleep(500);
                }

            }
        } catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
