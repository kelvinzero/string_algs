package order_statistics;

import sun.util.resources.ca.CalendarData_ca;

import java.util.Random;

/**
 * Created by Kelvinzero on 2/13/2017.
 */
public class OrderStatistics
{

    int[] _values;
    public OrderStatistics(int[] values){
        _values = values;
    }

    public int randomizedSelect(int start, int end, int i){
        if(start == end)
            return _values[start];
        int partition = start +  Math.abs(new Random().nextInt() % end);
        int k = partition - start + 1;
        if(i == k)
            return _values[partition];
        else if(i < k)
            return randomizedSelect(start, partition-1, i);
        else
            return randomizedSelect(partition+1, end, i);
    }

}
