package fm_index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Kelvinzero on 2/7/2017.
 */
public class StringSuffix {


    public static char[][] firstLastTable(String[] text){

        int r = 0;
        ArrayList<String> bwt = BWT(text);
        char[][] flTable = new char[bwt.size()][2];

        for(String row : bwt){
            flTable[r][0] = row.charAt(0);
            flTable[r][1] = row.charAt(row.length()-1);
            r++;
        }
        return flTable;
    }

    public static String[] SAfromBWT(char[] BWT, int[] Cx){

        int n = BWT.length;
        String[] suffixArray = new String[n];
        for(int i = 0; i < suffixArray.length; i++)
            suffixArray[i] = "";

        for(int c = 0; c < BWT.length; c++){
            for(int r = 0; r < suffixArray.length; r++){
                suffixArray[r] += BWT[r];
            }
            Arrays.sort(suffixArray);
        }
        return suffixArray;
    }

    private static char charOneLess(char[][] FL, char ch){
        char[] chary = new char[FL.length];
        int i = 0;
        for(char[] charray : FL){
            chary[i] = charray[1];
            i++;
        }
        Arrays.sort(chary);

        for(i = chary.length-1; i >= 0; i--){
            if(chary[i] < ch)
                return chary[i];
        }
        return ch;
    }

    private static int Cx(char Qi, int[] Cx){
        switch (Qi){
            case '$':
                return Cx[0];
            case 'A':
                return Cx[1];
            case 'C':
                return Cx[2];
            case 'G':
                return Cx[3];
            case 'T':
                return Cx[4];
        }
        return -1;
    }

    public static ArrayList<String> BWT(String text){

        int n = text.length();
        int m = n+1;
        // char[][] table = new char[m][m];
        char[] lastRow = new char[m];
        char[] nextRow;
        ArrayList<String> table = new ArrayList<>();

        // first row, prepend '$' to text
        lastRow[0] = '$';
        for(int i = 1; i < m; i++)
            lastRow[i] = text.charAt(i-1);

        table.add(new String(lastRow));

        for(int i = 1; i < m; i++){
            nextRow = new char[m];
            nextRow[0] = lastRow[m-1];
            for(int k = 1; k < m; k++)
                nextRow[k] = lastRow[k-1];
            table.add(new String(nextRow));
            lastRow =nextRow;

        }
        lexographicBubbleSort(table);
        return table;
    }

    public static ArrayList<String> BWT(String[] text) {

        StringBuilder readCat = new StringBuilder();
        Arrays.stream(text).forEach(E -> readCat.append(E + '$'));
        ArrayList<String> suffixarray = new ArrayList<>();
        int m = readCat.length();
        suffixarray.add(readCat.toString());

        for(int i = 1; i < readCat.length(); i++){
            String row = suffixarray.get(i-1).charAt(m-1) + "";
            row += suffixarray.get(i-1).substring(0, m-1);
            suffixarray.add(row);
        }
        lexographicBubbleSort(suffixarray);
        return suffixarray;
    }

    private static int lexographicCompare(String a, String b){
        int dif = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) < b.charAt(i))
                return -1;
            if(a.charAt(i) > b.charAt(i))
                return 1;
        }
        return dif;
    }

    public static void lexographicBubbleSort(ArrayList<String> table){
        String runner;

        for(int i = 1; i < table.size(); i++){
            int k = i-1;
            runner = table.get(i);

            while(k >= 0 && lexographicCompare(runner, table.get(k)) < 0){
                table.set(k+1, table.get(k));
                table.set(k, runner);
                k--;
            }
        }
    }

    public static int[] Cx(char[][] flTable){

        int[] Cx = new int[5];
        int symbolNumber = 1;

        Cx[0] = 0;
        char symbol = flTable[0][0];

        for(int i = 1; i < flTable.length; i++){
            if(flTable[i][0] != symbol){
                symbol = flTable[i][0];
                Cx[symbolNumber] = i;
                symbolNumber++;
            }
        }

        return Cx;
    }

    public static String[] randomGenome(int count, int min, int max){

        Random rand = new Random(255);
        String[] genome = new String[count];
        StringBuilder read;

        for(int i = 0; i < count; i++){

            int readLength = min + (max-min == 0 ? 0 : Math.abs(rand.nextInt() % (max-min)));
            read = new StringBuilder();

            for(int j = 0; j < readLength; j++){

                switch (Math.abs(rand.nextInt()%4)){
                    case 0:
                        read.append('A');
                        break;
                    case 1:
                        read.append('T');
                        break;
                    case 2:
                        read.append('G');
                        break;
                    case 3:
                        read.append('C');
                        break;
                }
            }
            genome[i] = read.toString();
        }
        return genome;
    }

    private class Node{
        String _sequence;
    }
    private class Edge{
        Node _B;
        Node _E;
        String _E_label;
        String _B_label;
    }
}
