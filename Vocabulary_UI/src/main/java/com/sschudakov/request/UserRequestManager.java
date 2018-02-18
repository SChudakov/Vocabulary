package com.sschudakov.request;

import com.sschudakov.entity.*;
import com.sschudakov.service.impl.*;
import com.sschudakov.service.interf.*;

import java.sql.SQLException;
import java.util.*;
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
    public List<String> getLanguages() throws SQLException {
        return languageService.findAll().stream().map(Language::getLanguageName).collect(Collectors.toList());
    }

    public List<String> getCollections() throws SQLException {
        return wordCollectionService.findAll().stream().map(WordCollection::getCollectionName).collect(Collectors.toList());
    }

    public List<String> getClasses() throws SQLException {
        return wordClassService.findAll().stream().map(WordClass::getWordClassName).collect(Collectors.toList());
    }

    public List<String> getMeaningsByWord(String word, String wordLanguage, String meaningsLanguage) throws SQLException {
        Collection<WordMeaningRelationship> wmrCollection = wmrService.findByWordAndLanguage(word, wordLanguage);
        List<String> meanings = new ArrayList<>();
        for (WordMeaningRelationship wmr : wmrCollection) {
            if (wmr.getMeaning().getLanguage().getLanguageName().equals(meaningsLanguage)) {
                meanings.add(wmr.getMeaning().getValue());
            }
        }
        return meanings;
    }

    public String getClassByWord(String word, String language) throws SQLException {
        return wordService.findByValueAndLanguage(word, language).getWordClass().getWordClassName();
    }

    public List<String> getWordsByLanguageName(String language) {
        //todo: do we need this?
        throw new UnsupportedOperationException();
    }

    public Map<String, Boolean> getCollectionsByWord(String word, String language) throws SQLException {
        Map<String, Boolean> collections = new HashMap<>();
        for (WordCollection collection : wordCollectionService.findAll()) {
            collections.put(collection.getCollectionName(), Boolean.FALSE);
        }
        for (WordCollectionRelationship wcr : wcrService.findByWordAndLanguage(word, language)) {
            collections.put(wcr.getWordCollection().getCollectionName(), Boolean.TRUE);
        }
        return collections;
    }

    public List<String> getWordsByCollectionName(String collection) throws SQLException {
        return wcrService.findByCollection(collection).stream().map(wcr -> wcr.getWord().getValue()).collect(Collectors.toList());
    }

    //change requests
    public boolean createWord(String word, String language) throws SQLException {
        if (wordExists(word, language)) {
            return false;
        }
        wordService.create(word, "???", language); //todo: default word class?
        return true;
    }

    public boolean createCollection(String name) throws SQLException {
        if (collectionExists(name)) {
            return false;
        }
        wordCollectionService.create(name);
        return true;
    }

    public boolean deleteWord(String word, String language) throws SQLException {
        if (!wordExists(word, language)) {
            return false;
        }
        wordService.delete(wordService.findByValueAndLanguage(word, language).getWordID());
        return true;
    }

    public boolean deleteCollection(String name) throws SQLException {
        if (!collectionExists(name)) {
            return false;
        }
        wordCollectionService.delete(wordCollectionService.findByName(name).getCollectionID());
        return true;
    }

    public boolean addMeaningToWord(String word, String language, String meaning, String meaningLanguage) throws SQLException {
        if (!wordExists(word, language) || !wordExists(meaning,meaningLanguage)) {
            //todo: mb throw exception to specify what exactly doesn't exist
            return false;
        }
        Word wordObj = wordService.findByValueAndLanguage(word, language);
        Word meaningObj = wordService.findByValueAndLanguage(meaning, meaningLanguage);
        wmrService.create(wordObj, meaningObj);
        return true;
    }

    public boolean addWordToCollection(String word, String language, String collection) throws SQLException {
        if (!wordExists(word, language) || !collectionExists(collection)) {
            //todo: mb throw exception to specify what exactly doesn't exist
            return false;
        }
        Word wordObj = wordService.findByValueAndLanguage(word, language);
        WordCollection collectionObj = wordCollectionService.findByName(collection);
        wcrService.create(wordObj, collectionObj);
        return true;
    }

    public boolean removeWordFromCollection(String word, String language, String collection) {
        throw new UnsupportedOperationException(); //todo: implement this
    }

    public boolean changeWordClassTo(String word, String language, String className) throws SQLException {
        if (!wordExists(word, language) || !classExists(className)) {
            //todo: mb throw exception to specify what exactly doesn't exist
            return false;
        }
        Word wordObj = wordService.findByValueAndLanguage(word, language);
        wordObj.setWordClass(wordClassService.findByName(className));
        wordService.update(wordObj);
        return true;
    }

    //check requests
    public boolean wordExists(String word, String language) throws SQLException {
        return wordService.findByValueAndLanguage(word, language) != null;
    }

    public boolean collectionExists(String collection) throws SQLException {
        return wordCollectionService.findByName(collection) != null;
    }

    public boolean languageExists(String language) throws SQLException {
        return languageService.findByName(language) != null;
    }

    public boolean classExists(String className) throws SQLException {
        return wordClassService.findByName(className) != null;
    }
}
