package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@DatabaseTable(tableName = "words classes")
public class WordClass {
    @DatabaseField(id = true, columnName = "id")
    private int wordClassID;
    @DatabaseField(columnName = "name", canBeNull = false)
    private String wordClassName;

    public int getWordClassID() {
        return wordClassID;
    }

    public void setWordClassID(int wordClassID) {
        this.wordClassID = wordClassID;
    }

    public String getWordClassName() {
        return wordClassName;
    }

    public void setWordClassName(String wordClassName) {
        this.wordClassName = wordClassName;
    }

    public WordClass() {
    }

    public WordClass(String wordClassName) {
        this.wordClassName = wordClassName;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.wordClassName)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordClass) {
            WordClass casted = (WordClass) obj;
            return new EqualsBuilder()
                    .append(this.wordClassName, casted.getWordClassName())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(this.wordClassID)
                .append(this.wordClassName)
                .toString();
    }
}