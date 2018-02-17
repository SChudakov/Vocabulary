package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.entity.WordCollection;

import java.sql.SQLException;
import java.util.List;

public class WordCollectionDaoHbnImpl implements WordCollectionDao{
    @Override
    public void save(WordCollection wordCollection) throws SQLException {

    }

    @Override
    public WordCollection update(WordCollection wordCollection) throws SQLException {
        return null;
    }

    @Override
    public WordCollection findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public WordCollection findByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<WordCollection> findAll() throws SQLException {
        return null;
    }

    @Override
    public void remove(Integer wordCollectionID) throws SQLException {

    }
}
