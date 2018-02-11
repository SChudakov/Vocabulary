package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@DatabaseTable(tableName = "word_meaning_relationships")
public class WordMeaningRelationship {

    public static final String ID_COLUMN_NAME = "meaning_relationship_id";
    public static final String WORD_COLUMN_NAME = "relationship_word";
    public static final String MEANING_COLUMN_NAME = "relationship_meaning";

    @DatabaseField(generatedId = true, columnName = ID_COLUMN_NAME)
    private int wordMeaningRelationshipID;
    @DatabaseField(columnName = WORD_COLUMN_NAME, canBeNull = false, foreign = true)
    private Word word;
    @DatabaseField(columnName = MEANING_COLUMN_NAME, canBeNull = false, foreign = true)
    private Word meaning;


    public int getWordMeaningRelationshipID() {
        return wordMeaningRelationshipID;
    }

    public void setWordMeaningRelationshipID(int wordMeaningRelationshipID) {
        this.wordMeaningRelationshipID = wordMeaningRelationshipID;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getMeaning() {
        return meaning;
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
                .append(this.word)
                .append(this.meaning)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordMeaningRelationship) {
            WordMeaningRelationship casted = (WordMeaningRelationship) obj;
            return new EqualsBuilder()
                    .append(this.word, casted.getWord())
                    .append(this.meaning, casted.getMeaning())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.wordMeaningRelationshipID)
                .append("word", this.word)
                .append("meaning", this.meaning)
                .build();
    }
}
