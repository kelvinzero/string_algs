package string_match;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kelvinzero on 2/2/2017.
 */
public class StringMatchingTest {

    @Test
    public void findMatches() throws Exception {

        String T = "ABACABBACALABAMADABBALABAMAABC";
        String P = "ALABAMA";

        StringMatching.findMatches(T, P);
    }

}