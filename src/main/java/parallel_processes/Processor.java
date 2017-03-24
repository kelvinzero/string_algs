package parallel_processes;

import java.awt.*;
import java.util.concurrent.Semaphore;

/**
 * Created by Josh on 3/19/2017.
 */
public class Processor {

    static Semaphore sem = new Semaphore(1, true);

    private int[] _processors_round;
    private int[] _observableId;
    private P_Process[] _processes;
    private int _bitCount;

    public Processor(Point[] points){
        int n = points.length;
        _processors_round = new int[n];
        _observableId = new int[n];
        _processes = new P_Process[n];
        _bitCount = Integer.toBinaryString(n-1).length();
        make_processes(points);
    }

    public void start(){

    }

    private void make_processes(Point[] points){

        int pid = 0;
        for(Point p: points)
            _processes[pid] = new P_Process(p, points[0], pid++);
        for(P_Process proc : _processes)
            proc.start();
    }


    class P_Process extends Thread{

        Point _my_point;
        Point _rightmost_observable;
        Point _origin;
        int _pid;

        P_Process(Point my_point, Point origin, int myPID){
            _my_point = my_point;
            _origin = origin;
            _pid = myPID;
        }

        @Override
        public void run(){

            int swap_PID = Process_tools.flipBit(_pid, _processors_round[_pid], _bitCount);

            while(_processors_round[_pid] < _bitCount){

                if(_processors_round[_pid] == _processors_round[swap_PID]){

                    if(_pid > swap_PID){

                    }
                }
            }
        }

    }

}
