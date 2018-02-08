package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@DatabaseTable(tableName = "words collections")
public class WordCollection {
    @DatabaseField(id = true, columnName = "id")
    private int collectionID;
    @DatabaseField(columnName = "name", canBeNull = false)
    private String collectionName;


    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public WordCollection() {
    }

    public WordCollection(String collectionName) {
        this.collectionName = collectionName;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.collectionName)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordCollection) {
            WordCollection casted = (WordCollection) obj;
            return new EqualsBuilder()
                    .append(this.collectionName, casted.getCollectionName())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(this.collectionID)
                .append(this.collectionName)
                .toString();
    }
}
