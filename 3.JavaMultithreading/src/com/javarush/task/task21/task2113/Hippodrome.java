package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses = new ArrayList<>();
    public static Hippodrome game;


    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {

            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        List<Horse> horseList = new ArrayList<>();

        Horse horse1 = new Horse("Wildlachs", 3, 0);
        Horse horse2 = new Horse("Zanderfisch", 3, 0);
        Horse horse3 = new Horse("Kabeljau", 3, 0);

        horseList.add(horse1);
        horseList.add(horse2);
        horseList.add(horse3);

        game = new Hippodrome(horseList);

        game.run();
    }
}
