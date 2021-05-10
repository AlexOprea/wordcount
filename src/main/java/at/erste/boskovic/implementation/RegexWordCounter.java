package at.erste.boskovic.implementation;

import at.erste.boskovic.WordCounter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public final class RegexWordCounter implements WordCounter {

    private static final String WORD_DEFINITION_REGEX = "[A-Za-z]+";
    private static final String SPACE_BETWEEN_WORDS_DEFINITION_REGEX = "\\s+";
    private List<String> stopWords = new ArrayList<>();

    public RegexWordCounter() {
    }

    public RegexWordCounter(InputStream stopWordsInputStream) {
        Scanner myReader = new Scanner(stopWordsInputStream);

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            stopWords.add(line);
        }
    }

    @Override
    final public long countWords(String sentence) {
        if (isNull(sentence)){
            return 0;
        }

        String[] potentialWords = splitPotentialWords(sentence);
        return countWords(potentialWords);
    }

    private String[] splitPotentialWords(String sentence){
        return sentence.split(SPACE_BETWEEN_WORDS_DEFINITION_REGEX);
    }

    private long countWords(String[] potentialWords){
        return Stream.of(potentialWords).filter(this::isWord).count();
    }

    private boolean isWord(String potentialWord){
        return potentialWord.matches(WORD_DEFINITION_REGEX) && !isStopWord(potentialWord);
    }

    private boolean isStopWord(String potentialWord){
        return stopWords.stream().anyMatch(sw -> sw.equals(potentialWord));
    }
}
