package com.sschudakov.desktop.words;

import com.sschudakov.desktop.words.parsing.FileParser;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.desktop.factory.ServiceFactory;
import com.sschudakov.service.dao.WordCollectionSrv;
import com.sschudakov.service.dao.WordSrv;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Semen Chudakov on 16.12.2017.
 */
public class WordsCollectionsManager {


    //-------------- regular expression constant -----------------//

    private static final String REGULAR_EXPRESSION_FOR_REMOVING_EXTENSION = "[.][^.]+";

    //-------------- file parser -----------------//

    private FileParser fileParser;


    //-------------- service objects  -----------------//

    private WordSrv wordSrv;
    private WordCollectionSrv wordCollectionSrv;

    //-------------- constructor  -----------------//

    public WordsCollectionsManager() {

        this.fileParser = new FileParser();

        this.wordSrv = ServiceFactory.createWordService();
        this.wordCollectionSrv = ServiceFactory.createWordCollectionService();
    }


    //-------------- persisting collections into database -----------------//

    public void persistCollectionIntoDatabase(String path, Language wordLanguage,
                                              Language meaningLanguage, WordClass wordClass) {
        File collectionFile = new File(path);
        HashMap<String, List<String>> readCollection = this.fileParser.parse(collectionFile);

        WordCollection persistedCollection = null;

        try {
            String collectionName = formCollectionsName(collectionFile.getName());
            wordCollectionSrv.create(collectionName);
            persistedCollection = wordCollectionSrv.findByName(collectionName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WordCollection finalPersistedCollection = persistedCollection;
        readCollection.entrySet().forEach(e -> {
            try {
                createWord(e.getKey(), wordClass, wordLanguage);
                Word persistedWord = findWord(e.getKey(), wordLanguage);
                putInCollection(persistedWord, finalPersistedCollection);
                e.getValue().forEach(v -> {
                    try {
                        createWord(v, wordClass, meaningLanguage);
                        Word persistedMeaning = findWord(v, meaningLanguage);
                        addMeaning(persistedWord, persistedMeaning);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void createWord(String value, WordClass wordClass, Language language) {
        try {
            if (!wordSrv.wordExists(value, language)) {
                wordSrv.create(value, wordClass, language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Word findWord(String value, Language language) {
        Word result = null;
        try {
            result = wordSrv.findByValueAndLanguage(value, language);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void addMeaning(Word word, Word meaning) {
        try {
            wordSrv.addMeaning(word, meaning);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void putInCollection(Word word, WordCollection collection) {
        try {
            wordSrv.putInCollection(word, collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //-------------- read collections names -----------------//

    private List<String> readCollectionsNames(String directoryPath) {
        return readCollectionsNames(new File(directoryPath));
    }

    private List<String> readCollectionsNames(File collectionsDirectory) {
        List<String> result = new ArrayList<>();
        for (File file : Objects.requireNonNull(collectionsDirectory.listFiles())) {
            result.add(formCollectionsName(file.getName()));
        }
        return result;
    }

    private String formCollectionsName(String fileName) {
        return fileName.replaceFirst(
                REGULAR_EXPRESSION_FOR_REMOVING_EXTENSION,
                ""
        );
    }
}
