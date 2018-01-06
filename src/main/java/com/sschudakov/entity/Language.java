package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "languages")
public class Language {

    @DatabaseField(id = true, columnName = "id")
    private int languageID;
    @DatabaseField(columnName = "language", canBeNull = false, foreign = true)
    private String languageName;

    public Language(String languageName) {
        this.languageName = languageName;
        this.languageID = languageName.hashCode();
    }
}
