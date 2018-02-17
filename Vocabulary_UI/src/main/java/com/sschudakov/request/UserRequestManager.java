package com.sschudakov.request;

import java.util.HashMap;
import java.util.List;

public class UserRequestManager {

    private UserRequestValidator userRequestValidator;

    public UserRequestManager() {
        this.userRequestValidator = new UserRequestValidator();
    }

    //get requests
    public List<String> getLanguages() {
        throw new UnsupportedOperationException();
    }

    public List<String> getCollections() {
        throw new UnsupportedOperationException();
    }

    public List<String> getClasses() {
        throw new UnsupportedOperationException();
    }

    public List<String> getMeaningsByWord(String word, String wordLanguage, String meaningsLanguage) {
        throw new UnsupportedOperationException();
    }

    public String getClassByWord(String word, String language) {
        throw new UnsupportedOperationException();
    }

    public List<String> getWordsByLanguageName(String language) {
        throw new UnsupportedOperationException();
    }

    public HashMap<String, Boolean> getCollectionsByWord(String word, String language) {
        throw new UnsupportedOperationException();
    }

    public List<String> getWordsByCollectionName(String collection) {
        throw new UnsupportedOperationException();
    }

    //change requests
    public void addWord() {
        throw new UnsupportedOperationException();
    }

    public void addCollection() {
        throw new UnsupportedOperationException();
    }

    public void addLanguage() {
        throw new UnsupportedOperationException();
    }

    public void deleteWord() {
        throw new UnsupportedOperationException();
    }

    public void deleteCollection() {
        throw new UnsupportedOperationException();
    }

    public void deleteLanguage() {
        throw new UnsupportedOperationException();
    }

    public void addMeaningToWord(String word, String language, String meaning, String meaningLanguage) {
        throw new UnsupportedOperationException();
    }

    public void addWordToCollection(String word, String language, String collection) {
        throw new UnsupportedOperationException();
    }

    public void changeWordClassTo(String word, String language, String className) {
        throw new UnsupportedOperationException();
    }

    //check requests
    public boolean wordExists(String word, String language) {
        throw new UnsupportedOperationException();
    }

    public boolean collectionExists(String collection) {
        throw new UnsupportedOperationException();
    }

    public boolean languageExists(String language) {
        throw new UnsupportedOperationException();
    }
}
