package wordcount;

import wordcount.domain.WordCounter;
import wordcount.io.FileReader;
import wordcount.io.StopWordsReader;
import wordcount.io.UserInterActor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String NUMBER_OF_WORDS_MESSAGE = "Number of words:";
    public static final String ENTER_TEXT_MESSAGE = "Enter text: ";

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        StopWordsReader stopWordsReader = new StopWordsReader(fileReader);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {
            List<String> stopWords = stopWordsReader.read("/stopwords.txt");
            UserInterActor userInterActor = new UserInterActor(scanner, bufferedWriter);
            WordCounter wordCounter = new WordCounter(stopWords);

            String text = userInterActor.read(ENTER_TEXT_MESSAGE);
            long count = wordCounter.count(text);
            userInterActor.write(NUMBER_OF_WORDS_MESSAGE, count);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
