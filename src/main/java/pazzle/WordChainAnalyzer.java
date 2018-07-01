package pazzle;

import java.util.*;

public class WordChainAnalyzer {

    public List<String> analyzeChain(String firstWord, String lastWord, Set<String> dictionary) {
        List<String> candidates = new LinkedList<String>();
        candidates.add(firstWord);
        Map<String, String> chainMap = new HashMap<String, String>();
        chainMap.put(firstWord, null);

        int count = 0;
        while (candidates.size() > 0) {
            if (count++ > 15000) throw new RuntimeException("no result: too long calculation");
            String previos = candidates.remove(0);
            for(String newWord: dictionary) {
                if (!chainMap.containsKey(newWord) && isSimilar(previos, newWord)) {
                    chainMap.put(newWord, previos);
                    if (lastWord.equals(newWord)) return formatWordChain(lastWord, chainMap);
                    candidates.add(newWord);
                }
            }
        }
        return new ArrayList<String>();
    }

    private List<String> formatWordChain(String lastWord, Map<String, String> chainMap) {
        List<String> result = new ArrayList<String>();
        result.add(lastWord);
        lastWord = chainMap.get(lastWord);
        while (lastWord != null) {
            result.add(0,lastWord);
            lastWord = chainMap.get(lastWord);
        }
        return result;
    }

    private boolean isSimilar (String xx, String yy){
        char[] x = xx.toCharArray();
        char[] y = yy.toCharArray();
        int counter = 0;
        for(int i = 0; i < x.length; i++) {
            if(x[i] != y[i]) counter++;
        }
        if(counter == 1) return true;
        return false;
    }
}
