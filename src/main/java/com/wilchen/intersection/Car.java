package com.wilchen.intersection;

/*
 *  Name   : Car
 *  Author : William Chen
 */
public class Car {
    private final int direction;
    private final int startTime;

    public Car(int time, int direction){
        this.direction = direction;
        this.startTime = time;
    }

    public int getDirection(){
        return direction;
    }
    public int getStartTime(){
        return startTime;
    }
}
