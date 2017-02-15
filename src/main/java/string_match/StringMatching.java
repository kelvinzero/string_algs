package string_match;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Kelvinzero on 2/2/2017.
 */
public class StringMatching {

    private static char[] _P;
    private static char[] _T;
    private static Vector<Entry> _alphabet;
    private static Vector<Entry>[] _Q;

    public static void findMatches(String T, String P){

        _P = P.toCharArray();
        _T = T.toCharArray();
        _alphabet = new Vector<>();

        for(char p : P.toCharArray()){
            Entry entry = new Entry(p);
            if(!_alphabet.contains(entry)) {
                _alphabet.add(entry);
            }
        }
        constructAutomata();
        printStateTable();

    }

    private static void printStateTable(){
        System.out.println("Pattern: " + Arrays.toString(_P) +"\n");
        System.out.println("Automaton:\n---------");
        int i = 0;
        System.out.print("States: ");
        for(Entry e : _alphabet)
            System.out.printf(" %2s", e._letter);
        System.out.println();
        System.out.print("         ");

        for(Entry e : _alphabet)
            System.out.print("---");

        System.out.println();
        for(Vector<Entry> V : _Q){
            System.out.print("State " + i++ + ": ");
            for(Entry E : V)
                System.out.printf("%2s ", E._k);
            System.out.println();
        }

        System.out.print("State " + i + ": ");
        for(Entry E : _Q[1])
            System.out.printf("%2s ", E._k);
        System.out.println();
    }

    private static void constructAutomata(){

        int m = _P.length; // pattern length
        _Q = new Vector[m]; //

        for(int i = 0; i < m; i++){
            _Q[i] = new Vector<>();
            for(int v = 0; v < _alphabet.size(); v++)
                _Q[i].add(new Entry(_alphabet.get(v)._letter));
        }
        for(int q = 0; q < m; q++){

            for(Entry a : _alphabet){

                int k = Math.min(m+1, q+2);
                char[] Aqx = new char[q+1];
                char[] Ak, AqXsuffix;
                System.arraycopy(_P, 0, Aqx, 0, q);
                Aqx[q] = a._letter;

                do {
                    k--;
                    Ak = Arrays.copyOfRange(_P, 0, k);
                    AqXsuffix = Arrays.copyOfRange(Aqx, q+1-k, q+1);
                }
                while(!Arrays.equals(Ak, AqXsuffix) );

                _Q[q].get(_Q[q].indexOf(a))._k = k;
            }
        }
    }

    private static class Entry{
        int _k;
        char _letter;

        Entry(char letter){
            _letter = letter;
        }

        @Override
        public boolean equals(Object a){
            try {
                Entry that = (Entry)a;
                if(that._letter == _letter)
                    return true;
            }
            catch(Exception e){
                return false;
            }
            return false;
        }
    }
}
