package com.sschudakov.request;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.service.LanguageSrv;
import com.sschudakov.service.WordClassSrv;
import com.sschudakov.service.WordCollectionSrv;
import com.sschudakov.service.WordSrv;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRequestManager {

    private LanguageSrv languageService;
    private WordClassSrv wordClassService;
    private WordCollectionSrv wordCollectionService;
    private WordSrv wordService;

    public UserRequestManager(LanguageSrv languageService, WordClassSrv wordClassService,
                              WordCollectionSrv wordCollectionService, WordSrv wordService) {
        this.languageService = languageService;
        this.wordClassService = wordClassService;
        this.wordCollectionService = wordCollectionService;
        this.wordService = wordService;
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
        Language foundWordLanguage = this.languageService.findByName(wordLanguage);
        Word foundWord = this.wordService.findByValueAndLanguage(word, foundWordLanguage);
        Language foundMeaningLanguage = this.languageService.findByName(meaningsLanguage);
        return this.wordService.getWordsMeanings(foundWord, foundMeaningLanguage);
    }

    public List<String> getWordsByLanguageName(String languageName) throws SQLException {
        Language foundLanguage = this.languageService.findByName(languageName);
        return this.wordService.findByLanguage(foundLanguage);
    }

    public String getWordClassByWord(String value, String language) throws SQLException {
        Language foundLanguage = this.languageService.findByName(language);
        System.out.println(value);
        System.out.println(language);
        Optional<WordClass> foundWordClass = Optional.of(this.wordService.findByValueAndLanguage(value, foundLanguage).getWordClass());
        if (foundWordClass.isPresent()) {
            return foundWordClass.get().getWordClassName();
        }
        return null;
    }

    public Map<String, Boolean> getWordCollections(String word, String language) throws SQLException {
        Language foundLanguage = this.languageService.findByName(language);
        Word foundWord = this.wordService.findByValueAndLanguage(word, foundLanguage);
        List<String> allCollection = this.wordCollectionService.findAll();
        return this.wordService.getWordCollections(foundWord, allCollection);
    }

    public List<String> getCollectionWords(String collectionName) throws SQLException {
        WordCollection foundCollection = this.wordCollectionService.findByName(collectionName);
        return this.wordService.getCollectionWords(foundCollection);
    }

    public List<String> getCollectionWords(String collectionName, String languageName) throws SQLException {
        WordCollection foundCollection = this.wordCollectionService.findByName(collectionName);
        Language language = this.languageService.findByName(languageName);
        return this.wordService.getCollectionWords(foundCollection, language);
    }


    //-------------- create collection request  ---------------//

    public void createCollection(String name) throws SQLException {
        this.wordCollectionService.create(name);
    }

    //-------------- delete requests ---------------//

    public void deleteWord(String wordValue, String languageName) throws SQLException {
        Language wordLanguage = this.languageService.findByName(languageName);
        Word word = this.wordService.findByValueAndLanguage(wordValue, wordLanguage);
        this.wordService.delete(word);
    }

    public void deleteCollection(String name) throws SQLException {
        this.wordCollectionService.deleteByName(name);
    }


    //-------------- create and update word methods ---------------//

    public void createWord(String wordValue, String wordClassName, String languageName) throws SQLException {
        WordClass wordClass = this.wordClassService.findByName(wordClassName);
        Language language = this.languageService.findByName(languageName);
        this.wordService.create(wordValue, wordClass, language);
    }

    public void updateWord(String wordValue, String wordClassName, String languageName) throws SQLException {
        Language language = this.languageService.findByName(languageName);
        Word word = this.wordService.findByValueAndLanguage(wordValue, language);
        WordClass wordClass = this.wordClassService.findByName(wordClassName);
        this.wordService.update(word, wordClass);
    }


    //-------------- add \ remove meaning methods ---------------//

    public void addMeaning(String word, String wordLanguage, String meaning, String meaningsLanguage) throws SQLException {
        Language foundWordLanguage = this.languageService.findByName(wordLanguage);
        Language foundMeaningLanguage = this.languageService.findByName(meaningsLanguage);
        Word foundWord = this.wordService.findByValueAndLanguage(word, foundWordLanguage);
        Word foundMeaning = this.wordService.findByValueAndLanguage(meaning, foundMeaningLanguage);
        this.wordService.addMeaning(foundWord, foundMeaning);
    }

    public void removeMeaning(String word, String wordLanguage, String meaning, String meaningsLanguage) throws SQLException {
        Language foundWordLanguage = this.languageService.findByName(wordLanguage);
        Language foundMeaningLanguage = this.languageService.findByName(meaningsLanguage);
        Word foundWord = this.wordService.findByValueAndLanguage(word, foundWordLanguage);
        Word foundMeaning = this.wordService.findByValueAndLanguage(meaning, foundMeaningLanguage);
        this.wordService.removeMeaning(foundWord, foundMeaning);
    }


    //-------------- put into \ remove from collection methods ---------------//

    public void putInCollection(String word, String wordLanguage, String collection) throws SQLException {
        Language foundWordLanguage = this.languageService.findByName(wordLanguage);
        Word foundWord = this.wordService.findByValueAndLanguage(word, foundWordLanguage);
        WordCollection foundWordCollection = this.wordCollectionService.findByName(collection);
        this.wordService.putInCollection(foundWord, foundWordCollection);
    }

    public void removeFromCollection(String word, String wordLanguage, String collection) throws SQLException {
        Language foundWordLanguage = this.languageService.findByName(wordLanguage);
        Word foundWord = this.wordService.findByValueAndLanguage(word, foundWordLanguage);
        WordCollection foundWordCollection = this.wordCollectionService.findByName(collection);
        this.wordService.removeFromCollection(foundWord, foundWordCollection);
    }

    public boolean wordExists(String word, String language) throws SQLException {
        Language foundLanguage = this.languageService.findByName(language);
        return this.wordService.wordExists(word, foundLanguage);
    }

    public boolean wordCollectionExists(String collection) throws SQLException {
        return this.wordCollectionService.collectionExists(collection);
    }
}
