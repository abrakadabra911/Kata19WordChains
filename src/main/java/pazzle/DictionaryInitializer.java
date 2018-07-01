package pazzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DictionaryInitializer {
    private HashSet<String> dictionary;
    private String firstWord;
    private String lastWord;

    private void initialize (String start, String end) throws IOException{
        if(start.length() != end.length()) {
            System.out.println("Prosze wprowadzić 2 słowa (argumenty) o takiej samej długości");
            System.exit(1);
        }

        // copying words to dictionary (same length words)
        File database = new File(getClass().getClassLoader().getResource("dictionary/wordlist.txt").getFile());
        BufferedReader scan = new BufferedReader(new FileReader(database),40 * 1024);
        String line;
        firstWord = start;
        lastWord = end;
        int length = start.length();
        dictionary = new HashSet<String>(); //the dictionary (1 word per line)
        while((line = scan.readLine()) != null) {
            if(line.length() == length) {
                dictionary.add(line);
            }
        }
        scan.close();

        // checking if dictionary contains first and last word
        if(!dictionary.contains(firstWord) || !dictionary.contains(lastWord)) {
            System.out.println("Prosze wprowadzić 2 słowa z listy wordlist.txt");
            System.exit(1);
        }
    }

    public static void main(String[] args)throws Exception  {
        DictionaryInitializer dictionaryInitializer = new DictionaryInitializer();
        WordChainAnalyzer wordChainAnalyzer = new WordChainAnalyzer();

        String first = "coly";
        String last = "code";
      //  pazzleInitializer.firstWord = args[0];
      //  pazzleInitializer.lastWord = args[1];

        // forward timing
        long startTime = System.nanoTime();
        dictionaryInitializer.initialize(first, last);
        List<String> resultChain = wordChainAnalyzer.analyzeChain(
                dictionaryInitializer.firstWord,
                dictionaryInitializer.lastWord,
                dictionaryInitializer.dictionary);
        for (String word : resultChain) {
            System.out.println(word);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  // get milliseconds
        System.out.println("from first to second word Calculation of chain takes: " + duration + " milliseconds");

        // backward timing
        startTime = System.nanoTime();
        dictionaryInitializer.initialize(last, first);
        resultChain = wordChainAnalyzer.analyzeChain(
                dictionaryInitializer.firstWord,
                dictionaryInitializer.lastWord,
                dictionaryInitializer.dictionary);
        for (String word : resultChain) {
            System.out.println(word);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;  // get milliseconds
        System.out.println("from second to first word Calculation of chain takes: " + duration + " milliseconds");
    }
}
