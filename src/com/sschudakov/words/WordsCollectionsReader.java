package com.sschudakov.words;

import com.sschudakov.bins.Word;
import com.sschudakov.parsing.FileParser;
import com.sschudakov.utils.FileExtensionDeterminer;

import java.io.File;
import java.util.*;

public class WordsCollectionsReader {

    private static final String WORDS_COLLECTIONS_DIRECTORY_PATH = "D:\\Workspace.java\\Vocabulary\\words_collections";

    public static Collection<HashMap<Word, List<Word>>> readWordsCollections() {
        Collection<HashMap<Word, List<Word>>> result = new ArrayList<>();
        for (File file : new File(WORDS_COLLECTIONS_DIRECTORY_PATH).listFiles()) {
            if (!FileExtensionDeterminer.isDOCXFile(file.getPath())) {
                result.add(FileParser.parse(file));
            }
        }
        return result;
    }
}
