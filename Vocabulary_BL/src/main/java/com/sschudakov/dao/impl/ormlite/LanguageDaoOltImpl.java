package com.sschudakov.dao.impl.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LanguageDaoOltImpl implements LanguageDao {

    private Dao<Language, Integer> languagesDao;

    public LanguageDaoOltImpl() {
        try {
            this.languagesDao = DaoManager.createDao(DatabaseManager.connectionSource, Language.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Language language) throws SQLException {
        this.languagesDao.create(language);
    }

    @Override
    public Language findById(Integer id) throws SQLException {
        return this.languagesDao.queryForId(id);
    }

    @Override
    public Language findByName(String name) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM languages WHERE " +
                                Language.NAME_FIELD_COLUMN_NAME + " = " + "\'" + name + "\'"
                );
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        if (!resultSet.next()) {
            return null;
        }
        return formLanguage(resultSet, name);
    }

    private Language formLanguage(ResultSet resultSet, String name) throws SQLException {
        Language result = new Language();
        result.setLanguageID(resultSet.getInt(Language.ID_FIELD_COLUMN_NAME));
        result.setLanguageName(name);
        return result;
    }


    @Override
    public List<Language> findAll() throws SQLException {
        return this.languagesDao.queryForAll();
    }

    /**
     * Data about languages should never been changed or removed.
     * So update and remove operations are not present in this DAO.
     */
}
