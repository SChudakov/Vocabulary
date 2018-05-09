package com.sschudakov.service;

import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.entity.WordMeaningRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordSrv {

    private WordDao wordDao;
    private WMRDao wmrDao;
    private WCRDao wcrDao;


    //-------------- constructor  ---------------//

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
            word.setWordClass(new WordClass(9, "expression"));//TODO: fix this
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

    public List<String> getWordMeanings(Word word, Language meaningsLanguage) throws SQLException {
        List<String> result = this.wmrDao.findWordMeanings(word, meaningsLanguage)
                .stream().map(Word::getValue).collect(Collectors.toList());
        return result;
    }

    public Map<String, List<String>> getWordsMeanings(Language wordsLanguage, Language meaningsLanguage) throws SQLException {
        Map<String, List<String>> result = new HashMap<>();
        List<Word> wordsList = this.wordDao.findByLanguage(wordsLanguage);
        this.wmrDao.findWordsMeanings(wordsList, meaningsLanguage)
                .entrySet().forEach(entry ->
                result.put(
                        entry.getKey().getValue(),
                        entry.getValue().stream().map(Word::getValue).collect(Collectors.toList())
                ));
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
        return this.wcrDao.findByCollection(collection)
                .stream().map(wcr -> wcr.getWord().getValue()).collect(Collectors.toList());
    }

    public List<String> getCollectionWords(WordCollection collection, Language language) throws SQLException {
        return this.wcrDao.findByCollectionAndLanguage(collection, language)
                .stream().map(a -> a.getWord().getValue()).collect(Collectors.toList());
    }


    //-------------- add \ remove meaning ---------------//

    public void addMeaning(Word word, Word meaning) throws SQLException {
        if (wordMeaningRelationshipExists(word, meaning)) {
            throw new IllegalArgumentException("Word " + word.getValue() +
                    " already a has meaning " + meaning.getValue());
        } else {
            this.wmrDao.save(new WordMeaningRelationship(word, meaning));
            this.wmrDao.save(new WordMeaningRelationship(meaning, word));
        }
    }

    public void removeMeaning(Word word, Word meaning) throws SQLException {
        if (wordMeaningRelationshipExists(word, meaning)) {
            this.wmrDao.remove(this.wmrDao.findByWordAndMeaning(word, meaning).getId());
            this.wmrDao.remove(this.wmrDao.findByWordAndMeaning(meaning, word).getId());
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
