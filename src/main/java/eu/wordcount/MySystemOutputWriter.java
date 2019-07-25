package eu.wordcount;

import java.io.PrintStream;

public class MySystemOutputWriter implements MyWriter{

    private PrintStream out;

    public MySystemOutputWriter(PrintStream out) {
        this.out = out;
    }

    @Override
    public void write(String output) {
        out.print(output);
    }
}
