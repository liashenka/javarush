package com.javarush.task.task27.task2712.ad;

public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;     //начальная сумма, стоимость рекламы
    private int hits;               //количество оплаченных показов
    private int duration;           //продолжительность
    private long amountPerOneDisplaying;

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = hits > 0 ? initialAmount/hits : 0;
    }

    public void revalidate(){
        if(hits <= 0) throw new UnsupportedOperationException();
        else hits--;
    }

    public int getHits() {
        return hits;
    }
}
