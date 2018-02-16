package com.sschudakov.request;

import java.util.Collection;

public class UserRequestManager {

    private UserRequestValidator userRequestValidator;

    public UserRequestManager() {
        this.userRequestValidator = new UserRequestValidator();
    }

    public void updateOrSaveWordInformation(String wordValue, String wordClass,
                                            String language, Collection<String> meanings,
                                            Collection<String> colections, String meanigsLanguage) {
        throw new UnsupportedOperationException();
    }

    public void createCollection(String collectionsName) {
        throw new UnsupportedOperationException();
    }

    public void deleteCollection(String collecitonName) {
        throw new UnsupportedOperationException();
    }

    public void deleteWord(String word, String language) {
        throw new UnsupportedOperationException();
    }

    public void ensureWordExists(String word, String language) {
        this.userRequestValidator.ensureWordExists(word, language);
    }

    public void removeMeaning(String meaning, String language){
        throw new UnsupportedOperationException();
    }

}
