package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@DatabaseTable(tableName = "languages")
public class Language {

    @DatabaseField(id = true, columnName = "id")
    private int languageID;
    @DatabaseField(columnName = "language", canBeNull = false)
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
                .append(this.languageID)
                .append(this.languageName)
                .toString();
    }
}
