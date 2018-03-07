package com.sschudakov.service;

import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.factory.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class WordCollectionSrv {

    private WordCollectionDao wordCollectionDao;
    private WCRDao wcrDao;

    public WordCollectionSrv() {
        this.wordCollectionDao = DaoFactory.createWordCollectionDao();
        this.wcrDao = DaoFactory.createWCRDao();
    }


    public void create(String wordCollectionName) throws SQLException {
        if (collectionExists(wordCollectionName)) {
            throw new IllegalArgumentException("Collection with name " + wordCollectionName + " already exists");
        } else {
            this.wordCollectionDao.save(new WordCollection(wordCollectionName));
        }

    }

    public WordCollection update(String oldName, String newName) throws SQLException {
        if (!collectionExists(oldName)) {
            throw new IllegalArgumentException("Collection with name " + oldName + " already exists");
        }
        WordCollection foundCollection = this.wordCollectionDao.findByName(oldName);
        foundCollection.setCollectionName(newName);
        update(foundCollection);
        return foundCollection;
    }

    private WordCollection update(WordCollection wordCollection) throws SQLException {
        return this.wordCollectionDao.update(wordCollection);
    }


    public WordCollection findById(Integer id) throws SQLException {
        return this.wordCollectionDao.findById(id);
    }


    public WordCollection findByName(String name) throws SQLException {
        return this.wordCollectionDao.findByName(name);
    }


    public List<String> findAll() throws SQLException {
        return this.wordCollectionDao.findAll().stream().map(WordCollection::getCollectionName).collect(Collectors.toList());
    }


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

    private boolean collectionExists(String collection) throws SQLException {
        return this.wordCollectionDao.findByName(collection) != null;
    }
}
