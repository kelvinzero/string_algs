package fm_index;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Josh on 2/9/2017.
 */
public class FMindex {

    char[][] _FL;
    String[] _genome;
    int[] _Cx;
    ArrayList<String> bws;

    public  FMindex(String[] genome){
        _genome = genome;
        Arrays.sort(_genome);
        _FL = StringSuffix.firstLastTable(genome);
        _Cx = StringSuffix.Cx(_FL);
        bws = StringSuffix.BWT(genome);
    }

    public static int numberFromLetter(char i){
        switch (i){
            case '$':
                return 0;
            case 'A':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
            case 'T':
                return 4;
        }
        return -1;
    }

    public static char letterFromNumber(int i){
        switch (i){
            case 0:
                return '$';
            case 1:
                return 'A';
            case 2:
                return 'C';
            case 3:
                return 'G';
            case 4:
                return 'T';
        }
        return '$';
    }

    public int[] findOverlaps(String pattern, int threshold){

        int i = pattern.length()-1;
        int begin = 0;
        int end = _FL.length-1;
        int[] interval = {};
        int[] interval$ = {};

        while(begin <= end && i >= 0){
            // if pattern length >= tao
            if( pattern.length() - i > threshold){
                interval$ = updateBackwards(begin, end, '$');
                if(interval$[0] <= interval$[1]){
                    System.out.print(pattern + " <-> {");
                    for(int k = interval$[0]; k <= interval$[1]; k++){
                        System.out.print(_genome[k] + " ");
                    }
                    System.out.print("} : interval$ {" + interval$[0] + "," + interval$[1] + "}");
                }
            }

            interval = updateBackwards(begin, end, pattern.charAt(i--));
            begin = interval[0];
            end = interval[1];
        }
        if(interval[0] <= interval[1]){
            System.out.println("{" + bws.get(interval[0]) + "} contained.");
        }
        return interval;
    }

    private int[] updateBackwards(int b, int e, char letter){

        int [] interval = {
                Cx(letter)-1 + rank(letter, b-1, _FL)+1,
                Cx(letter)-1 + rank(letter, e, _FL)
        };
        return interval;
    }

    public int[] updateFwdBackwd(int b, int e, int bp, int ep, char letter){
        int[] interval = new int[4];
        interval[0] = Cx(letter)-1 + rank(letter, b-1, _FL)+1;
        interval[1] = Cx(letter)-1 + rank(letter, e, _FL);
        interval[2] = bp + (rankLT(letter, e, _FL) - rankLT(letter, b-1, _FL));
        interval[3] = bp + (rank(letter, e, _FL) - rankLT(letter, b-1, _FL)-1);
        return interval;
    }

    public ArrayList<int[]> findIntervals(String pattern, int threshold){
        ArrayList<int[]> pairs = new ArrayList<>();

        int i = pattern.length()-1;
        int begin = 0;
        int end = _FL.length-1;
        int beginPrime = begin;
        int endPrime = end;

        int[] interval = {};
        int[] interval$ = {};

        while(begin <= end && i >= 0){
            // if pattern length >= tao
            if( pattern.length() - i > threshold){
                interval$ = updateFwdBackwd(begin, end, beginPrime, endPrime, '$');
                if(interval$[0] <= interval$[1]){
                    pairs.add(interval$);
                }
            }
            interval = updateFwdBackwd(begin, end, beginPrime, endPrime, pattern.charAt(i));
            begin = interval[0];
            end = interval[1];
            beginPrime = interval[2];
            endPrime = interval[3];
            i--;
        }

        return pairs;

    }

    public static int rankLT(char base, int upto, char[][] FL){
        int rankLT = 0;

        for(int i = 0; i <= upto; i++){
            if(FL[i][1] < base){
                rankLT++;
            }
        }
        return rankLT;
    }

    public static int rank(char base, int upTo, char[][] FL) {

        int rank = 0;

        for (int i = 0; i <= upTo; i++) {
            if (FL[i][1] == base)
                rank++;
        }
        return rank;
    }

    private int Cx(char Qi){
        switch (Qi){
            case '$':
                return _Cx[0];
            case 'A':
                return _Cx[1];
            case 'C':
                return _Cx[2];
            case 'G':
                return _Cx[3];
            case 'T':
                return _Cx[4];
        }
        return -1;
    }

}
