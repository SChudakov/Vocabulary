package com.sschudakov.service;

import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordSrv {

    private WordDao wordDao;
    private WMRDao wmrDao;
    private WCRDao wcrDao;

    public WordSrv(WordDao wordDao, WMRDao wmrDao, WCRDao wcrDao) {
        this.wordDao = wordDao;
        this.wmrDao = wmrDao;
        this.wcrDao = wcrDao;
    }

    //-------------- create  ---------------//

    public void create(String wordValue, WordClass wordClass, Language language) throws SQLException {
        Word word = new Word();
        word.setValue(wordValue);
        word.setLanguage(language);
        if (wordClass != null) {
            word.setWordClass(wordClass);
        } else {
            word.setWordClass(new WordClass(10010, "default"));//TODO: fix this
        }
        this.wordDao.save(word);
    }

    //-------------- update ---------------//

    public Word update(Word word, WordClass wordClass) throws SQLException {
        word.setWordClass(wordClass);
        return this.wordDao.update(word);

    }


    //-------------- find ---------------//

    public Word findById(Integer id) throws SQLException {
        return this.wordDao.findById(id);
    }

    public Word findByValueAndLanguage(String value, Language language) throws SQLException {
        return this.wordDao.findByValueAndLanguage(value, language);
    }

    public List<String> findByLanguage(Language language) throws SQLException {
        return this.wordDao.findByLanguage(language)
                .stream().map(Word::getValue).collect(Collectors.toList());
    }

    public List<String> findAll() throws SQLException {
        return this.wordDao.findAll()
                .stream().map(Word::getValue).collect(Collectors.toList());
    }


    //-------------- delete ---------------//

    public void delete(Word word) throws SQLException {
        delete(word.getId());
    }

    private void delete(Integer wordId) throws SQLException {
        Word foundWord = wordDao.findById(wordId);
        for (WordCollectionRelationship wordCollectionRelationship : wcrDao.findRelationshipsByWord(foundWord)) {
            this.wcrDao.remove(wordCollectionRelationship.getId());
        }
        for (WordMeaningRelationship wordMeaningRelationship : wmrDao.findByWord(foundWord)) {
            this.wmrDao.remove(wordMeaningRelationship.getId());
        }
        for (WordMeaningRelationship wordMeaningRelationship : wmrDao.findByMeaning(foundWord)) {
            this.wmrDao.remove(wordMeaningRelationship.getId());
        }
        this.wordDao.remove(wordId);
    }


    //-------------- get word meanings ---------------//

    public List<String> getWordsMeanings(Word word, Language meaningsLanguage) throws SQLException {
        List<String> result = new ArrayList<>();
        this.wmrDao.findWordMeaningsIds(word, meaningsLanguage)
                .stream().forEach(meaningId -> {
            try {
                result.add(this.wordDao.findById(meaningId).getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return result;
    }


    //-------------- get word collections ---------------//

    public Map<String, Boolean> getWordCollections(Word word, List<String> collections) throws SQLException {
        Map<String, Boolean> collectionsMap = new HashMap<>();

        collections.stream().forEach(a -> collectionsMap.put(a, Boolean.FALSE));

        for (WordCollectionRelationship wcr : this.wcrDao.findRelationshipsByWord(word)) {
            collectionsMap.put(wcr.getWordCollection().getCollectionName(), Boolean.TRUE);
        }

        return collectionsMap;
    }


    //-------------- get collection words ---------------//

    public List<String> getCollectionWords(WordCollection collection) throws SQLException {
        return this.wcrDao.findWordsByCollection(collection)
                .stream().map(wcr -> wcr.getWord().getValue()).collect(Collectors.toList());
    }


    //-------------- add \ remove meaning ---------------//

    public void addMeaning(Word word, Word meaning) throws SQLException {
        if (wordMeaningRelationshipExists(word, meaning)) {
            throw new IllegalArgumentException("Word " + word + " already a has meaning " + meaning);
        } else {
            this.wmrDao.save(new WordMeaningRelationship(word, meaning));
        }
    }

    public void removeMeaning(Word word, Word meaning) throws SQLException {
        if (wordMeaningRelationshipExists(word, meaning)) {
            this.wmrDao.remove(this.wmrDao.findByWordAndMeaning(word, meaning).getId());
        } else {
            throw new IllegalArgumentException("word " + word + " has by now no meaning " + meaning);
        }
    }


    //-------------- put in \ remove from collection ---------------//

    public void putInCollection(Word word, WordCollection wordCollection) throws SQLException {
        if (wordCollectionsRelationshipExists(word, wordCollection)) {
            throw new IllegalArgumentException("word " + word +
                    " is already in " + wordCollection.getCollectionName() + " collection");
        } else {
            this.wcrDao.save(new WordCollectionRelationship(word, wordCollection));
        }
    }

    public void removeFromCollection(Word word, WordCollection wordCollection) throws SQLException {
        if (wordCollectionsRelationshipExists(word, wordCollection)) {

            this.wcrDao.remove(this.wcrDao.findByWordAndCollection(word, wordCollection).getId());
        } else {
            throw new IllegalArgumentException("there is no word " + word +
                    " in " + wordCollection.getCollectionName() + " collection");
        }
    }

    //-------------- exist queries ---------------//

    public boolean wordExists(String word, Language language) throws SQLException {
        return this.wordDao.findByValueAndLanguage(word, language) != null;
    }

    private boolean wordMeaningRelationshipExists(Word word, Word meaning) throws SQLException {
        return this.wmrDao.findByWordAndMeaning(word, meaning) != null;
    }

    private boolean wordCollectionsRelationshipExists(Word word, WordCollection wordCollection) throws SQLException {
        return this.wcrDao.findByWordAndCollection(word, wordCollection) != null;
    }
}
