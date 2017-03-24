package parallel_processes;

import java.awt.*;

/**
 * @author Josh Cotes
 */
class Process_tools {

    static int flipBit(int intIn, int bitAt, int maxBits){

        String binaryString = Integer.toBinaryString(intIn);
        char[] binaryChars = new char[maxBits];
        int charsLength = binaryChars.length;
        int stringLength = binaryString.length();

        for(int i = 0; i < binaryChars.length; i++){
            if(i < binaryString.length())
                binaryChars[charsLength-1-i] = binaryString.charAt(stringLength-1-i);
            else
                binaryChars[charsLength-1-i] = '0';
        }

            if (binaryChars[binaryChars.length - bitAt] == '1')
                binaryChars[binaryChars.length - bitAt] = '0';
            else
                binaryChars[binaryChars.length - bitAt] = '1';

        return Integer.parseInt(new String(binaryChars), 2);
    }

    static double vectorDirection(Point start, Point mid, Point end){
        double x1 = mid.x - start.x;
        double y1 = mid.y - start.y;
        double x2 = end.x - start.x;
        double y2 = end.y - start.y;
        return (x1 * y2)-(x2 * y1);
    }

}
