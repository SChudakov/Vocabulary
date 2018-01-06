package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.Collection;

/**
 * Created by Semen Chudakov on 14.12.2017.
 */
public class Word {
    @DatabaseField(id = true)
    private int wordID;
    @DatabaseField(columnName = "word")
    private String value;
    @DatabaseField(foreign = true)
    private WordClass wordClass;
    @ForeignCollectionField
    private Collection<WordCollection> wordCollections;

    public Word() {
    }

    public Word(String value) {
        this.value = value;
        this.wordID = value.hashCode();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word otherWord = (Word) obj;
            return this.value.equals(otherWord.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
