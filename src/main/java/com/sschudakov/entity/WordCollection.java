package com.sschudakov.entity;

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
@Table(name = "word_collections")
public class WordCollection {

    public static final String ID_CN = "word_collection_id";
    public static final String NAME_CN = "word_collection_name";

    @Id
    @Column(name = ID_CN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = NAME_CN)
    private String collectionName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer collectionID) {
        this.id = collectionID;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }


    public WordCollection() {
        this(null, null);
    }

    public WordCollection(String collectionName) {
        this(null, collectionName);
    }

    public WordCollection(Integer id, String collectionName) {
        this.id = id;
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
                .append("id", this.id)
                .append("collection name", this.collectionName)
                .build();
    }
}
