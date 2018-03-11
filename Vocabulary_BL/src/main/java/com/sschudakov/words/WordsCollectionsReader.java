package com.sschudakov.words;

import com.sschudakov.entity.Word;
import com.sschudakov.parsing.FileParser;
import com.sschudakov.utils.FileExtensionDeterminer;

import java.io.File;
import java.util.*;

public class WordsCollectionsReader {

    private static final String WORDS_COLLECTIONS_DIRECTORY_PATH = "D:\\Workspace.java\\Vocabulary\\words_collections";

    public static Collection<HashMap<String, List<String>>> readWordsCollections() {
        Collection<HashMap<String, List<String>>> result = new ArrayList<>();
        for (File file : new File(WORDS_COLLECTIONS_DIRECTORY_PATH).listFiles()) {
            System.out.println("\nFILE: " + file.getName() + "\n");
            if (!FileExtensionDeterminer.isDOCXFile(file.getPath())) {
                result.add(FileParser.parse(file));
            }
        }
        return result;
    }
}
