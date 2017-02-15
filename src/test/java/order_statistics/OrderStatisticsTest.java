package order_statistics;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kelvinzero on 2/13/2017.
 */
public class OrderStatisticsTest {
    @Test
    public void randomizedSelect() throws Exception {
        int[] ary = {1, 5, 7, 2, 4, 9, 10, 22, 16, 6, 3};
        OrderStatistics ostat = new OrderStatistics(ary);
        ostat.randomizedSelect(0, ary.length-1, 4);
    }

}