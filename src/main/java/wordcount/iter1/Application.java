package wordcount.iter1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;

public class Application {
    public static void main(String[] args) {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {

            WordCounterApplication wordCounterApplication = new WordCounterApplication(
                    new ConsoleInteractor(in, out),
                    new ClasspathStopwordsProvider("stopwords.txt")
            );

            wordCounterApplication.run();

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
