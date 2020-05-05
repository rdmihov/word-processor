package com.troweprice.interview.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.common.collect.ImmutableList;
import com.troweprice.interview.model.WordProcessorResponse;
import org.junit.Before;
import org.junit.Test;

public class WordProcessorTest {
    
    private WordProcessor wordProcessor;
    
    @Before
    public void setUp() throws Exception {
        this.wordProcessor = new WordProcessor();
    }
    
    @Test
    public void whenASentenceIsPassedInThenCorrectlyIdentifyLongestWord(){
        final WordProcessorResponse response = wordProcessor.findWord("The cow jumped over the moon.");
        assertNotNull(response);
        assertEquals(6, response.getWordLength());
        assertEquals(ImmutableList.of("jumped"), response.getWords());
    }

    @Test
    public void whenNullIsPassedInThenReturnAppropriateResponse(){
        final WordProcessorResponse response = wordProcessor.findWord(null);
        assertNotNull(response);
        assertEquals(0, response.getWordLength());
        assertEquals(ImmutableList.of(), response.getWords());
    }

    @Test
    public void whenEmptyStringIsPassedInThenReturnAppropriateResponse(){
        final WordProcessorResponse response = wordProcessor.findWord("");
        assertNotNull(response);
        assertEquals(0, response.getWordLength());
        assertEquals(ImmutableList.of(), response.getWords());
    }

    @Test
    public void whenASingleWordIsPassedInThenCorrectlyIdentifyLongestWord(){
        final WordProcessorResponse response = wordProcessor.findWord("Approved.");
        assertNotNull(response);
        assertEquals(8, response.getWordLength());
        assertEquals(ImmutableList.of("Approved"), response.getWords());
    }

    @Test
    public void whenASpaceAfterAPeriodIsMissingThenCorrectlyIdentifyLongestWord(){
        final WordProcessorResponse response = wordProcessor.findWord("The cow jumped over the moon.The grass is green.");
        assertNotNull(response);
        assertEquals(6, response.getWordLength());
        assertEquals(ImmutableList.of("jumped"), response.getWords());
    }

    @Test
    public void whenMoreThanOneWordIsLongestThenReturnAllWords(){
        final WordProcessorResponse response = wordProcessor.findWord("The boy likes to play games.");
        assertNotNull(response);
        assertEquals(5, response.getWordLength());
        assertEquals(ImmutableList.of("likes", "games"), response.getWords());
    }

    @Test
    public void whenAContractionIsGivenThenCorrectlyIdentifyLongestWord(){
        final WordProcessorResponse response = wordProcessor.findWord("The man found he didn't need it.");
        assertNotNull(response);
        assertEquals(5, response.getWordLength());
        assertEquals(ImmutableList.of("found", "didnt"), response.getWords());
    }

    @Test
    public void whenAPossessionIsGivenThenCorrectlyIdentifyLongestWord(){
        final WordProcessorResponse response = wordProcessor.findWord("The students' grades were better than last year.");
        assertNotNull(response);
        assertEquals(8, response.getWordLength());
        assertEquals(ImmutableList.of("students"), response.getWords());
    }

    @Test
    public void whenACompoundIsGivenThenItIsCountedAsOneWord(){
        final WordProcessorResponse response = wordProcessor.findWord("He looked young for a forty-five-year-old.");
        assertNotNull(response);
        assertEquals(19, response.getWordLength());
        assertEquals(ImmutableList.of("forty-five-year-old"), response.getWords());
    }

    @Test
    public void whenACompoundWithNumbersIsGivenThenItIsIgnored(){
        final WordProcessorResponse response = wordProcessor.findWord("He looked young for a 45-year-old.");
        assertNotNull(response);
        assertEquals(6, response.getWordLength());
        assertEquals(ImmutableList.of("looked"), response.getWords());
    }

    @Test
    public void whenASentenceIsPassedInThenCorrectlyIdentifyShortestWord(){
        final WordProcessorResponse response = wordProcessor.findShortestWord("The roses were crimson.");
        assertNotNull(response);
        assertEquals(3, response.getWordLength());
        assertEquals(ImmutableList.of("The"), response.getWords());
    }

    @Test
    public void whenMoreThanOneWordIsShortestThenReturnAllWords(){
        final WordProcessorResponse response = wordProcessor.findShortestWord("The roads were full with cars and bikes.");
        assertNotNull(response);
        assertEquals(3, response.getWordLength());
        assertEquals(ImmutableList.of("The", "and"), response.getWords());
    }
}