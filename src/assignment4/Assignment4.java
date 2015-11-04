package assignment4;

import java.io.*;
import java.util.*;

/**
 * Assignment4
 * @author Jacob Jones
 * 11/1/2015 
 */
public class Assignment4 {

    long WordsCorr = 0;
    long WordsIncorr = 0;
    long correctComp = 0;
    long incorrectComp = 0;
    MyLinkedList[] dictionaryArr = new MyLinkedList[26];

    /**
     * @param args
     */
    public static void main(String[] args) {
        Assignment4 s = new Assignment4();
        s.readfile();
        s.oliver();
        s.print();
    }

    /**
     *
     * Opens the file and ensures that the the ostr will be filled with the next
     * word from oliver.txt. It will lowercase the word, and send it to the containsCount
     * method where it will be checked for in the list and comparisons will be counted.
     * @return ostr.
     */
    public String oliver() {
        String ostr = "";
        try {
            FileInputStream oli = new FileInputStream(new File("oliver.txt"));
            char let;
            ostr = ""; //word to be processed
            int n = 0;
            int[] count = new int[0];
            while ((n = oli.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    ostr += Character.toLowerCase(let);
                }
                if ((Character.isWhitespace(let) || let == '-') && !ostr.isEmpty()) {
                    if (dictionaryArr[ostr.charAt(0) - 97].containsCount(ostr)[0] == 1) {
                        WordsCorr++;
                        correctComp += dictionaryArr[ostr.charAt(0) - 97].containsCount(ostr)[1];
                    } else {
                        WordsIncorr++;
                        incorrectComp += dictionaryArr[ostr.charAt(0) - 97].containsCount(ostr)[1] - 1;
                    }

                    ostr = "";
                }
            }
            oli.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return ostr;
    }

    /**
     *
     * Opens the dictionary file and adds all of the words to the correct linkedList 
     * in an array.
     * It is formatted to all lower case for searching.
     * @return an array of linkedLists
     */
    public Object[] readfile() { //reads in the file to be read
        File f = new File("random_dictionary.txt");
        String word;

        for (int i = 0; i < dictionaryArr.length; i++) {
            dictionaryArr[i] = new MyLinkedList<>();
        }

        try {
            Scanner inf = new Scanner(f);
            while (inf.hasNext()) {
                word = inf.next().toLowerCase();
                dictionaryArr[word.charAt(0) - 97].addLast(word);

            }

            //while
            inf.close();

        }//try
        catch (IOException e) {
            e.printStackTrace();
        }//catch
        return dictionaryArr;
    }//readfile

    /**
     * prints out all variables with labels.
     */
    public void print() {
        System.out.println("Number of correct words: " + WordsCorr);
        System.out.println("Number of incorrect words: " + WordsIncorr);
        System.out.println("Total comparisons of correct words: " + correctComp);
        System.out.println("Total number of incorrect word comparisons: " + incorrectComp);
        System.out.println("Average number of comparisons for correct words: " + correctComp / WordsCorr);
        System.out.println("Average number of comparisons for incorrect words: " + incorrectComp / WordsIncorr);
    }

}

/**
 * OUTPUTS
 * run:
Number of correct words: 937492
Number of incorrect words: 54648
Total comparisons of correct words: 3336600696
Total number of incorrect word comparisons: 403377564
Average number of comparisons for correct words: 3559
Average number of comparisons for incorrect words: 7381
BUILD SUCCESSFUL (total time: 59 seconds)
 */
