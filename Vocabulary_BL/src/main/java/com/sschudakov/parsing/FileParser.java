package com.sschudakov.parsing;

import com.sschudakov.entity.Word;
import com.sschudakov.logging.LoggersManager;

import java.io.*;
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
    private static final int WORD_POSITION = 0;
    private static final int MEANINGS_POSITION = 1;
    private static final String REGULAR_EXPRESSION_FOR_PARSING_MEANINGS = ",|/|\\\\";//[^\w]

    public static HashMap<Word, List<Word>> parse(String path) {
        return parse(new File(path));
    }

    public static HashMap<Word, List<Word>> parse(File file) {

        HashMap<Word, List<Word>> result = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), Charset.defaultCharset()/*forName("windows-1251")*/))) {

            String line;
            Word word;
            List<Word> meanings;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                try {
                    System.out.println(line);

                    String[] wordAndMeanings = line.split(WORD_FROM_MEANING_SEPARATOR);

                    ensureCorrectSeparation(wordAndMeanings);

                    word = parseWord(wordAndMeanings[WORD_POSITION]);
                    meanings = parseMeanings(wordAndMeanings[MEANINGS_POSITION]);

                    LoggersManager.getParsingLogger().trace("parsed word: " + word + " with meanings: " + meanings);

                    result.put(
                            word,
                            meanings
                    );
                } catch (IllegalArgumentException e) {
                    LoggersManager.getParsingLogger().error(e.getMessage() + " in " + file.getName() + " line: " + i);
//                    e.printStackTrace();
                }
                i++;
            }
        } catch (IOException e) {
            LoggersManager.getParsingLogger().error(e);
//            e.printStackTrace();
        }
        return result;
    }


    private static List<Word> parseMeanings(String line) {
        System.out.println("meanings line: " + line);
        List<Word> result = new ArrayList<>();
        String[] meanings = line.split(REGULAR_EXPRESSION_FOR_PARSING_MEANINGS);
        System.out.println("meanings array: " + Arrays.toString(meanings));
        try {
            for (String meaning : meanings) {
                result.add(parseWord(meaning));
            }
        } catch (Exception e) {
            LoggersManager.getParsingLogger().error(e);
//            e.printStackTrace();
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
        if (word == null) {
            throw new IllegalArgumentException("given word is null");
        }

        if (word.trim().isEmpty()) {
            throw new IllegalArgumentException("given word contains only white spaces");
        }
    }
}
