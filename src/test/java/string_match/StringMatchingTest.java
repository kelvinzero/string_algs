package string_match;


import org.junit.jupiter.api.Test;

/**
 * Created by Kelvinzero on 2/2/2017.
 */
public class StringMatchingTest {

    @Test
    public void findMatches() throws Exception {

        String T = "ABACABBACALABAMADABBALABAMAABC";
        String P = "BANANA";
        StringMatching.findMatches(T, P);
    }

}