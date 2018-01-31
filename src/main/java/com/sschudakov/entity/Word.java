package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    /*@ForeignCollectionField(columnName = "collections")*/
    private Collection<WordCollection> wordCollections;
    /*@ForeignCollectionField(columnName = "meanings")*/
    private Collection<Word> meanings;

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

    public Collection<Word> getMeanings() {
        return meanings;
    }

    public void setMeanings(Collection<Word> meanings) {
        this.meanings = meanings;
    }


    public Word() {
    }

    public Word(String value) {
        this.value = value;
        this.wordID = value.hashCode();
        this.wordCollections = new ArrayList<>();
    }

    public Word(String value, String wordClass) {
        this.value = value;
        this.wordClass = new WordClass(wordClass);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.wordID)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word casted = (Word) obj;
            return new EqualsBuilder()
                    .append(this.wordID, casted.getWordID())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(this.wordID)
                .append(this.value)
                .append(this.wordClass)
                .append(this.wordCollections)
                .toString();
    }
}
