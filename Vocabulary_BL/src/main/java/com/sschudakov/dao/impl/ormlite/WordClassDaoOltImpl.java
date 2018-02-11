package com.sschudakov.dao.impl.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WordClassDaoImpl implements WordClassDao {
    private Dao<WordClass, Integer> wordClassDao;

    public WordClassDaoImpl() {
        try {
            this.wordClassDao = DaoManager.createDao(DatabaseManager.connectionSource, WordClass.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(WordClass wordClass) throws SQLException {
        this.wordClassDao.create(wordClass);
    }


    @Override
    public WordClass findById(Integer id) throws SQLException {
        return this.wordClassDao.queryForId(id);
    }

    @Override
    public WordClass findByName(String name) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word_classes WHERE " +
                                WordClass.CLASS_NAME_COLUMN_NAME + " = " + "\'" + name + "\'"
                );
        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        if (!resultSet.next()) {
            return null;
        }

        return formWordClass(resultSet, name);
    }

    private WordClass formWordClass(ResultSet resultSet, String name) throws SQLException {
        WordClass result = new WordClass();
        result.setWordClassID(resultSet.getInt(WordClass.ID_COLUMN_NAME));
        result.setWordClassName(name);
        return result;
    }

    @Override
    public List<WordClass> findAll() throws SQLException {
        return this.wordClassDao.queryForAll();
    }


    /**
     * Data about word classes never been changed or removed.
     * So update and remove operations are not present in this DAO.
     */
}
