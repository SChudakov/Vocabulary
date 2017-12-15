package com.sschudakov.parsing;

import com.sschudakov.bins.Word;
import com.sschudakov.logging.LoggersManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Semen Chudakov on 14.12.2017.
 */
public class FileParser {

    private static final String WORD_FROM_MEANING_SEPARATOR = "-";
    private static final int WORD_POSITION = 0;
    private static final int MEANINGS_POSITION = 1;
    private static final String REGULAR_EXPRESSION_FOR_PARSIN_MEANINGS = "[^\\w]";

    public static HashMap<Word, List<Word>> parseFile(String path) {
        return parseFile(new File(path));
    }

    public static HashMap<Word, List<Word>> parseFile(File file) {

        HashMap<Word, List<Word>> result = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {

                    String[] wordAndMeanings = line.split(WORD_FROM_MEANING_SEPARATOR);

                    ensureCorrectSeparation(wordAndMeanings);

                    result.put(
                            parseWord(wordAndMeanings[WORD_POSITION]),
                            parseMeanings(wordAndMeanings[MEANINGS_POSITION])
                    );
                } catch (IllegalArgumentException e) {
                    LoggersManager.getParsingLogger().error(e);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            LoggersManager.getParsingLogger().error(e);
            e.printStackTrace();
        }
        return result;
    }


    private static List<Word> parseMeanings(String line) {
        List<Word> result = new ArrayList<>();
        String[] meanings = line.split(REGULAR_EXPRESSION_FOR_PARSIN_MEANINGS);
        try {
            for (String meaning : meanings) {
                result.add(parseWord(meaning));
            }
        } catch (Exception e) {
            LoggersManager.getParsingLogger().error(e);
            e.printStackTrace();
        }
        return result;
    }

    private static Word parseWord(String word) {
        word = word.trim();

        ensureWordIsCorrect(word);
        return new Word(word);
    }

    private static void ensureCorrectSeparation(String[] wordAndMeanings) {
        if (wordAndMeanings.length != 2) {
            throw new IllegalArgumentException("word and meanings array " +
                    Arrays.toString(wordAndMeanings) + " has not length 2");
        }
    }

    private static void ensureWordIsCorrect(String word) {

    }
}
