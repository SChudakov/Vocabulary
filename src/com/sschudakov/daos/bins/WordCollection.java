package com.sschudakov.daos.bins;

public class WordCollection {

    private int collectionID;
    private String collectionName;

    public WordCollection(String collectionName) {
        this.collectionName = collectionName;
        this.collectionID = collectionName.hashCode();
    }
}
