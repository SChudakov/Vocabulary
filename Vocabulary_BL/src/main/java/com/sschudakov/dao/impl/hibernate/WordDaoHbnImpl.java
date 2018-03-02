package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class WordDaoHbnImpl implements WordDao{

    @Override
    public void save(Word word) throws SQLException {

    }

    @Override
    public Word update(Word word) throws SQLException {
        return null;
    }

    @Override
    public Word findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Word findByValueAndLanguageId(String value, Integer languageId) throws SQLException {
        return null;
    }

    @Override
    public List<Word> findByLanguageId(Integer languageId) throws SQLException {
        return null;
    }

    @Override
    public Collection<Word> findAll() throws SQLException {
        return null;
    }

    @Override
    public void remove(Integer wordID) throws SQLException {

    }
}
