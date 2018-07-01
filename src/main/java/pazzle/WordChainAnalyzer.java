package pazzle;

import java.util.*;

public class WordChainAnalyzer {

    public List<String> analyzeChain(String firstWord, String lastWord, Set<String> dictionary) {
        List<String> candidatesQueue = new LinkedList<String>();
        candidatesQueue.add(firstWord);
        Map<String, String> chainMap = new HashMap<String, String>();
        chainMap.put(firstWord, null);

        int count = 0;
        while (candidatesQueue.size() > 0) {
            if (count++ > 15000) throw new RuntimeException("calculation stopped: too long time");
            String previous = candidatesQueue.remove(0);
            for(String newWord: dictionary) {
                if (isSimilar(previous, newWord) && !chainMap.containsKey(newWord)) {
                    chainMap.put(newWord, previous);
                    if (lastWord.equals(newWord)) {
                        return convertMap2ChainList(lastWord, chainMap);
                    }
                    candidatesQueue.add(newWord);
                }
            }
        }
        return new ArrayList<String>();
    }

    private List<String> convertMap2ChainList(String lastWord, Map<String, String> chainMap) {
        List<String> result = new ArrayList<String>();
        result.add(lastWord);
        lastWord = chainMap.get(lastWord);
        while (lastWord != null) {
            result.add(0,lastWord);
            lastWord = chainMap.get(lastWord);
        }
        chainMap = new HashMap<String, String>(); //necessary for next calculation
        return result;
    }

    //check if there is only one letter of difference
    private boolean isSimilar (String xx, String yy){
        char[] x = xx.toCharArray();
        char[] y = yy.toCharArray();
        int counter = 0;
        for(int i = 0; i < x.length; i++) {
            if(x[i] != y[i]) counter++;
        }
        return counter == 1;
    }
}
