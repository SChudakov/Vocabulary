package com.sschudakov.entity;

public class WordCollection {

    private int collectionID;
    private String collectionName;

    public WordCollection(String collectionName) {
        this.collectionName = collectionName;
        this.collectionID = collectionName.hashCode();
    }
}
