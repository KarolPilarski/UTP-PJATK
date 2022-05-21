package zad2;

import java.util.concurrent.Callable;

public class CountCoordinates implements Callable<Integer> {
    int radius;
    double radians;
    String type;
    public CountCoordinates(int radius, double radians, String type) {
        this.radius = radius;
        this.radians = radians;
        this.type=type;
    }

    @Override
    public Integer call() throws Exception {
        if(type.equals("X")) return 550 + (int) (Math.round(Math.cos(radians) * radius));
        else if(type.equals("Y")) return 350 + (int) (Math.round(Math.sin(radians) * radius));
        else return null;
    }


}
