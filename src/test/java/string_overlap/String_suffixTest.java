package string_overlap;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Kelvinzero on 2/7/2017.
 */
public class String_suffixTest {

    @Test
    public void bwt() throws Exception {

        String[] genome = StringSuffix.randomGenome(2);

        char[][] fl = StringSuffix.firstLastTable(genome);
        int[] Cx = StringSuffix.Cx(fl);
        System.out.println(Arrays.toString(Cx));

        for(int i = 0; i < fl.length; i++){
            System.out.println(fl[i][0] + " " + fl[i][1]);
        }

    }

    @Test
    public void FMIndex() throws Exception {

        FMindex fmi = new FMindex(StringSuffix.randomGenome(2));
        for (int i = 0; i < fmi._FL.length; i++){
            System.out.println(fmi._FL[i][0] + " " + fmi._FL[i][1]);
        }
        fmi.backwardSearch("ATA");
        for(int i = 0; i < fmi._genome.length; i++){
            fmi.findOverlaps(fmi._genome[i], 3);
        }
    }
}