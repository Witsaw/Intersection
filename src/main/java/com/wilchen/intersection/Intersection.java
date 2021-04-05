package com.wilchen.intersection;

/*
 *  Name   : Intersection
 *  Author : William Chen
 */

import java.util.LinkedList;
import java.util.Queue;

public class Intersection{

    private static final double PERCENT = 0.5;

    private int max;

    private int time;

    private boolean light;

    private Queue<Car> streets[];

    private int numcars[];

    private int cardelay[];

    @SuppressWarnings("unchecked")
    public Intersection(int duration){
        time = 0;
        max = duration;
        light = false;

        streets = new LinkedList[4];
        numcars = new int[4];
        cardelay = new int[4];
        for(int i=0;i<4;i++){
            streets[i] = new LinkedList<Car>();
            numcars[i] = cardelay[i] = 0;
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addTraffic(Queue[] streets, int time){
        for(int i = 0; i < streets.length; i++){
            if(Math.random() < PERCENT){
                streets[i].add(new Car(time, (int)(Math.random()*3)));
            }
        }
    }

    private void advance(){
        if(light == false){
            Queue<Car> northQueue = streets[0];
            Queue<Car> southQueue = streets[2];
            Car northCar = northQueue.peek();
            Car southCar = southQueue.peek();
            if(northCar != null && northCar.getDirection() == 0 && southCar != null && southCar.getDirection() == 2){
                updateDelay(2, southQueue.poll());
                numcars[2]++;
            }else if(northCar != null && northCar.getDirection() == 2 && southCar != null && southCar.getDirection() == 0){
                updateDelay(0, northQueue.poll());
                numcars[0]++;
            }else{
                northQueue.poll();
                southQueue.poll();
                numcars[2]++;
                numcars[0]++;
                updateDelay(0, northQueue.poll());
                updateDelay(2, southQueue.poll());
            }

        }else{
            Queue<Car> northQueue = streets[1];
            Queue<Car> southQueue = streets[3];

            Car northCar = northQueue.peek();
            Car southCar = southQueue.peek();
            if(northCar != null && northCar.getDirection() == 0 && southCar != null && southCar.getDirection() == 2){
                updateDelay(3, southQueue.poll());
                numcars[3]++;
            }else if(northCar != null && northCar.getDirection() == 2 && southCar != null && southCar.getDirection() == 0){
                updateDelay(1, northQueue.poll());
                numcars[1]++;
            }else{
                northQueue.poll();
                southQueue.poll();
                numcars[1]++;
                numcars[3]++;
                updateDelay(3, southQueue.poll());
                updateDelay(1, northQueue.poll());
            }
        }
    }

    private void updateDelay(int dir, Car c){
        if(c != null)
            cardelay[dir] = cardelay[dir] + time - c.getStartTime();
    }

    private void switchLights(){
        if(time%10 == 0)light = !light;
    }

    public void run(){
        for(;time<max;time++){
            advance();
            addTraffic(streets, time);
            switchLights();
        }
    }

    public void printStats(){
        System.out.println("                 N \tE \tS \tW");
        System.out.println("Cars crossed: \t"+numcars[0]+" \t"+
                numcars[1]+" \t"+numcars[2]+" \t"+numcars[3]);
        System.out.println("Total delay:   \t"+cardelay[0]+" \t"+
                cardelay[1]+" \t"+cardelay[2]+" \t"+
                cardelay[3]);

        System.out.print("Average delay: \t");
        for(int i=0;i<4;i++){
            if(numcars[i]==0)System.out.print("N/A \t");
            else System.out.print(((double)cardelay[i]/numcars[i])+" \t");
        }
        System.out.println();

        System.out.println("Cars stranded: \t"+streets[0].size()+" \t"+
                streets[1].size()+" \t"+
                streets[2].size()+" \t"+
                streets[3].size());
    }
}