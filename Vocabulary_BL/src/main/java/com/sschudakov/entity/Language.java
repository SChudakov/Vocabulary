package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
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
@DatabaseTable(tableName = "languages")
public class Language {

    public static final String ID_FIELD_COLUMN_NAME = "language_id";
    public static final String NAME_FIELD_COLUMN_NAME = "language_name";

    @Id
    @Column(name = ID_FIELD_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DatabaseField(generatedId = true, columnName = ID_FIELD_COLUMN_NAME)
    private int languageID;

    @Column(name = NAME_FIELD_COLUMN_NAME)
    @DatabaseField(columnName = NAME_FIELD_COLUMN_NAME, canBeNull = false)
    private String languageName;

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
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
        this.languageID = languageName.hashCode();
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
                .append("language id", this.languageID)
                .append("language name", this.languageName)
                .build();
    }
}
