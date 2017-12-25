package com.sschudakov.daos.bins;

public class Language {
    private int languageID;
    private String languageName;

    public Language(String languageName) {
        this.languageName = languageName;
        this.languageID = languageName.hashCode();
    }
}
