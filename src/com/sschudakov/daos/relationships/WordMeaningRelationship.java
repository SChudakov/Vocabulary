package com.sschudakov.daos.relationships;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sschudakov.daos.bins.Word;

@DatabaseTable(tableName = "word_meaning")
public class WordMeaningRelationship {

    @DatabaseField(id = true)
    private int wordMeaningRelationship;
    @DatabaseField(foreign = true)
    private Word wordID;
    @DatabaseField(foreign = true)
    private Word meaningID;

    public WordMeaningRelationship() {
    }
}
