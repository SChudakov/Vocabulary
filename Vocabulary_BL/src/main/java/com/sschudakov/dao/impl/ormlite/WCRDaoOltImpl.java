package com.sschudakov.dao.impl.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WCRDaoImpl implements WCRDao {
    private Dao<WordCollectionRelationship, Integer> wordCollectionRelationshipsDao;

    public WCRDaoImpl() {
        try {
            this.wordCollectionRelationshipsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordCollectionRelationship.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        this.wordCollectionRelationshipsDao.create(wordCollectionRelationship);
    }

    @Override
    public WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        this.wordCollectionRelationshipsDao.update(wordCollectionRelationship);
        return wordCollectionRelationship;
    }

    @Override
    public WordCollectionRelationship findById(Integer id) throws SQLException {
        return this.wordCollectionRelationshipsDao.queryForId(id);
    }

    @Override
    public Collection<WordCollectionRelationship> findByWord(String word) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word collection relationship WHERE " +
                                WordCollectionRelationship.WORD_COLUMN_NAME + " = " + "\'" + word + "\'"
                );
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }

    @Override
    public Collection<WordCollectionRelationship> findByCollection(String collectionsName) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM 'word collection relationship' WHERE " +
                                WordCollectionRelationship.COLLECTION_COLUMN_NAME + "=" + collectionsName
                );
        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }

    private Collection<WordCollectionRelationship> formWCRCollection(ResultSet resultSet) throws SQLException {
        Collection<WordCollectionRelationship> result = new ArrayList<>();
        WordCollectionRelationship wcr;
        while (resultSet.next()) {
            wcr = new WordCollectionRelationship();
            wcr.setWordCollectionRelationshipID(resultSet.getInt(WordCollectionRelationship.ID_COLUMN_NAME));
            /*wcr.setWord();
            wcr.setWordCollection();*/
            result.add(wcr);
        }
        //TODO: implement
        /*return result;*/
        throw new UnsupportedOperationException();
    }

    @Override
    public List<WordCollectionRelationship> findAll() throws SQLException {
        return this.wordCollectionRelationshipsDao.queryForAll();
    }

    @Override
    public void remove(Integer wordCollectionRelationshipID) throws SQLException {
        this.wordCollectionRelationshipsDao.deleteById(wordCollectionRelationshipID);
    }
}
