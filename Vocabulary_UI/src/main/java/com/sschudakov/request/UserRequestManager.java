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
import java.util.Collection;
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
    public List<String> getMeaningsByWord(String word, String wordLanguage, String meaningsLanguage) throws SQLException {
        List<String> result = new ArrayList<>();
        for (Word w : this.wmrService.findWordMeanings(word, wordLanguage, meaningsLanguage)) {
            result.add(w.getValue());
        }
        return result;
    }

    public List<String> getWordsByLanguageName(String language) {
        //todo: do we need this?
        throw new UnsupportedOperationException();
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


    //change requests
    public void saveWordInformation(String word, String wordClass, String language) throws SQLException {
        if (wordExists(word, language)) {
            Word foundWord = this.wordService.findByValueAndLanguage(word, language);
            foundWord.setWordClass(this.wordClassService.findByName(wordClass));
            this.wordService.update(foundWord);
        } else {
            this.wordService.create(word, wordClass, language);
        }
    }

    public void addMeanings(String word, String language, Map<String, Collection<String>> meanings) throws SQLException {
        Word persistedWord = this.wordService.findByValueAndLanguage(word, language);
        for (Map.Entry<String, Collection<String>> stringCollectionEntry : meanings.entrySet()) {
            for (String s : stringCollectionEntry.getValue()) {
                addMeaning(persistedWord, s, stringCollectionEntry.getKey());
            }
        }
    }

    private void addMeaning(Word word, String meaning, String meaningsLanguage) throws SQLException {
        Word foundMeaning = this.wordService.findByValueAndLanguage(meaning, meaningsLanguage);
        this.wmrService.create(word, foundMeaning);
    }

    public void removeMeanings(String word, String language, Map<String, Collection<String>> meanings) throws SQLException {
        for (Map.Entry<String, Collection<String>> stringCollectionEntry : meanings.entrySet()) {
            for (String s : stringCollectionEntry.getValue()) {
                this.wmrService.delete(word, language, s, stringCollectionEntry.getKey());
            }
        }
    }

    public void putInCollections(String word, String lanugage, Collection<String> collections) throws SQLException {
        Word foundWord = this.wordService.findByValueAndLanguage(word, lanugage);
        for (String collection : collections) {
            this.wcrService.create(foundWord, this.wordCollectionService.findByName(collection));
        }
    }

    public void removeFromCollections(String word, String language, Collection<String> collections) throws SQLException {
        for (String collection : collections) {
            removeFromCollection(word, language, collection);
        }
    }

    public void removeFromCollection(String word, String language, String collectionName) throws SQLException {
        this.wcrService.delete(word, language, collectionName);
    }

    public void createCollection(String name) throws SQLException {
        if (collectionExists(name)) {
            throw new IllegalAccessError("Collection with name " + name + " already exists");
        }
        this.wordCollectionService.create(name);
    }

    //remove request
    public void deleteWord(String word, String language) throws SQLException {
        wordService.delete(wordService.findByValueAndLanguage(word, language).getWordID());
    }

    public void deleteCollection(String name) throws SQLException {
        this.wordCollectionService.delete(name);
    }




    //check requests
    private boolean wordExists(String word, String language) throws SQLException {
        return wordService.findByValueAndLanguage(word, language) != null;
    }

    private boolean wordMeaningRelationshipExists(Word word, Word meaning) {
        return this.wmrService.findByWordAndMeaningIds(word.getWordID(), meaning.getWordID()) != null;
    }

    private boolean wordCollectionsRelationshipExists(Word word, WordCollection collection) {
        return this.wcrService.findByWordAndCollectionIds(word.getWordID(), collection.getCollectionID()) != null;
    }

    private boolean collectionExists(String collection) throws SQLException {
        return wordCollectionService.findByName(collection) != null;
    }
}
