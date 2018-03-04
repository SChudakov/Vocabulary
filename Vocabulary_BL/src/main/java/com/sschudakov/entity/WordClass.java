package com.sschudakov.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "word_classes")
public class WordClass {

    public static final String ID_COLUMN_NAME = "word_class_id";
    public static final String NAME_COLUMN_NAME = "word_class_name";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = NAME_COLUMN_NAME)
    private String wordClassName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer wordClassID) {
        this.id = wordClassID;
    }

    public String getWordClassName() {
        return wordClassName;
    }

    public void setWordClassName(String wordClassName) {
        this.wordClassName = wordClassName;
    }

    public WordClass() {
        this(null, null);
    }

    public WordClass(String wordClassName) {
        this(null, wordClassName);
    }

    public WordClass(Integer id, String name) {
        this.id = id;
        this.wordClassName = name;
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
                .append("id", this.id)
                .append("word class", this.wordClassName)
                .build();
    }
}