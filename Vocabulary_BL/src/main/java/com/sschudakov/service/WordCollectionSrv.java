package com.sschudakov.service;

import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class WordCollectionSrv {


    //-------------- dao objects  ---------------//

    private WordCollectionDao wordCollectionDao;
    private WCRDao wcrDao;


    //-------------- constructor  ---------------//

    public WordCollectionSrv(WordCollectionDao wordCollectionDao, WCRDao wcrDao) {
        this.wordCollectionDao = wordCollectionDao;
        this.wcrDao = wcrDao;
    }


    //-------------- create  ---------------//

    public void create(String wordCollectionName) throws SQLException {
        if (collectionExists(wordCollectionName)) {
            throw new IllegalArgumentException("Collection with name " + wordCollectionName + " already exists");
        } else {
            this.wordCollectionDao.save(new WordCollection(wordCollectionName));
        }

    }


    //-------------- update ---------------//

    private WordCollection update(WordCollection wordCollection) throws SQLException {
        return this.wordCollectionDao.update(wordCollection);
    }


    //-------------- find ---------------//

    public WordCollection findById(Integer id) throws SQLException {
        return this.wordCollectionDao.findById(id);
    }


    public WordCollection findByName(String name) throws SQLException {
        return this.wordCollectionDao.findByName(name);
    }


    public List<String> findAll() throws SQLException {
        return this.wordCollectionDao.findAll().stream().map(WordCollection::getCollectionName).collect(Collectors.toList());
    }


    //-------------- delete ---------------//

    public void deleteByName(String name) throws SQLException {
        if (collectionExists(name)) {
            delete(this.wordCollectionDao.findByName(name).getId());
        } else {
            throw new IllegalArgumentException("There is no collection with the name " + name);
        }
    }

    private void delete(Integer collectionsId) throws SQLException {
        for (WordCollectionRelationship wordCollectionRelationship : this.wcrDao.findByCollection(
                wordCollectionDao.findById(collectionsId)
        )) {
            this.wcrDao.remove(wordCollectionRelationship.getId());
        }
        this.wordCollectionDao.remove(collectionsId);
    }


    //-------------- exist query ---------------//

    public boolean collectionExists(String collection) throws SQLException {
        return this.wordCollectionDao.findByName(collection) != null;
    }
}
