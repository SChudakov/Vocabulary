package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@DatabaseTable(tableName = "word meaning relationship")
public class WordMeaningRelationship {

    @DatabaseField(columnName = "id", id = true)
    private int wordMeaningRelationshipID;
    @DatabaseField(columnName = "word", canBeNull = false, foreign = true)
    private Word word;
    @DatabaseField(columnName = "meaning", canBeNull = false, foreign = true)
    private Word meaning;


    public int getWordMeaningRelationshipID() {
        return wordMeaningRelationshipID;
    }

    public Word getWord() {
        return word;
    }

    public Word getMeaning() {
        return meaning;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public void setMeaning(Word meaning) {
        this.meaning = meaning;
    }


    public WordMeaningRelationship() {
    }

    public WordMeaningRelationship(Word word, Word meaning) {
        this.word = word;
        this.meaning = meaning;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.wordMeaningRelationshipID)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordMeaningRelationship) {
            WordMeaningRelationship casted = (WordMeaningRelationship) obj;
            return new EqualsBuilder()
                    .append(this.wordMeaningRelationshipID, casted.getWordMeaningRelationshipID())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(this.wordMeaningRelationshipID)
                .append(this.word)
                .append(this.meaning)
                .toString();
    }
}
