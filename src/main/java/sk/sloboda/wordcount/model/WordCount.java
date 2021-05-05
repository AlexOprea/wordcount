package sk.sloboda.wordcount.model;

import java.text.DecimalFormat;

public class WordCount {

    private int count = 0;
    private int uniqueCount = 0;
    private Double averageLength = 0.0;

    public WordCount() {
    }

    public WordCount(int count, int uniqueCount) {
        this.count = count;
        this.uniqueCount = uniqueCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUniqueCount() {
        return uniqueCount;
    }

    public void setUniqueCount(int uniqueCount) {
        this.uniqueCount = uniqueCount;
    }

    public Double getAverageLength() {
        return averageLength;
    }

    public void setAndRoundAverageLength(Double averageLength) {
        if (averageLength != null) {
            DecimalFormat formatter = new DecimalFormat("#.##");
            this.averageLength = Double.valueOf(formatter.format(averageLength));
        } else {
            this.averageLength = averageLength;
        }

    }
}
