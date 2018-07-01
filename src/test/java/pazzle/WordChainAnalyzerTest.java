package pazzle;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.*;

public class WordChainAnalyzerTest {
    @Before
    public void setUp() throws Exception {

    }

    @Rule
    public TestRule timeout = new Timeout(10000);

    @Test
    public void analyzeChainForSampleDataShouldReturnCorrectChain() throws Exception {
        // arrange
        WordChainAnalyzer analyzer = new WordChainAnalyzer();
        HashSet<String> dictionary = new HashSet<String>();
        dictionary.add("coly");
        dictionary.add("toly");
        dictionary.add("cole");
        dictionary.add("code");

        //act
       /* PazzleInitializer pazzleInitializer = new PazzleInitializer();
        pazzleInitializer.initialize("coly", "code");
        dictionary = pazzleInitializer.dictionary;*/
        List<String> result = analyzer.analyzeChain("coly", "code", dictionary);
        List<String> target = new ArrayList<String>();
        target.add("coly");
        target.add("cole");
        target.add("code");


        //assert
        assertEquals(3, result.size());
        assertEquals(result, target);
    }


}
