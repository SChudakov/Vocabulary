package com.sschudakov.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;

import java.sql.SQLException;
import java.util.List;

public class LanguageDaoImpl implements LanguageDao{

    private Dao<Language, Integer> languagesDao;

    public LanguageDaoImpl() {
        try {
            this.languagesDao = DaoManager.createDao(DatabaseManager.connectionSource, Language.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Language language) throws SQLException {
        this.languagesDao.create(language);
    }

    public Language update(Language language) throws SQLException {
        this.languagesDao.update(language);
        return language;
    }

    public Language findById(Integer id) throws SQLException {
        return this.languagesDao.queryForId(id);
    }

    public Language findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public List<Language> findAll() throws SQLException {
        return this.languagesDao.queryForAll();
    }

    public void remove(Integer languageID) throws SQLException {
        this.languagesDao.deleteById(languageID);
    }
    
}
