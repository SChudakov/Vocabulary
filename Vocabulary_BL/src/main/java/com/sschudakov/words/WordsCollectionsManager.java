package com.sschudakov.words;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 16.12.2017.
 */
public class WordsCollectionsManager {


    private static final String WORDS_COLLECTIONS_DIRECTORY = "D:\\Workspace.java\\Vocabulary\\words_collections";
    private static final String REGULAR_EXPRESSION_FOR_REMOVING_EXTENSION = "[.][^.]+";

    private static List<String> collections;

    public static List<String> readExistingCollections() {
        return readExistingCollections(new File(WORDS_COLLECTIONS_DIRECTORY));
    }


    private static List<String> readExistingCollections(File collectionsDirectory) {
        List<String> result = new ArrayList<>();
        for (File file : collectionsDirectory.listFiles()) {
            result.add(formCollectionsName(file.getName()));
        }
        return result;
    }

    private static String formCollectionsName(String fileName) {
        return fileName.replaceFirst(
                REGULAR_EXPRESSION_FOR_REMOVING_EXTENSION,
                ""
        );
    }
}
