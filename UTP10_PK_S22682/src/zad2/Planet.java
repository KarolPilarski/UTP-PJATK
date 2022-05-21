package zad2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Planet extends Thread{
    String name;
    int CordX;
    int CordY;
    int speed;
    int radius;
    int size;
    Color color;
    int position;
    int state;


    public void run(){
        double angle=Math.random()*360;
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(state==0) {
                double radians = angle * 2.0 * Math.PI / 360.0;
                final ExecutorService executorService = Executors.newSingleThreadExecutor();
                final Future<Integer> CordXf = executorService.submit(new CountCoordinates(radius,radians,"X"));
                final Future<Integer> CordYf = executorService.submit(new CountCoordinates(radius,radians,"Y"));

                try{
                    CordX = CordXf.get();
                    CordY = CordYf.get();
                } catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
                executorService.shutdown();

                angle = angle + (1.0 / (speed+1)) * 100;
            }
        }
    }

    public Planet(String name, int radius, int size, int speed, Color color) {
        this.name = name;
        this.radius=radius;
        this.size = size;
        this.speed = speed;
        this.color = color;
        CordX=550-(size/2);
        CordY=400-radius-(size/2);
        this.position=0;
        state=0;
    }

    public void printState(){
        if(state==0) System.out.println("RUNNING");
        else if(state==1) System.out.println("WAITING");
        else if(state==2) System.out.println("ABORTED");
    }
    public void startThread(){
        if(state==1){
            state=0;
        }
    }
    public void stopThread(){
        if(state==0){
            state=1;
        }
    }

    public void abort(){
        this.interrupt();
        this.stop();
        state=2;

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getPlanetName() {
        return name;
    }

    public int getCordX() {
        return CordX;
    }

    public int getCordY() {
        return CordY;
    }


    public int getRadius() {
        return radius;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
