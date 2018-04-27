package com.sschudakov.desktop.words.parsing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Semen Chudakov on 14.12.2017.
 */
public class FileParser {

    private static final String WORD_FROM_MEANING_SEPARATOR = " – | - ";
    private static final String MEANING_REGULAR_EXPRESSION = ",|/|\\\\";
    private static final int WORD_POSITION = 0;
    private static final int MEANINGS_POSITION = 1;

    private static Logger logger = LogManager.getLogger(FileParser.class);

    public HashMap<String, List<String>> parse(String path) {
        return parse(new File(path));
    }

    public HashMap<String, List<String>> parse(File file) {

        HashMap<String, List<String>> result = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), Charset.defaultCharset()/*forName("windows-1251")*/))) {

            String line;

            String word;
            List<String> meanings;

            int i = 0;
            while ((line = reader.readLine()) != null) {
                try {

                    String[] wordAndMeanings = line.split(WORD_FROM_MEANING_SEPARATOR);

                    ensureCorrectSeparation(wordAndMeanings);

                    word = parseWord(wordAndMeanings[WORD_POSITION]);
                    meanings = parseMeanings(wordAndMeanings[MEANINGS_POSITION]);

                    logger.trace("parsed word: " + word + " with meanings: " + meanings);

                    result.put(
                            word,
                            meanings
                    );
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage() + " in " + file.getName() + " line: " + i);
                }
                i++;
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }


    private List<String> parseMeanings(String line) {


        List<String> result = new ArrayList<>();

        String[] meanings = line.split(MEANING_REGULAR_EXPRESSION);

        try {
            for (String meaning : meanings) {
                result.add(parseWord(meaning));
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    private String parseWord(String word) {
        ensureWordIsCorrect(word);
        word = word.trim();
        return word;
    }

    private void ensureCorrectSeparation(String[] wordAndMeanings) {
        if (wordAndMeanings.length != 2) {
            throw new IllegalArgumentException("word and meanings array " +
                    Arrays.toString(wordAndMeanings) + " has not length 2");
        }
    }

    private void ensureWordIsCorrect(String word) {
        if (word == null) {
            throw new IllegalArgumentException("given word is null");
        }

        if (word.trim().isEmpty()) {
            throw new IllegalArgumentException("given word contains only white spaces");
        }
    }
}
