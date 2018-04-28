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

    public static final String ID_CN = "language_id";
    public static final String NAME_CN = "language_name";

    @Id
    @Column(name = ID_CN, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = NAME_CN, updatable = false, nullable = false)
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
        this(null, null);
    }

    public Language(String languageName) {
        this(null, languageName);
    }

    public Language(Integer id, String languageName) {
        this.id = id;
        this.languageName = languageName;
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
