package com.sschudakov.request;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.service.impl.LanguageSrvImpl;
import com.sschudakov.service.impl.WCRSrvImpl;
import com.sschudakov.service.impl.WMRSrvImpl;
import com.sschudakov.service.impl.WordClassSrvImpl;
import com.sschudakov.service.impl.WordCollectionSrvImpl;
import com.sschudakov.service.impl.WordSrvImpl;
import com.sschudakov.service.interf.LanguageSrv;
import com.sschudakov.service.interf.WCRSrv;
import com.sschudakov.service.interf.WMRSrv;
import com.sschudakov.service.interf.WordClassSrv;
import com.sschudakov.service.interf.WordCollectionSrv;
import com.sschudakov.service.interf.WordSrv;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRequestManager {

    private UserRequestValidator userRequestValidator;
    private WordCollectionSrv wordCollectionService;
    private WordClassSrv wordClassService;
    private LanguageSrv languageService;
    private WordSrv wordService;
    private WCRSrv wcrService;
    private WMRSrv wmrService;

    public UserRequestManager() {
        this.userRequestValidator = new UserRequestValidator();
        wordCollectionService = new WordCollectionSrvImpl();
        wordClassService = new WordClassSrvImpl();
        languageService = new LanguageSrvImpl();
        wordService = new WordSrvImpl();
        wcrService = new WCRSrvImpl();
        wmrService = new WMRSrvImpl();
    }

    //get requests

    /**
     * Tested successfully
     *
     * @return
     * @throws SQLException
     */
    public List<String> getLanguages() throws SQLException {
        return languageService.findAll().stream().map(Language::getLanguageName).collect(Collectors.toList());
    }

    /**
     * Tested successfully
     *
     * @return
     * @throws SQLException
     */
    public List<String> getCollections() throws SQLException {
        return wordCollectionService.findAll().stream().map(WordCollection::getCollectionName).collect(Collectors.toList());
    }

    /**
     * Tested successfully
     *
     * @return
     * @throws SQLException
     */
    public List<String> getClasses() throws SQLException {
        return wordClassService.findAll().stream().map(WordClass::getWordClassName).collect(Collectors.toList());
    }

    /**
     * Tested successfully
     *
     * @return
     * @throws SQLException
     */
    public List<String> getWordMeanings(String word, String wordLanguage, String meaningsLanguage) throws SQLException {
        List<String> result = new ArrayList<>();
        for (Word w : this.wmrService.findWordMeanings(word, wordLanguage, meaningsLanguage)) {
            result.add(w.getValue());
        }
        return result;
    }

    public List<String> getWordsByLanguageName(String language) throws SQLException {
        return this.wordService.findByLanguage(language).stream().map(Word::getValue).collect(Collectors.toList());
    }

    public String getWordClassByWord(String value, String language) throws SQLException {
        return this.wordService.findByValueAndLanguage(value,language).getWordClass().getWordClassName();
    }

    /**
     * Tested successfully
     *
     * @return
     * @throws SQLException
     */
    public Map<String, Boolean> getCollectionsByWord(String word, String language) throws SQLException {
        Map<String, Boolean> collections = new HashMap<>();
        for (WordCollection collection : this.wordCollectionService.findAll()) {
            collections.put(collection.getCollectionName(), Boolean.FALSE);
        }
        for (WordCollectionRelationship wcr : this.wcrService.findByWordAndLanguage(word, language)) {
            collections.put(wcr.getWordCollection().getCollectionName(), Boolean.TRUE);
        }
        return collections;
    }

    /**
     * Tested successfully
     *
     * @return
     * @throws SQLException
     */
    public List<String> getWordsByCollectionName(String collection) throws SQLException {
        return this.wcrService.findByCollection(collection).stream().map(wcr -> wcr.getWord().getValue()).collect(Collectors.toList());
    }


    //create request
    public void createCollection(String name) throws SQLException {
        if (collectionExists(name)) {
            throw new IllegalArgumentException("Collection with name " + name + " already exists");
        }
        this.wordCollectionService.create(name);
    }


    //deleteByName request
    public void deleteWord(String word, String language) throws SQLException {
        if (wordExists(word, language)) {
            this.wordService.delete(wordService.findByValueAndLanguage(word, language).getWordID());
        } else {
            throw new IllegalArgumentException("There is no word " + word + " in " + language);
        }
    }

    public void deleteCollection(String name) throws SQLException {
        if (collectionExists(name)) {
            this.wordCollectionService.deleteByName(name);
        } else {
            throw new IllegalArgumentException("There is no collection with the name " + name);
        }
    }


    //save word information methods
    public void saveWordInformation(String word, String wordClass, String language) throws SQLException {
        if (wordExists(word, language)) {
            Word foundWord = this.wordService.findByValueAndLanguage(word, language);
            foundWord.setWordClass(this.wordClassService.findByName(wordClass));
            this.wordService.update(foundWord);
        } else {
            if (wordClass == null) {
                this.wordService.create(word, "empty", language);//todo: omg constant
            } else {
                this.wordService.create(word, wordClass, language);
            }
        }
    }


    public void addMeaning(String word, String wordLanguage, String meaning, String meaningsLanguage) throws SQLException {
        Word foundWord = this.wordService.findByValueAndLanguage(word, wordLanguage);
        Word foundMeaning = this.wordService.findByValueAndLanguage(meaning, meaningsLanguage);
        if (!wordMeaningRelationshipExists(word, wordLanguage, meaning, meaningsLanguage)) {
            this.wmrService.create(foundWord, foundMeaning);
        } else {
            throw new IllegalArgumentException("Word " + word + " already has meaning " + meaning);
        }
    }

    public void removeMeaning(String word, String wordLanguage, String meaning, String meaningsLanguage) throws SQLException {
        if (wordMeaningRelationshipExists(word, wordLanguage, meaning, meaningsLanguage)) {
            this.wmrService.delete(word, wordLanguage, meaning, meaningsLanguage);
        } else {
            throw new IllegalArgumentException("word " + word + " has no meaning " + meaning);
        }
    }


    public void putInCollection(String word, String wordLanguage, String collection) throws SQLException {
        if (!wordCollectionsRelationshipExists(word, wordLanguage, collection)) {
            this.wcrService.create(
                    this.wordService.findByValueAndLanguage(word, wordLanguage),
                    this.wordCollectionService.findByName(collection)
            );
        } else {
            throw new IllegalArgumentException("word " + word + " is already in " + collection + " collection");
        }
    }

    public void removeFromCollection(String word, String wordLanguage, String collection) throws SQLException {
        if (wordCollectionsRelationshipExists(word, wordLanguage, collection)) {
            this.wcrService.delete(word, wordLanguage, collection);
        } else {
            throw new IllegalArgumentException("there is no word " + word + " in " + collection + " collection");
        }
    }


    //check requests
    public boolean wordExists(String word, String language) throws SQLException {
        return wordService.findByValueAndLanguage(word, language) != null;
    }

    public boolean collectionExists(String collection) throws SQLException {
        return wordCollectionService.findByName(collection) != null;
    }

    public boolean wordMeaningRelationshipExists(String word, String language, String meaning, String meaningLanguage) throws SQLException {
        return this.wmrService.findByWordAndMeaning(word, language, meaning, meaningLanguage).size() == 2;
    }

    public boolean wordCollectionsRelationshipExists(String word, String language, String collection) throws SQLException {
        return this.wcrService.findByWordAndCollection(word, language, collection) != null;
    }
}
