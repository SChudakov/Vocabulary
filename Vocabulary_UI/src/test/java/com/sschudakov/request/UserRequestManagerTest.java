package com.sschudakov.request;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Map;

public class UserRequestManagerTest {

    private UserRequestManager userRequestManager;

    @Before
    public void init() {
        userRequestManager = new UserRequestManager();
    }


    @Test
    public void getLanguages() throws SQLException {
        for (String s : this.userRequestManager.getLanguages()) {
            System.out.println(s);
        }
    }

    @Test
    public void getCollections() throws SQLException {
        for (String s : this.userRequestManager.getCollections()) {
            System.out.println(s);
        }
    }

    @Test
    public void getClasses() throws SQLException {
        for (String s : this.userRequestManager.getClasses()) {
            System.out.println(s);
        }
    }

    @Test
    public void getMeaningsByWord() throws SQLException {
        for (String s : this.userRequestManager.getMeaningsByWord("apple", "English", "English")) {
            System.out.println(s);
        }
    }

    @Test
    public void getClassByWord() {
    }

    @Test
    public void getWordsByLanguageName() {
    }

    @Test
    public void getCollectionsByWord() throws SQLException {
        Map<String, Boolean> map = this.userRequestManager.getCollectionsByWord("apple", "English");
        for (String collection : map.keySet()) {
            System.out.println(collection + " " + map.get(collection));
        }
    }

    @Test
    public void getWordsByCollectionName() throws SQLException {
        for (String kek : this.userRequestManager.getWordsByCollectionName("kek")) {
            System.out.println(kek);
        }
    }

    @Test
    public void createWord() {
    }

    @Test
    public void createCollection() {
    }

    @Test
    public void deleteWord() {
    }

    @Test
    public void deleteCollection() {
    }

    @Test
    public void addMeaningToWord() {
    }

    @Test
    public void addWordToCollection() {
    }

    @Test
    public void removeWordFromCollection() {
    }

    @Test
    public void changeWordClassTo() {
    }

    @Test
    public void wordExists() {
    }

    @Test
    public void collectionExists() {
    }

    @Test
    public void languageExists() {
    }

    @Test
    public void classExists() {
    }
}