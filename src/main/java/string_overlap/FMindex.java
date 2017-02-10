package string_overlap;

import java.util.ArrayList;

/**
 * Created by Josh on 2/9/2017.
 */
public class FMindex {

    char[][] _FL;
    String[] _genome;
    int[] _Cx;
    ArrayList<String> bws;

    public FMindex(String[] genome){
        _genome = genome;
        _FL = StringSuffix.firstLastTable(genome);
        _Cx = StringSuffix.Cx(_FL);
        bws = StringSuffix.BWT(genome);
    }

    private int numberFromLetter(char i){
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

    private char letterFromNumber(int i){
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

        while(begin <= end && i >= 0){
            i--;
            interval = updateBackwards(begin, end, '$');
            begin = interval[0];
            end = interval[1];
        }
        return interval;
    }

    private int[] updateBackwards(int b, int e, char letter){

        int [] interval = {
                Cx(letter)-1 + rank(letter, b-1)+1,
                Cx(letter)-1 + rank(letter, e)
        };
        return interval;
    }

    public int[] backwardSearch(String pattern){

        int[] interval = new int[2];
        int i = pattern.length()-1;
        char thisLetter = pattern.charAt(i);
        int begin = 0;
        int end = _FL.length-1;

        while(begin <= end && i >= 0){
            thisLetter = pattern.charAt(i);
            i--;
            begin = Cx(thisLetter)-1 + rank(thisLetter, begin-1)+1;
            end = Cx(thisLetter)-1 + rank(thisLetter, end);
            interval[0] = begin;
            interval[1] = end;

        }
        return  interval;
    }

    private int rank(char base, int upTo) {

        int rank = 0;

        for (int i = 0; i <= upTo; i++) {
            if (_FL[i][1] == base)
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
