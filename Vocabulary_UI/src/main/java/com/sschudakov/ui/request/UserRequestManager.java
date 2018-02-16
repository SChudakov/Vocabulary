package com.sschudakov.ui.request;

import java.util.HashMap;
import java.util.List;

public class UserRequestManager {

    private UserRequestValidator userRequestValidator;

    public UserRequestManager() {
        this.userRequestValidator = new UserRequestValidator();
    }

    //get requests
    public List<String> getLanguages() {
        return null;
    }

    public List<String> getCollections() {
        return null;
    }

    public List<String> getClasses() {
        return null;
    }

    public List<String> getMeaningsByWord(String word, String wordLanguage, String meaningsLanguage) {
        return null;
    }

    public String getClassByWord(String word, String language) {
        return null;
    }

    public List<String> getWordsByLanguageName(String language) {
        return null;
    }

    public HashMap<String, Boolean> getCollectionsByWord(String word, String language) {
        return null;
    }

    public List<String> getWordsByCollectionName(String collection) {
        return null;
    }

    //change requests
    public void addWord() {}

    public void addCollection() {}

    public void addLanguage() {}

    public void deleteWord() {}

    public void deleteCollection() {}

    public void deleteLanguage() {}

    public void addMeaningToWord(String word, String language, String meaning, String meaningLanguage) {};

    public void addWordToCollection(String word, String language, String collection) {};

    public void changeWordClassTo(String word, String language, String className) {};

    //check requests
    public boolean wordExists(String word, String language) {
        return false;
    }

    public boolean collectionExists(String collection) {
        return false;
    }

    public boolean languageExists(String language) {
        return false;
    }
}
