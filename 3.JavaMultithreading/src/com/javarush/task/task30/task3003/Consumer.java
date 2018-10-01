package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(450);

            while (true) {
                ShareItem item = queue.take();
                System.out.format("Processing " + item.toString() + "%n");
            }

        } catch (InterruptedException e) { }
    }
}
/*
* 5. Реализация метода run для Consumer:
5.1. Усыпи трэд на 0.45 секунды.
5.2. В бесконечном цикле забери элемент из очереди методом take и выведи в консоль "Processing item.toString()".

6. Сверь вывод с файлом output.txt.
7. Стек-трейс не выводи в консоль.
8. Для вывода любой информации на экран используй System.out.format.*/