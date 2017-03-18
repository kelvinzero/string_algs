package parallel_processes;

import java.awt.*;
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
            _processors[process_num] = new PointProcess(process_num, point);
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

        private int pid;
        private int _round;
        private Point _this_point;

        PointProcess(int pnum, Point thisPoint){
            pid = pnum;
            _this_point = thisPoint;
            _round = 0;
        }

        int getExecutionNumber(){
            return _round;
        }

        @Override
        public void run() {

            int bitCount = Integer.toBinaryString(_processorCount-1).length();
            int exchange_pid = Process_tools.flipBit(pid, _round +1, bitCount);
            PointProcess exchange_processor = _processors[exchange_pid];

            while (_round < bitCount) {

                // the lower one does nothing, only perform if higher process
                if(exchange_pid < this.pid && exchange_processor._round == _round){

                }
            }
        }
    }
}