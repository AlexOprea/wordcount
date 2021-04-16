package com.github.szsalyi.wordcount.numbercount;

import com.github.szsalyi.wordcount.io.ConsoleUserInterface;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new ConsoleUserInterface();
        StopWords stopWords = new StopWords();
        WordProcessor wordProcessor = new WordProcessor(userInterface);

        userInterface.printResult(wordProcessor.count());
    }
}
