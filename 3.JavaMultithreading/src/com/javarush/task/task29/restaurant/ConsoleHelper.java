package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String text = bis.readLine();
        return text;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        ConsoleHelper.writeMessage("Выберите блюдо. Для завершения наберите 'exit'.");
        ConsoleHelper.writeMessage(Dish.allDishesToString());

        List<Dish> dishes = new ArrayList<>();

        while (true) {
            String dish = readString();
            if (dish.equals("exit")){
                break;
            }

            if (dish.isEmpty()) {
                ConsoleHelper.writeMessage("Блюдо не выбрано");
                continue;
            }
            boolean found = false;
            for (Dish d : Dish.values()) {
                if (d.name().equals(dish)) {
                    dishes.add(d);
                    found = true;
                }
            }
            if (!found) {
                ConsoleHelper.writeMessage("Такого блюда в меню нет");
                continue;
            }
        }
        return dishes;
    }
}
