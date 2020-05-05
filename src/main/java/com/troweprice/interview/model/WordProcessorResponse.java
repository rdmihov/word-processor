package com.troweprice.interview.model;

import java.util.List;

public class WordProcessorResponse {
    
    private final int wordLength;
    
    private final List<String> words;
    
    public WordProcessorResponse(final int wordLength, final List<String> words){
        this.wordLength = wordLength;
        this.words = words;
    }

    public int getWordLength() {
        return wordLength;
    }

    public List<String> getWords() {
        return words;
    }
}
