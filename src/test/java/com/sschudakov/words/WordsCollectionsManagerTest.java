package com.sschudakov.words;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordsCollectionsManagerTest {

    @Test
    public void readExistingCollections() {
        System.out.println(WordsCollectionsManager.readExistingCollections().toString());
    }
}