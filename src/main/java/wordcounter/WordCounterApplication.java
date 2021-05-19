package wordcounter;

import wordcounter.counter.WordCounter;
import wordcounter.reader.FileReader;

import java.util.Scanner;

public class WordCounterApplication {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter text: ");
        String text = myObj.nextLine();
        WordCounter wordCounter = new WordCounter();
        // TODO handle IOException
        wordCounter.setStopWords(new FileReader("stopwords.txt"));
        wordCounter.setText(text);
        System.out.println("Number of words: " + wordCounter.countWords());
    }
}
