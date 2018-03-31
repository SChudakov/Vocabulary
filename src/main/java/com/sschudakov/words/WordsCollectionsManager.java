package com.sschudakov.words;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.factory.ServiceFactory;
import com.sschudakov.words.parsing.FileParser;
import com.sschudakov.service.WordCollectionSrv;
import com.sschudakov.service.WordSrv;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Semen Chudakov on 16.12.2017.
 */
public class WordsCollectionsManager {


    //-------------- regular expression constant -----------------//

    private static final String REGULAR_EXPRESSION_FOR_REMOVING_EXTENSION = "[.][^.]+";


    //-------------- regular expression constant -----------------//

    private static WordSrv wordSrv = ServiceFactory.createWordService();
    private static WordCollectionSrv wordCollectionSrv = ServiceFactory.createWordCollectionService();


    //-------------- persisting collections into database -----------------//

    public static void persistCollectionIntoDatabase(String path, Language wordLanguage,
                                                     Language meaningLanguage, WordClass wordClass) {
        File collectionFile = new File(path);
        HashMap<String, List<String>> readCollection = FileParser.parse(collectionFile);

        WordCollection persistedCollection = null;

        try {
            String collectionName = formCollectionsName(collectionFile.getName());
            wordCollectionSrv.create(collectionName);
            persistedCollection = wordCollectionSrv.findByName(collectionName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WordCollection finalPersistedCollection = persistedCollection;
        readCollection.entrySet().stream().forEach(e -> {
            try {
                createWord(e.getKey(), wordClass, wordLanguage);
                Word persistedWord = findWord(e.getKey(), wordLanguage);
                putInCollection(persistedWord, finalPersistedCollection);
                e.getValue().stream().forEach(v -> {
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

    private static void createWord(String value, WordClass wordClass, Language language) {
        try {
            if (!wordSrv.wordExists(value, language)) {
                wordSrv.create(value, wordClass, language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Word findWord(String value, Language language) {
        Word result = null;
        try {
            result = wordSrv.findByValueAndLanguage(value, language);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void addMeaning(Word word, Word meaning) {
        try {
            wordSrv.addMeaning(word, meaning);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void putInCollection(Word word, WordCollection collection) {
        try {
            wordSrv.putInCollection(word, collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //-------------- read collections names -----------------//

    private static List<String> readCollectionsNames(String directoryPath) {
        return readCollectionsNames(new File(directoryPath));
    }

    private static List<String> readCollectionsNames(File collectionsDirectory) {
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
