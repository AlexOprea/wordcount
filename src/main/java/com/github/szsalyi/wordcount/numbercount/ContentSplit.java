package com.github.szsalyi.wordcount.numbercount;

import java.util.Arrays;
import java.util.List;

/**
 * @author Szabolcs Salyi
 * @since 2021-04-16
 */
public class ContentSplitImpl {
    private String regexDelimiter = "\\s+";

    public List<String> stringList(String inputContent) {
        String[] splittedContent = inputContent.split(regexDelimiter);

        return Arrays.asList(splittedContent);
    }
}
