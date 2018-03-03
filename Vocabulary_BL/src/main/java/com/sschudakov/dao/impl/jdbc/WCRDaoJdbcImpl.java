package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WCRDaoJdbcImpl implements WCRDao {
    private WordDao wordDao;
    private WordCollectionDao wordCollectionDao;

    public WCRDaoJdbcImpl() {
        this.wordDao = new WordDaoJdbcImpl();
        this.wordCollectionDao = new WordCollectionDaoJdbcImpl();
    }


    @Override
    public void save(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_collection_relationships")
                .append("(").append(WordCollectionRelationship.WORD_COLUMN_NAME).append(",")
                .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append(wordCollectionRelationship.getWord().getId()).append(",")
                .append(wordCollectionRelationship.getWordCollection().getId()).append(")").append(";");
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }


    @Override
    public WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("UPDATE word_collection_relationships")
                .append(" SET ")
                .append(WordCollectionRelationship.WORD_COLUMN_NAME).append("=").append(wordCollectionRelationship.getWord().getId()).append(",")
                .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append("=").append(wordCollectionRelationship.getWordCollection().getId())
                .append(" WHERE ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(wordCollectionRelationship.getId()).append(";");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        return wordCollectionRelationship;
    }


    @Override
    public WordCollectionRelationship findById(Integer id) throws SQLException {
        StringBuilder selectQuery = new StringBuilder("");
        selectQuery.append("SELECT * FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(id);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(selectQuery.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWCR(resultSet);
    }

    @Override
    public List<WordCollectionRelationship> findByWord(Word word) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships WHERE ")
                .append(WordCollectionRelationship.WORD_COLUMN_NAME).append("=").append(word.getId());

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }

    @Override
    public List<WordCollectionRelationship> findByCollection(WordCollection collection) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships WHERE ")
                .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append("=").append(collection.getId());

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }


    private List<WordCollectionRelationship> formWCRCollection(ResultSet resultSet) throws SQLException {
        List<WordCollectionRelationship> result = new ArrayList<>();
        WordCollectionRelationship wcr;
        while (resultSet.next()) {
            wcr = new WordCollectionRelationship();
            wcr.setId(resultSet.getInt(WordCollectionRelationship.ID_COLUMN_NAME));
            wcr.setWord(
                    this.wordDao.findById(
                            resultSet.getInt(WordCollectionRelationship.WORD_COLUMN_NAME)));
            wcr.setWordCollection(
                    this.wordCollectionDao.findById(
                            resultSet.getInt(WordCollectionRelationship.COLLECTION_COLUMN_NAME)));
            result.add(wcr);
        }
        return result;
    }

    @Override
    public WordCollectionRelationship findByWordAndCollection(Word word, WordCollection collection) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.WORD_COLUMN_NAME).append("=").append(word.getId())
                .append(" AND ")
                .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append("=").append(collection.getId());

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        if (!resultSet.next()) {
            return null;
        }
        return formWCR(resultSet);
    }

    private WordCollectionRelationship formWCR(ResultSet resultSet) throws SQLException {
        WordCollectionRelationship result = new WordCollectionRelationship();
        result.setId(resultSet.getInt(WordCollectionRelationship.ID_COLUMN_NAME));
        result.setWord(this.wordDao.findById(
                resultSet.getInt(WordCollectionRelationship.WORD_COLUMN_NAME)
        ));
        result.setWordCollection(this.wordCollectionDao.findById(
                resultSet.getInt(WordCollectionRelationship.COLLECTION_COLUMN_NAME)
        ));
        return result;
    }


    @Override
    public List<WordCollectionRelationship> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }

    @Override
    public void remove(Integer wordCollectionRelationshipID) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(wordCollectionRelationshipID);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }
}
