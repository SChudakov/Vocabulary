package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "words classes")
public class WordClass {
    @DatabaseField(id = true, columnName = "id")
    private int wordClassID;
    @DatabaseField(columnName = "name", canBeNull = false)
    private String wordClassName;

    public WordClass(String wordClassName) {
        this.wordClassName = wordClassName;
        this.wordClassID = wordClassName.hashCode();
    }
}
