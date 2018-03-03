package com.sschudakov.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Semen Chudakov on 14.12.2017.
 */
@Entity
@Table(name = "words")
public class Word {

    public static final String ID_COLUMN_NAME = "word_id";
    public static final String VALUE_COLUMN_NAME = "value";
    public static final String WORD_CLASS_COLUMN_NAME = "class";
    public static final String LANGUAGE_COLUMN_NAME = "language";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = VALUE_COLUMN_NAME)
    private String value;

    @ManyToOne
    @JoinColumn(name = WORD_CLASS_COLUMN_NAME,
            foreignKey = @ForeignKey(name = WORD_CLASS_COLUMN_NAME))
    private WordClass wordClass;

    @ManyToOne
    @JoinColumn(name = LANGUAGE_COLUMN_NAME,
            foreignKey = @ForeignKey(name = LANGUAGE_COLUMN_NAME))
    private Language language;


    public Integer getId() {
        return id;
    }

    public void setId(Integer wordID) {
        this.id = wordID;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


    public Word() {
        this(null, null, null, null);
    }

    public Word(String value) {
        this(null, value, null, null);
    }

    public Word(String value, WordClass wordClass, Language language) {
        this(null, value, wordClass, language);
    }

    public Word(Integer id, String value, WordClass wordClass, Language language) {
        this.id = id;
        this.value = value;
        this.wordClass = wordClass;
        this.language = language;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.value)
                .append(this.language)
                .append(this.wordClass)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word casted = (Word) obj;
            return new EqualsBuilder()
                    .append(this.value, casted.getValue())
                    .append(this.language, casted.getLanguage())
                    .append(this.wordClass, casted.getId())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("id", this.id)
                .append("value", this.value)
                .append("language", this.language)
                .append("value class", this.wordClass)
                .build();
    }
}
