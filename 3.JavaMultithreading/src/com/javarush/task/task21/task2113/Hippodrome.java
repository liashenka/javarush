package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses = new ArrayList<>();
    public static Hippodrome game;
    public Horse winner;


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

    public void move() { //В методе move класса Hippodrome должен быть вызван метод move по одному разу для каждой лошади(каждого элемента списка horses).
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

    public Horse getWinner() {

        double maxDistance = 0.0;
        for (Horse horse : horses) {
            if(horse.getDistance() > maxDistance) {
                maxDistance = horse.getDistance();
                winner = horse;
            }
        }

        return winner;
    }

    public void printWinner() {
        Horse winner = getWinner();
        System.out.println("Winner is " + winner.getName() + "!");
    }

    public static void main(String[] args) throws InterruptedException {

        List<Horse> horseList = new ArrayList<>();

        Horse horse1 = new Horse("Wildlachs", 3, 0);
        Horse horse2 = new Horse("Hecht", 3, 0);
        Horse horse3 = new Horse("Kabeljau", 3, 0);

        horseList.add(horse1);
        horseList.add(horse2);
        horseList.add(horse3);

        game = new Hippodrome(horseList);

        game.run();

        game.printWinner();
    }
}
