package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;  //код валюты, например, USD Состоит из трех букв
    private Map<Integer, Integer> denominations = new HashMap<>(); // это Map<номинал, количество>

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {

        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if(denominations.containsKey(denomination)){
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount(){
        int result = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) {
            result += pair.getKey() * pair.getValue();
        }
        return result;
    }

    public boolean hasMoney(){
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
        int sum = expectedAmount;
        Map<Integer, Integer> temp = new HashMap<>();
        temp.putAll(denominations);

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : temp.entrySet()) {
            list.add(pair.getKey());
        }

        Collections.sort(list);
        Collections.reverse(list);

        Map<Integer, Integer> result = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (Integer i : list) {
            int key = i;
            int value = temp.get(key);

            while (true){
                if(sum < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                sum -= key;
                value--;
                if(result.containsKey(key)) result.put(key, result.get(key) + 1);
                else result.put(key, 1);
            }
        }

        if(sum > 0) throw new NotEnoughMoneyException();
        else{
            for (Map.Entry<Integer, Integer> pair : result.entrySet()) {
                ConsoleHelper.writeMessage("\\t" + pair.getKey() + " - " + pair.getValue());
            }
            denominations.clear();
            denominations.putAll(temp);
            ConsoleHelper.writeMessage("Transaction was successful!");
        }

        return result;
    }
}
