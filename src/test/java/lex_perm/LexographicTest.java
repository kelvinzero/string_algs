package lex_perm;

import lex_perm.Lexographic;
import lex_perm.Logger;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by kelvinzero on 1/26/17.
 */
public class LexographicTest {

    private final int[] ARRAY = {1,2,3,4};

    @Test
    public void nextPermutation() throws Exception {
        int[] arrayStop = Arrays.copyOf(ARRAY, ARRAY.length);
        Arrays.sort(arrayStop, 0, ARRAY.length);

        StringBuilder logToTex = new StringBuilder();
        Logger log = new Logger();

        do { // stops when cycle rolls over
            logToTex.append(Arrays.toString(ARRAY));
            Lexographic.nextPermutation(ARRAY, log );
            logToTex.append(" & " + log._phi + " & " + log._cost + " & " + log._cost + " + " +
            log._phi + " - " + log._lastCiHat + " = " + log._thisCiHat + "\\\\\n\\hline\n");
            if(log._thisCiHat < 0){
                System.out.println(logToTex.toString());
                System.out.println("\n**FAIL below zero map");
                throw new RuntimeException("Failed");
            }

        } while (!Arrays.equals(ARRAY, arrayStop));
        logToTex.append(Arrays.toString(arrayStop) + " & * & * & * \\\\\n\\hline");
        System.out.println(logToTex.toString());
    }

}