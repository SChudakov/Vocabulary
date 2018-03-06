package com.sschudakov.request;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.service.*;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class UserRequestManager {

    private WordCollectionSrv wordCollectionService;
    private WordClassSrv wordClassService;
    private LanguageSrv languageService;
    private WordSrv wordService;
    private WCRSrv wcrService;
    private WMRSrv wmrService;

    public UserRequestManager() {
        wordCollectionService = new WordCollectionSrv();
        wordClassService = new WordClassSrv();
        languageService = new LanguageSrv();
        wordService = new WordSrv();
        wcrService = new WCRSrv();
        wmrService = new WMRSrv();
    }


    //-------------- initializations get requests ---------------//

    public List<String> getLanguages() throws SQLException {
        return languageService.findAll();
    }

    public List<String> getCollections() throws SQLException {
        return wordCollectionService.findAll();
    }

    public List<String> getClasses() throws SQLException {
        return wordClassService.findAll();
    }


    //-------------- load information get requests ---------------//

    public List<String> getWordMeanings(String word, String wordLanguage, String meaningsLanguage) throws SQLException {
        List<String> result = new ArrayList<>();
        for (Word w : this.wmrService.findWordMeanings(word, wordLanguage, meaningsLanguage)) {
            result.add(w.getValue());
        }
        return result;
    }

    public List<String> getWordsByLanguageName(String language) throws SQLException {
        return this.wordService.findByLanguage(language);
    }

    public String getWordClassByWord(String value, String language) throws SQLException {
        Optional<WordClass> foundWordClass = Optional.of(this.wordService.findByValueAndLanguage(value, language).getWordClass());
        if (foundWordClass.isPresent()) { //TODO: ---------------------------------------------------------------------------------------------------------------
            return foundWordClass.get().getWordClassName();
        }
        return null;
    }

    public Map<String, Boolean> getCollectionsByWord(String word, String language) throws SQLException {
        Map<String, Boolean> collections = new HashMap<>();
        this.wordCollectionService.findAll().stream().forEach(a -> collections.put(a, Boolean.FALSE));
        for (WordCollectionRelationship wcr : this.wcrService.findByWordAndLanguage(word, language)) {
            collections.put(wcr.getWordCollection().getCollectionName(), Boolean.TRUE);
        }
        return collections;
    }

    public List<String> getWordsByCollectionName(String collection) throws SQLException {
        return this.wcrService.findByCollection(collection).stream().map(wcr -> wcr.getWord().getValue()).collect(Collectors.toList());
    }


    //-------------- create collection request  ---------------//

    public void createCollection(String name) throws SQLException {
        this.wordCollectionService.create(name);
    }


    //-------------- delete requests ---------------//

    public void deleteWord(String word, String language) throws SQLException {
        if (wordExists(word, language)) {
            this.wordService.delete(wordService.findByValueAndLanguage(word, language).getId()); //TODO: -------------------------------------------------------
        } else {
            throw new IllegalArgumentException("There is no word " + word + " in " + language);
        }
    }

    public void deleteCollection(String name) throws SQLException {
        this.wordCollectionService.deleteByName(name);
    }


    //-------------- save word information method ---------------//

    public void saveWordInformation(String word, String wordClass, String language) throws SQLException {
        if (wordExists(word, language)) {
            Word foundWord = this.wordService.findByValueAndLanguage(word, language);//TODO: -------------------------------------------------------
            foundWord.setWordClass(this.wordClassService.findByName(wordClass));
            this.wordService.update(foundWord);//TODO: -------------------------------------------------------
        } else {
            this.wordService.create(word, wordClass, language);//TODO: -------------------------------------------------------
        }
    }


    //-------------- add \ remove meaning methods ---------------//

    public void addMeaning(String word, String wordLanguage, String meaning, String meaningsLanguage) throws SQLException {
        Word foundWord = this.wordService.findByValueAndLanguage(word, wordLanguage);//TODO: -------------------------------------------------------
        Word foundMeaning = this.wordService.findByValueAndLanguage(meaning, meaningsLanguage);//TODO: -------------------------------------------------------
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


    //-------------- put into \ remove from collection methods ---------------//

    public void putInCollection(String word, String wordLanguage, String collection) throws SQLException {
        if (!wordCollectionsRelationshipExists(word, wordLanguage, collection)) {
            this.wcrService.create(
                    this.wordService.findByValueAndLanguage(word, wordLanguage),//TODO: -------------------------------------------------------
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


    //-------------- check requests ---------------//

    public boolean wordExists(String word, String language) throws SQLException {
        return wordService.findByValueAndLanguage(word, language) != null;
    }


    public boolean wordMeaningRelationshipExists(String word, String language, String meaning, String meaningLanguage) throws SQLException {
        return this.wmrService.findByWordAndMeaning(word, language, meaning, meaningLanguage) != null;
    }

    public boolean wordCollectionsRelationshipExists(String word, String language, String collection) throws SQLException {
        return this.wcrService.findByWordAndCollection(word, language, collection) != null;
    }
}
