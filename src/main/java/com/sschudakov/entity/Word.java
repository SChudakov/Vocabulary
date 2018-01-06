package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Semen Chudakov on 14.12.2017.
 */
@DatabaseTable(tableName = "words")
public class Word {
    @DatabaseField(id = true, columnName = "id")
    private int wordID;
    @DatabaseField(columnName = "word", canBeNull = false)
    private String value;
    @DatabaseField(columnName = "word class", foreign = true, canBeNull = false)
    private WordClass wordClass;
    @ForeignCollectionField(columnName = "meanings")
    private Collection<WordCollection> wordCollections;

    public Word() {
    }

    public Word(String value) {
        this.value = value;
        this.wordID = value.hashCode();
        this.wordCollections = new ArrayList<>();
    }


    public int getWordID() {
        return wordID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public WordClass getWordClass() {
        return wordClass;
    }

    public void setWordClass(WordClass wordClass) {
        this.wordClass = wordClass;
    }

    public Collection<WordCollection> getWordCollections() {
        return wordCollections;
    }

    public void setWordCollections(Collection<WordCollection> wordCollections) {
        this.wordCollections = wordCollections;
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
