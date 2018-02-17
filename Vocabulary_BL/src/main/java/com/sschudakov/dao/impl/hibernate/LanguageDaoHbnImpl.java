package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.entity.Language;

import java.sql.SQLException;
import java.util.List;

public class LanguageDaoHbnImpl implements LanguageDao{
    @Override
    public void save(Language language) throws SQLException {

    }

    @Override
    public Language findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Language findByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<Language> findAll() throws SQLException {
        return null;
    }
}
