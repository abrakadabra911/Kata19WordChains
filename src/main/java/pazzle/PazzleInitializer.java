package pazzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PazzleInitializer {
    public HashSet<String> dictionary;
    private String firstWord;
    private String lastWord;

    public void initialize (String start, String end) throws IOException{
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
        PazzleInitializer pazzleInitializer = new PazzleInitializer();
        WordChainAnalyzer wordChainAnalyzer = new WordChainAnalyzer();

       // pazzleSolution.initialize(args[0], args[1]);
        pazzleInitializer.initialize("coly", "code");

        // for checking result
        List<String> resultChain = wordChainAnalyzer.analyzeChain(pazzleInitializer.firstWord, pazzleInitializer.lastWord, pazzleInitializer.dictionary);
        for (String word : resultChain) {
            System.out.println(word);
        }
    }
}
