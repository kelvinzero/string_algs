package fourier;

import plotter.GNUterm;
import plotter.GnuPlotter2D;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;


public class FFT {

    public FFT(){

    }

    static Point[] closestPoints(Point[] px) {

        int n = px.length;
        if(n < 3)
            return bruteforce(px);

        return closestPoints(Arrays.copyOfRange(px, 0, px.length/2-1));
    }

    static Point[] bruteforce(Point[] points){

        Point[] shortest = {points[0], points[1]};
        double dist = distance(shortest[0], shortest[1]);

        for(int i = 0; i < points.length; i++){
            for(int j = i+1; j < points.length; j++){
                if(distance(points[i],points[j]) < dist){
                    dist = distance(points[i],points[j]);
                    shortest[0] = points[i];
                    shortest[1] = points[j];
                }
            }
        }
        return shortest;
    }

    public static void bubbleSort(Point[] comparable, char sortOn) {
        boolean changed = false;
        do {
            changed = false;
            for (int a = 0; a < comparable.length - 1; a++) {

                int difference;
                if(sortOn == 'y')
                    difference = comparable[a].y - comparable[a+1].y;
                else
                    difference = comparable[a].x - comparable[a+1].x;

                if (difference > 0) {
                    Point tmp = comparable[a];
                    comparable[a] = comparable[a + 1];
                    comparable[a + 1] = tmp;
                    changed = true;
                }
            }
        } while (changed);
    }


    //  Distance = max{|x1-x2|,|y1-y2|}
    static double distance(Point one, Point two){
        return Math.max(Math.abs(one.getX()-two.getX()), Math.abs(one.getY()-two.getY()));
    }

}
