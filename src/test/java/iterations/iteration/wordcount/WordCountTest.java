package iterations.iteration.wordcount;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordCountTest {
	
	WordCount wordCount;
	
	@Before
	public void setUp() {
		wordCount = new WordCount();
	}

	@Test
	public void countWordsTest() {
		assertEquals(wordCount.countWords(""), 0);
		assertEquals(wordCount.countWords("blabla"), 1);
		assertEquals(wordCount.countWords("word1 word2 wordDrei"), 1);
		assertEquals(wordCount.countWords("aabb ccff dfgndjkf 4dfg48 rr$"), 3);
		assertEquals(wordCount.countWords("yyy5yyyyy"), 0);
		assertEquals(wordCount.countWords("  dfgdf df "), 2);
		assertEquals(wordCount.countWords("  dfgdf	dfgdfg	dfgdfgdfgfg "), 3);
		assertEquals(wordCount.countWords("  dfgdf	 	 	dfgdfg	dfgdfgdfgfg "), 3);
	}

	@Test
	public void countWordsWithStopWordsTest() {
		
		wordCount.addStopWords("df", "aabb", "dfgdf");
		
		assertEquals(wordCount.countWords(""), 0);
		assertEquals(wordCount.countWords("blabla"), 1);
		assertEquals(wordCount.countWords("word1 word2 wordDrei"), 1);
		assertEquals(wordCount.countWords("aabb ccff dfgndjkf 4dfg48 rr$"), 2);
		assertEquals(wordCount.countWords("yyy5yyyyy"), 0);
		assertEquals(wordCount.countWords("  dfgdf df "), 0);
		assertEquals(wordCount.countWords("  dfgdf	dfgdfg	dfgdfgdfgfg "), 2);
		assertEquals(wordCount.countWords("  dfgdf	 	 	dfgdfg	dfgdfgdfgfg "), 2);
	}
	
	@Test
	public void countWordWithDashAsSeparator() {
		wordCount.setWordsSeparator("[ ,\\t,\\-]");
		assertEquals(wordCount.countWords("Humpty-Dumpty-blabla  -  hallo-world"), 5);
		wordCount.setWordsSeparator("[ ,\\t,\\-,\\.]");
		assertEquals(wordCount.countWords("Humpty-Dumpty-blabla  -  hallo-world"), 5);
	}

}
