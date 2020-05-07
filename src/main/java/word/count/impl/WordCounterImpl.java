package word.count.impl;

import java.util.Collection;

import text.split.TextSplitter;
import word.count.WordCounter;
import word.match.WordMatcher;

public class WordCounterImpl implements WordCounter {

    private final Collection<WordMatcher> wordMatchers;
    private final TextSplitter textSplitter;

    public WordCounterImpl(Collection<WordMatcher> wordMatchers, TextSplitter textSplitter) {
        this.wordMatchers = wordMatchers;
        this.textSplitter = textSplitter;
    }

    @Override
    public long count(String text) {
        if (text == null) {
            return 0;
        }

        return textSplitter.split(text).stream()
                .filter(this::matchesAllMatchers)
                .count();
    }

    private boolean matchesAllMatchers(String word) {
        return wordMatchers.stream()
                .allMatch(matcher -> matcher.match(word));
    }
}
