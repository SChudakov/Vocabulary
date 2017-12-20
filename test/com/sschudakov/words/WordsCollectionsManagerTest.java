package com.sschudakov.words;

import org.junit.Test;

/**
 * Created by Semen Chudakov on 16.12.2017.
 */
public class WordsCollectionsManagerTest {

    @Test
    public void readExistingCollectionsTest(){
        System.out.println(WordsCollectionsManager.readExistingCollections().toString());
    }
}
