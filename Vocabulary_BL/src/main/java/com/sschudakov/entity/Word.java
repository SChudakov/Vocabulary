package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    @DatabaseField(columnName = "word class", foreign = true, canBeNull = false)
    private Language language;


    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
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
    }

    public Word(String value) {
        this(value, null, null);
    }

    public Word(String value, WordClass wordClass, Language language) {
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
                    .append(this.wordClass, casted.getWordID())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.wordID)
                .append(this.value)
                .append(this.language)
                .append(this.wordClass)
                .toString();
    }
}
