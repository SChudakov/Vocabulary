package com.sschudakov.bins;

/**
 * Created by Semen Chudakov on 14.12.2017.
 */
public class Word {

    private String value;

    public Word(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word otherWord = (Word) obj;
            return this.value.equals(otherWord.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
