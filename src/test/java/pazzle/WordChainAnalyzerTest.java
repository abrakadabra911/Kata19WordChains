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
    public TestRule timeout = new Timeout(1000);

    @Test
    public void analyzeChainForSampleDataShouldReturnCorrectChain() throws Exception {
        // arrange
        WordChainAnalyzer analyzer = new WordChainAnalyzer();
        HashSet<String> dictionary = new HashSet<String>();
        dictionary.add("doly");
        dictionary.add("cole"); //expected
        dictionary.add("code"); //expected
        dictionary.add("soly");
        dictionary.add("cony");
        dictionary.add("caly");
        dictionary.add("cold");
        dictionary.add("coly"); //expected

        //act
        List<String> result = analyzer.analyzeChain("coly", "code", dictionary);
        List<String> target = new ArrayList<String>();
        target.add("coly");
        target.add("cole");
        target.add("code");

        //assert
        // for(String x: result) System.out.println(x); // print result
        assertEquals(3, result.size());
        assertEquals(result, target);
    }
}
