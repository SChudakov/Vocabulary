package com.sschudakov.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "word_meaning_relationships")
public class WordMeaningRelationship {

    public static final String ID_COLUMN_NAME = "meaning_relationship_id";
    public static final String WORD_COLUMN_NAME = "relationships_word";
    public static final String MEANING_COLUMN_NAME = "relationships_meaning";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = WORD_COLUMN_NAME, nullable = false,
            foreignKey = @ForeignKey(name = WORD_COLUMN_NAME))
    private Word word;

    @ManyToOne
    @JoinColumn(name = MEANING_COLUMN_NAME, nullable = false,
            foreignKey = @ForeignKey(name = MEANING_COLUMN_NAME))
    private Word meaning;


    public Integer getId() {
        return id;
    }

    public void setId(Integer wordMeaningRelationshipID) {
        this.id = wordMeaningRelationshipID;
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
        this(null, null, null);
    }

    public WordMeaningRelationship(Word word, Word meaning) {
        this(null, word, meaning);
    }

    public WordMeaningRelationship(Integer id, Word word, Word meaning) {
        this.id = id;
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
                .append("id", this.id)
                .append("word", this.word)
                .append("meaning", this.meaning)
                .build();
    }
}
