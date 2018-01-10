package com.sschudakov.words;

import com.sschudakov.entity.Word;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class WordsCollectionsReaderTest {

    @Test
    public void readWordsCollections() {
        int numOfWords = 0;
        for (HashMap<Word, List<Word>> wordListHashMap : WordsCollectionsReader.readWordsCollections()) {
            numOfWords += wordListHashMap.keySet().size();
            for (Word word : wordListHashMap.keySet()) {
                System.out.println("word: " + word + " meanings" + wordListHashMap.get(word));
            }
        }

        System.out.println("num of words: " + numOfWords);
    }
}