package com.sschudakov.entity;

public class WordClass {
    private int wordClassID;
    private String wordClassName;

    public WordClass(String wordClassName) {
        this.wordClassName = wordClassName;
        this.wordClassID = wordClassName.hashCode();
    }
}
