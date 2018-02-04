package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@DatabaseTable(tableName = "word collection relationship")
public class WordCollectionRelationship {
    @DatabaseField(columnName = "id", id = true)
    private int wordCollectionRelationshipID;
    @DatabaseField(columnName = "word", canBeNull = false, foreign = true)
    private Word word;
    @DatabaseField(columnName = "collection", canBeNull = false, foreign = true)
    private WordCollection wordCollection;


    public int getWordCollectionRelationshipID() {
        return wordCollectionRelationshipID;
    }

    public void setWordCollectionRelationshipID(int wordCollectionRelationshipID) {
        this.wordCollectionRelationshipID = wordCollectionRelationshipID;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public WordCollection getWordCollection() {
        return wordCollection;
    }

    public void setWordCollection(WordCollection wordCollection) {
        this.wordCollection = wordCollection;
    }

    public WordCollectionRelationship() {
    }

    public WordCollectionRelationship(Word word, WordCollection wordCollection) {
        this.word = word;
        this.wordCollection = wordCollection;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.word)
                .append(this.wordCollection)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordCollectionRelationship) {
            WordCollectionRelationship casted = (WordCollectionRelationship) obj;
            return new EqualsBuilder()
                    .append(this.word, casted.getWord())
                    .append(this.wordCollection, casted.getWordCollection())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(this.wordCollectionRelationshipID)
                .append(this.word)
                .append(this.wordCollection)
                .toString();
    }
}
