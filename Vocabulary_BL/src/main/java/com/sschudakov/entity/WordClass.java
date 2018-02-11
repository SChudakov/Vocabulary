package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "word_classes")
@DatabaseTable(tableName = "word_classes")
public class WordClass {

    public static final String ID_COLUMN_NAME = "word_class_id";
    public static final String CLASS_NAME_COLUMN_NAME = "word_class_name";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DatabaseField(generatedId = true, columnName = ID_COLUMN_NAME)
    private int wordClassID;

    @Column(name = CLASS_NAME_COLUMN_NAME)
    @DatabaseField(columnName = CLASS_NAME_COLUMN_NAME, canBeNull = false)
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
                .append("id", this.wordClassID)
                .append("word class", this.wordClassName)
                .build();
    }
}