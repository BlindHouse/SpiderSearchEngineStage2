package FileStuff;

/**
 * Created by jackthebones on 21/05/15.
 */


import Estructuras.ArbolAVL;

import java.util.ArrayList;


public class WordCounter {

    public static ArbolAVL WordProcess(String PlainText) {

        // Text in
        String inText = PlainText;

        // Puts it into an array, and splits
        String[] wordlist = inText.replaceAll(",", "")
                .replaceAll("-", "").replaceAll("","").replaceAll("\"","")
                .replaceAll("\\.","").replaceAll("™","").replaceAll("\\)","")
                .replaceAll("\\(","").split("\\s+");


        // declare Arraylist for words
        ArrayList<String> wordEncounter = new ArrayList<String>();
        ArrayList<Integer> numberEncounter = new ArrayList<Integer>();

        // Checks number of encounters of words
        for (int i = 0; i < wordlist.length; i++) {
            String word = wordlist[i];

            // Make everything lowercase just for ease...
            word = word.toLowerCase();

            if (wordEncounter.contains(word)) {
                // Checks word encounter - return index of word
                int position = wordEncounter.indexOf(word);
                Integer number = numberEncounter.get(position);
                int number_int = number.intValue();
                number_int++;
                number = new Integer(number_int);
                numberEncounter.set(position, number);

                // Number of encounters - add 1;
            } else {
                wordEncounter.add(word);
                numberEncounter.add(new Integer(1));
            }
        }

        ArbolAVL arbol = new ArbolAVL();
        // Text out (the list of words)
        for (int i = 0; i < wordEncounter.size(); i++) { //AQUI SACO LAS VARIABLES PALABRA MAS CANTIDAD
            int num = numberEncounter.get(i);
            String str = wordEncounter.get(i);
            arbol.insert(num, str);
        }
        return arbol;
    }
}
