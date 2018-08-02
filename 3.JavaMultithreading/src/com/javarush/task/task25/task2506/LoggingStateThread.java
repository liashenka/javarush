package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread implements Runnable{
    private Thread target;
    LoggingStateThread (Thread target){
        this.target = target;
    }

    @Override
    public void run(){
        Thread.State currentsState = target.getState();
        System.out.println(currentsState);
        while ((!currentsState.equals(State.TERMINATED))){
            Thread.State newState = target.getState();
            if(!currentsState.equals(newState)){
                System.out.println(newState);
                currentsState = newState;
            }
        }
        this.interrupt();
    }
}
