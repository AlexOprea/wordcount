import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
public class WordCounterTest {

    private static WordsCounter testClass;

    @BeforeClass
    public static void setup() {
        testClass = new WordsCounter();
    }

    @Test
    public void shouldCountWordsOnlyMatchingInputNoStopWords() {
        String input = "aaa bbb ccc ddd";
        List<String> stopWords = Collections.singletonList("");
        ResultModel result = testClass.countWords(input, stopWords);
        assertEquals(Integer.valueOf(4), result.getTotalWordsCount());
    }

    @Test
    public void shouldCountWordsIgnoreNumbersSpecialCharsSeparateLinesStopWords() {
        String input = "aaa bbb b2b ccć ąą 22 cc dd  ";
        List<String> stopWords = Arrays.asList("aaa", "dd");
        ResultModel result = testClass.countWords(input, stopWords);
        assertEquals(Integer.valueOf(2), result.getTotalWordsCount());
    }

    @Test
    public void shouldCountWordsIgnoreNumbersSpecialCharsOneLineStopWords() {
        String input = "aaa bbb b2b ccć ąą 22 cc dd  ";
        List<String> stopWords = Arrays.asList("aaa bbb", "dd");
        ResultModel result = testClass.countWords(input, stopWords);
        assertEquals(Integer.valueOf(1), result.getTotalWordsCount());
    }

    @Test
    public void shouldReturnZeroForNoInput() {
        String input = "";
        List<String> stopWords = Collections.singletonList("");
        ResultModel result = testClass.countWords(input, stopWords);
        assertEquals(Integer.valueOf(0), result.getTotalWordsCount());
    }
}
