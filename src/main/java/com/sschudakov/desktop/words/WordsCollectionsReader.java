package com.sschudakov.desktop.words;

import com.sschudakov.desktop.words.parsing.FileParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class WordsCollectionsReader {

    private static final String WORDS_COLLECTIONS_DIRECTORY_PATH = "D:\\Workspace.java\\Vocabulary\\words_collections";
    private static final String DOCX_EXTENSION = ".docx";


    public static Collection<HashMap<String, List<String>>> readWordsCollections() {
        Collection<HashMap<String, List<String>>> result = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(WORDS_COLLECTIONS_DIRECTORY_PATH).listFiles())) {
            System.out.println("\nFILE: " + file.getName() + "\n");
            if (!isDOCXFile(file.getPath())) {
                result.add(FileParser.parse(file));
            }
        }
        return result;
    }

    private static boolean isDOCXFile(String path) {
        return path.endsWith(DOCX_EXTENSION);
    }
}
