package lex_perm;

/**
 * Created by kelvinzero on 1/26/17.
 */
public class Lexographic {

    public static void nextPermutation(int[] array, Logger log) {

        int n = array.length;

        // Find longest non-increasing suffix, make this the head
        int i = n - 1;
        for(; i>0 && array[i-1] >= array[i]; i--);

        if (i <= 0) {
            for (int k = 0; k < array.length / 2; k++){
                int temp = array[k];
                int phi = (n * i - n );
                int cost = (k+1)*2;
                array[k] = array[n-1-k];
                array[n-1-k] = temp;
                log.nextLog(phi, cost);
            }
            return;
        }

        ////////////////////////////////
        int phi =  (n - (n - i));
        //System.out.println("i = " + i + "  n-i = " + (n-i) + "   ");
        ////////////////////////////////


        // [i - 1] pivot
        // Find rightmost element that exceeds the pivot
        int j = n - 1;
        while (array[j] <= array[i - 1])
            j--;

        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

     // Reverse the suffix
        j = n - 1;
        int cost = 2;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
            cost += 2;
        }

        //int phi = (array.length * i - (array.length - i) );
        log.nextLog(phi, cost);
    }
}
