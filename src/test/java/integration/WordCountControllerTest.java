package integration;

import controller.WordCountController;
import io.FileBasedUI;
import io.UserInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.WordCountService;
import testUtil.MockIO;
import util.FileBasedStopWords;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCountControllerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void test_countWords_OneWord() {

        WordCountController controller = setUpController("ABC");

        controller.countWords();

        assertEquals("Number of words: 1", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test_countWords_DotSeparator() {

        WordCountController controller = setUpController("ABC .DEF");

        controller.countWords();

        assertEquals("Number of words: 1", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test_countWords_DigitAndDotSeparator() {

        WordCountController controller = setUpController("AB1C .DEF");

        controller.countWords();

        assertEquals("Number of words: 0", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test_countWords_WordWithDigit() {

        WordCountController controller = setUpController("AB1C def");

        controller.countWords();

        assertEquals("Number of words: 1", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test_countWords_StopWord() {

        WordCountController controller = setUpController("ABC the");

        controller.countWords();

        assertEquals("Number of words: 1", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test_countWords_AllStopWord() {

        WordCountController controller = setUpController("a the");

        controller.countWords();

        assertEquals("Number of words: 0", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test_countWords_FileBased() {

        WordCountController controller = setUpController_withFileBasedUI("src/test/java/resources/validWords.txt");

        controller.countWords();

        assertEquals("Number of words: 4", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test_countWords_FileBased_AllStopWords() {

        WordCountController controller = setUpController_withFileBasedUI("src/test/java/resources/allStopWords.txt");

        controller.countWords();

        assertEquals("Number of words: 0", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test_countWords_FileBased_validAndStopWords() {

        WordCountController controller = setUpController_withFileBasedUI("src/test/java/resources/validAndStopWords.txt");

        controller.countWords();

        assertEquals("Number of words: 5", outputStreamCaptor.toString().trim());
    }


    private WordCountController setUpController(String input) {

        final MockIO io = new MockIO();
        io.setInputString(input);

        final FileBasedStopWords stopWords = new FileBasedStopWords();
        final WordCountService service = new WordCountService(stopWords);
        final WordCountController controller = new WordCountController(io, service);

        return controller;

    }

    private WordCountController setUpController_withFileBasedUI(String fileName) {

        final FileBasedUI fileBasedUI = new FileBasedUI(fileName);

        final FileBasedStopWords stopWords = new FileBasedStopWords();
        final WordCountService service = new WordCountService(stopWords);
        final WordCountController controller = new WordCountController(fileBasedUI, service);

        return controller;

    }

}
