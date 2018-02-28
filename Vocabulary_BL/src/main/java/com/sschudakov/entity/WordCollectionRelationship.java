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
@Table(name = "word_collection_relationships")
public class WordCollectionRelationship {

    public static final String ID_COLUMN_NAME = "collection_relationship_id";
    public static final String WORD_COLUMN_NAME = "relationships_word";
    public static final String COLLECTION_COLUMN_NAME = "relationships_collection";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = WORD_COLUMN_NAME,
            foreignKey = @ForeignKey(name = WORD_COLUMN_NAME))
    private Word word;

    @ManyToOne
    @JoinColumn(name = COLLECTION_COLUMN_NAME,
            foreignKey = @ForeignKey(name = COLLECTION_COLUMN_NAME))
    private WordCollection wordCollection;


    public Integer getId() {
        return id;
    }

    public void setId(Integer wordCollectionRelationshipID) {
        this.id = wordCollectionRelationshipID;
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
                .append("id", this.id)
                .append("word", this.word)
                .append("word collection", this.wordCollection)
                .build();
    }
}
