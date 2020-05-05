package com.troweprice.interview.handler;

import com.google.common.collect.ImmutableList;
import com.troweprice.interview.model.WordProcessorResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordProcessor {
    
    private static final Logger LOG = Logger.getLogger(WordProcessor.class.getName());
    private static final String ALLOWED_CHARACTERS_REGEX = "[^A-Za-z-']";
    private static final String VALID_WORD_REGEX = "^[A-Za-z][A-Za-z-]*$";
    private static final String APOSTROPHE_POSSESSION_REGEX = "'\\s+";
    private static final Pattern APOSTROPHE_CONTRACTION_STRING = Pattern.compile("'([a-z]+)");
    
    public WordProcessorResponse findWord(final String sentence){
        return findWord(sentence, sortLengthDescending);
    }
    
    public WordProcessorResponse findShortestWord(final String sentence){
        return findWord(sentence, sortLengthAscending);
    }
    
    private WordProcessorResponse findWord(final String sentence, final BinaryOperator<Entry<Integer, List<String>>> sortingMechanism){
        if (sentence == null || sentence.isEmpty()){
            LOG.warning("Null string was passed in, returning empty response");
            return new WordProcessorResponse(0, ImmutableList.of());
        }
        
        final String removedSpecialCharacters = sentence
            .replaceAll(ALLOWED_CHARACTERS_REGEX, " ")
            .replaceAll(APOSTROPHE_POSSESSION_REGEX," ");
        final Optional<Entry<Integer, List<String>>> entryOpt = Arrays.stream(removedSpecialCharacters.split("\\s"))
            .map(s -> s.trim())
            .map(this::replaceContraction)
            .filter(word -> word.matches(VALID_WORD_REGEX))
            .collect(Collectors.groupingBy(s -> s.length(), Collectors.toList()))
            .entrySet().stream()
            .reduce(sortingMechanism);
        final Entry<Integer, List<String>> entry = entryOpt.get();
        return new WordProcessorResponse(entry.getKey(), entry.getValue());
    }
    
    private String replaceContraction(final String word){
        final Matcher matcher = APOSTROPHE_CONTRACTION_STRING.matcher(word);
        if (matcher.find()){
            final String replacement = matcher.group(1);
            return word.replaceAll(APOSTROPHE_CONTRACTION_STRING.pattern(), replacement);
        }
        return word;
    }
    
    private BinaryOperator<Entry<Integer, List<String>>> sortLengthDescending = (e1, e2) -> e1.getKey() > e2.getKey() ? e1 : e2;
    
    private BinaryOperator<Entry<Integer, List<String>>> sortLengthAscending = (e1, e2) -> e1.getKey() < e2.getKey() ? e1 : e2;

}
