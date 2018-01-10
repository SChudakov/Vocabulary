package com.sschudakov.database;

import java.util.Collection;

public class TablesContentReader {
    public static void readDaosContent() {
        Collection<Class<?>> daoClasses = DaoClassesReader.readClasses();
        for (Class<?> daoClass : daoClasses) {
            System.out.println("\n" + daoClass + ": ");
            for (Object o : DatabaseAccessor.uploadData(daoClass)) {
                System.out.println(o);
            }
        }
    }
}
