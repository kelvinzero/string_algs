package parallel_processes;

import org.junit.Test;

import java.awt.*;
import java.util.Random;

/**
 * Created by Josh on 3/17/2017.
 */
public class PointProcessorTest {


    @Test
    public void runProcesses() throws Exception {

        Random rand = new Random(5);
        Point[] points = new Point[8];
        for(int i = 1; i <= 8; i++){
            points[i-1] = new Point(i, rand.nextInt() % 20);
        }
        Point_processor pp = new Point_processor(points);
        System.out.println(pp.getPoints());
        pp.runProcesses();
    }

}