package com.javarush.task.task29.task2909.human;


public class Soldier extends Human{

    private BloodGroup bloodGroup;


    public Soldier(String name, int age) {
        super(name, age);
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void fight() {
    }


    @Override
    public void live(){
        fight();
    }

}
