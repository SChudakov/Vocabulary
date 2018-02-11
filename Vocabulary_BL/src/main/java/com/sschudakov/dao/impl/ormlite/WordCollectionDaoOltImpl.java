package com.sschudakov.dao.impl.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordCollection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WordCollectionDaoImpl implements WordCollectionDao {
    private Dao<WordCollection, Integer> wordCollectionsDao;

    public WordCollectionDaoImpl() {
        try {
            this.wordCollectionsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordCollection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(WordCollection wordCollection) throws SQLException {
        this.wordCollectionsDao.create(wordCollection);
    }

    @Override
    public WordCollection update(WordCollection wordCollection) throws SQLException {
        this.wordCollectionsDao.update(wordCollection);
        return wordCollection;
    }

    @Override
    public WordCollection findById(Integer id) throws SQLException {
        return this.wordCollectionsDao.queryForId(id);
    }

    @Override
    public WordCollection findByName(String name) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word_collection_relationships WHERE " +
                                WordCollection.COLLECTION_NAME_COLUMN_NAME + " = " + "\'" + name + "\'"
                );
        System.out.println("SELECT * FROM word_collection_relationships WHERE " +
                WordCollection.COLLECTION_NAME_COLUMN_NAME + " = " + "\'" + name + "\'");
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        if (!resultSet.next()) {
            return null;
        }
        return formWordCollection(resultSet, name);
    }

    private WordCollection formWordCollection(ResultSet resultSet, String name) throws SQLException {
        WordCollection result = new WordCollection();
        result.setCollectionID(resultSet.getInt(WordCollection.ID_COLUMN_NAME));
        result.setCollectionName(name);
        return result;
    }

    @Override
    public List<WordCollection> findAll() throws SQLException {
        return this.wordCollectionsDao.queryForAll();
    }

    @Override
    public void remove(Integer wordCollectionID) throws SQLException {
        this.wordCollectionsDao.deleteById(wordCollectionID);
    }
}
