import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public final class WordCounterResult {

    private final List<String> words;

    public WordCounterResult() {
        this.words = Collections.emptyList();
    }

    public WordCounterResult(final List<String> words) {
        this.words = words;
    }

    public WordCounterResult(final WordCounterResult wordCount) {
        this.words = new ArrayList<>(wordCount.words);
    }

    public long getCount() {
        return words.size();
    }

    public long getUniqueCount() {
        return new HashSet<>(words).size();
    }

    public void printMessage() {
        final String message = "Number of words: " + getCount() + ", unique: " + getUniqueCount();

        System.out.println(message);
    }

}