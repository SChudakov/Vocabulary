package com.sschudakov.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "word_classes")
@DatabaseTable(tableName = "word_collections")
public class WordCollection {

    public static final String ID_COLUMN_NAME = "word_collection_id";
    public static final String COLLECTION_NAME_COLUMN_NAME = "word_collection_name";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DatabaseField(generatedId = true, columnName = ID_COLUMN_NAME)
    private int collectionID;

    @Column(name = COLLECTION_NAME_COLUMN_NAME)
    @DatabaseField(columnName = COLLECTION_NAME_COLUMN_NAME, canBeNull = false)
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
                .append("id", this.collectionID)
                .append("collection name", this.collectionName)
                .build();
    }
}
