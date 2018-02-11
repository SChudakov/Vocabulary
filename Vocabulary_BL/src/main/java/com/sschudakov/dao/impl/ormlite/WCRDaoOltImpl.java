package com.sschudakov.dao.impl.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WCRDaoOltImpl implements WCRDao {
    private Dao<WordCollectionRelationship, Integer> wordCollectionRelationshipsDao;
    private WordDao wordDao;
    private WordCollectionDao wordCollectionDao;

    public WCRDaoOltImpl() {
        try {
            this.wordCollectionRelationshipsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordCollectionRelationship.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.wordDao = new WordDaoOltImpl();
        this.wordCollectionDao = new WordCollectionDaoOltImpl();
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
    public Collection<WordCollectionRelationship> findByWordId(int wordId) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word_collection_relationship WHERE " +
                                WordCollectionRelationship.WORD_COLUMN_NAME + " = " + "\'" + wordId + "\'"
                );
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }

    @Override
    public Collection<WordCollectionRelationship> findByCollection(int collectionId) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word_collection_relationship WHERE " +
                                WordCollectionRelationship.COLLECTION_COLUMN_NAME + "=" + collectionId
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
            wcr.setWord(
                    this.wordDao.findById(
                            resultSet.getInt(WordCollectionRelationship.WORD_COLUMN_NAME)));
            wcr.setWordCollection(
                    this.wordCollectionDao.findById(
                            resultSet.getInt(WordCollectionRelationship.COLLECTION_COLUMN_NAME)));
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
