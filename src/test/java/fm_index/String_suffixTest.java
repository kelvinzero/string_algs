package fm_index;



import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kelvinzero on 2/7/2017.
 */
public class String_suffixTest {

    final String[] _READS = {
            "ATC",
            "CGC",
            "GCC"
    };
    @Test
    public void bwt() throws Exception {

        String[] genome = StringSuffix.randomGenome(3, 3, 3);

        char[][] fl = StringSuffix.firstLastTable(genome);
        ArrayList<String> bwt = StringSuffix.BWT(genome);

        Arrays.stream(genome).forEach(System.out::println);
        bwt.forEach(System.out::println);
    }

    @Test
    public void FMIndex() throws Exception {

        FMindex fmi = new FMindex(_READS);
        Arrays.stream(fmi._genome).forEach(System.out::println);


        for(int i = 0; i < fmi._genome.length; i++){
            fmi.findOverlaps(fmi._genome[i], 3);
        }
        for(char[] c : fmi._FL){
            //System.out.println(c[0] + " " + c[1]);
        }

        for(int i = 0; i < fmi._genome.length; i++){
            ArrayList<int[]> prs = fmi.findIntervals(fmi._genome[i], 3);
            prs.forEach(System.out::println);
        }
    }

    @Test
    public void reversal() throws Exception{
        String[] genome = new String[_READS.length];
        String newStr = "";
        for(int i = 0; i < _READS.length; i++){
            newStr = "";
            for(int j = 0; j < _READS[_READS.length-i-1].length(); j++){
                newStr += _READS[_READS.length-i-1].charAt(_READS[_READS.length-i-1].length()-1-j);
            }
            genome[i] = newStr;
        }

        FMindex fm1 = new FMindex(_READS);
        FMindex fmi = new FMindex(genome);
        for(String s : fmi.bws){
            System.out.println(s);
        }
        System.out.println();
        for(String s : fm1.bws){
            System.out.println(s);
        }
    }

    @Test
    public void SAfromBWT() throws Exception{
        String[] sequence = {"BANANA"};

        FMindex fm1 = new FMindex(sequence);
        int[] Cx = fm1._Cx;
        char[][] FL = fm1._FL;
        ArrayList<String> bwt = fm1.bws;
        bwt.forEach(System.out::println);
        char[] bwtOne = new char[FL.length];
        int i = 0;
        for(char[] c : FL){
            bwtOne[i++] = c[1];
        }
        StringSuffix.SAfromBWT(bwtOne, Cx);
    }
}