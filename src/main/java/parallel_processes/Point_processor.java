package parallel_processes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Josh Cotes
 */
public class Point_processor {

    private static int _processorCount;
    private Point[] _points;
    private PointProcess[] _processors;

    public static void main(String[] args){

        Random rand = new Random(5);
        Point[] points = new Point[8];
        for(int i = 1; i <= 8; i++){
            points[i-1] = new Point(i, rand.nextInt() % 20);
        }
        Point_processor pp = new Point_processor(points);
        System.out.println(pp.getPoints());
        pp.runProcesses();
    }

    Point_processor(Point[] points){
        _processorCount = points.length;
        _points = points;
        _processors = new PointProcess[_processorCount];
    }

    String getPoints() {
        final StringBuilder pointString = new StringBuilder();
        Arrays.stream(_points).forEach(P -> pointString.append(P.getX()).append(" ").append(P.getY()).append("\n"));
        return pointString.toString();
    }

    void runProcesses(){

        int process_num = 0;
        for(Point point : _points) {
            _processors[process_num] = new PointProcess(process_num, point, _points[0]);
            process_num++;
        }
        for(PointProcess pp : _processors){
            pp.start();
        }
    }

    /**
     * Defines a multi-thread process to determine max observable peak from a point
     */
    class PointProcess extends Thread{

        private int _pid;
        private int _round;
        private Point _point;
        private Point _high_point;
        private Point _origin;
        private boolean _is_observable = true;
        private ArrayList _message;

        PointProcess(int pnum, Point thisPoint, Point origin){
            _pid = pnum;
            _point = thisPoint;
            _high_point = thisPoint;
            _origin = origin;
            _round = 1;
            _message = new ArrayList();
        }

        int getExecutionNumber(){
            return _round;
        }

        @Override
        public void run() {

            int             bitCount            = Integer.toBinaryString(_processorCount-1).length();
            int             exchange_pid;
            PointProcess    exchange_process;

            _message.add(0);

            while (_round <= bitCount) {

                exchange_pid  = Process_tools.flipBit(_pid, _round, bitCount);
                exchange_process = _processors[exchange_pid];

            }
            //System.out.println("PID: " + _pid + " Point:[" + _point.x + "," + _point.y + "]  Visible: " + _is_observable);
        }
    }
}