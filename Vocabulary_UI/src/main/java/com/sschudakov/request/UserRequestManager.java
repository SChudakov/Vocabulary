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
    public List<String> getMeaningsByWord(String word, String wordLanguage, String meaningsLanguage) throws SQLException {
        List<String> result = new ArrayList<>();
        for (Word w : this.wmrService.findWordMeanings(word, wordLanguage, meaningsLanguage)) {
            result.add(w.getValue());
        }
        return result;
    }

    public String getClassByWord(String word, String language) throws SQLException {
        return wordService.findByValueAndLanguage(word, language).getWordClass().getWordClassName();
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
    public boolean createWord(String word, String language) throws SQLException {
        if (wordExists(word, language)) {
            return false;
        }
        this.wordService.create(word, "???", language); //TODO: default word class?
        return true;
    }

    public boolean deleteWord(String word, String language) throws SQLException {
        if (!wordExists(word, language)) {
            return false;
        }
        wordService.delete(wordService.findByValueAndLanguage(word, language).getWordID());
        return true;
    }

    public boolean createCollection(String name) throws SQLException {
        if (collectionExists(name)) {
            return false;
        }
        wordCollectionService.create(name);
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
        if (!wordExists(word, language) || !wordExists(meaning, meaningLanguage)) {
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
