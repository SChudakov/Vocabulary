package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "words collections")
public class WordCollection {
    @DatabaseField(id = true, columnName = "id")
    private int collectionID;
    @DatabaseField(columnName = "name", canBeNull = false)
    private String collectionName;

    public WordCollection(String collectionName) {
        this.collectionName = collectionName;
        this.collectionID = collectionName.hashCode();
    }
}
