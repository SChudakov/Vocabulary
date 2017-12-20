package com.sschudakov.parsing;

import com.sschudakov.bins.Word;
import com.sschudakov.words.WordsCollectionsReader;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class WordsCollectionsReaderTest {

    @Test
    public void readWordsCollectionsTest() {
        for (HashMap<Word, List<Word>> wordListHashMap : WordsCollectionsReader.readWordsCollections()) {
            for (Word word : wordListHashMap.keySet()) {
                System.out.println("word: " + word + " meanings" + wordListHashMap.get(word));
            }
        }
    }
}
