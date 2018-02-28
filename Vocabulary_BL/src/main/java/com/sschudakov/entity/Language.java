package com.sschudakov.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Language {

    public static final String ID_COLUMN_NAME = "language_id";
    public static final String NAME_OLUMN_NAME = "language_name";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = NAME_OLUMN_NAME)
    private String languageName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Language() {
    }

    public Language(String languageName) {
        this.languageName = languageName;
        this.id = languageName.hashCode();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.languageName)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Language) {
            Language casted = (Language) obj;
            return new EqualsBuilder()
                    .append(this.languageName, casted.getLanguageName())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("language id", this.id)
                .append("language name", this.languageName)
                .build();
    }
}
