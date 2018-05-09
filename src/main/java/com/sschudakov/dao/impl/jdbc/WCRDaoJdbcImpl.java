package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.desktop.database.DatabaseManager;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WCRDaoJdbcImpl implements WCRDao {

    private static Logger logger = LogManager.getLogger(WCRDaoJdbcImpl.class);

    //-------------- helping dao objects ---------------//

    private WordDao wordDao;
    private WordCollectionDao wordCollectionDao;


    //-------------- constructor ---------------//

    public WCRDaoJdbcImpl(WordDao wordDao, WordCollectionDao wordCollectionDao) {
        this.wordDao = wordDao;
        this.wordCollectionDao = wordCollectionDao;
    }


    //-------------- save  ---------------//

    @Override
    public void save(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("INSERT INTO word_collection_relationships")
                .append("(").append(WordCollectionRelationship.WORD_CN).append(",")
                .append(WordCollectionRelationship.COLLECTION_CN).append(")")
                .append(" VALUES ")
                .append("(").append(wordCollectionRelationship.getWord().getId()).append(",")
                .append(wordCollectionRelationship.getWordCollection().getId()).append(")").append(";");
        logger.info(query);
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(query.toString());
        insertStatement.execute();
    }


    //-------------- update ---------------//

    @Override
    public WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("UPDATE word_collection_relationships")
                .append(" SET ")
                .append(WordCollectionRelationship.WORD_CN).append("=").append(wordCollectionRelationship.getWord().getId()).append(",")
                .append(WordCollectionRelationship.COLLECTION_CN).append("=").append(wordCollectionRelationship.getWordCollection().getId())
                .append(" WHERE ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(wordCollectionRelationship.getId()).append(";");
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        return wordCollectionRelationship;
    }


    //-------------- find ---------------//

    @Override
    public WordCollectionRelationship findById(Integer id) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(id);
        logger.info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWCR(resultSet);
    }

    @Override
    public List<WordCollectionRelationship> findRelationshipsByWord(Word word) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships WHERE ")
                .append(WordCollectionRelationship.WORD_CN).append("=").append(word.getId());
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }

    @Override
    public List<WordCollectionRelationship> findByCollection(WordCollection collection) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships WHERE ")
                .append(WordCollectionRelationship.COLLECTION_CN).append("=").append(collection.getId());
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }

    @Override
    public List<WordCollectionRelationship> findByCollectionAndLanguage(WordCollection collection, Language language) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT *")
                .append(" FROM ")
                .append("word_collection_relationships INNER JOIN words")
                .append(" ON ")
                .append(WordCollectionRelationship.WORD_CN).append("=").append(Word.ID_CN)
                .append(" WHERE ")
                .append(WordCollectionRelationship.COLLECTION_CN).append("=").append(collection.getId())
                .append(" AND ")
                .append(Word.LANGUAGE_CN).append("=").append(language.getId());
        logger.info(query);
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
            wcr.setId(resultSet.getInt(WordCollectionRelationship.ID_CN));
            wcr.setWord(
                    this.wordDao.findById(
                            resultSet.getInt(WordCollectionRelationship.WORD_CN)));
            wcr.setWordCollection(
                    this.wordCollectionDao.findById(
                            resultSet.getInt(WordCollectionRelationship.COLLECTION_CN)));
            result.add(wcr);
        }
        return result;
    }

    @Override
    public WordCollectionRelationship findByWordAndCollection(Word word, WordCollection collection) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.WORD_CN).append("=").append(word.getId())
                .append(" AND ")
                .append(WordCollectionRelationship.COLLECTION_CN).append("=").append(collection.getId());
        logger.info(query);
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
        result.setId(resultSet.getInt(WordCollectionRelationship.ID_CN));
        result.setWord(this.wordDao.findById(
                resultSet.getInt(WordCollectionRelationship.WORD_CN)
        ));
        result.setWordCollection(this.wordCollectionDao.findById(
                resultSet.getInt(WordCollectionRelationship.COLLECTION_CN)
        ));
        return result;
    }


    @Override
    public List<WordCollectionRelationship> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collection_relationships");
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWCRCollection(resultSet);
    }


    //-------------- remove ---------------//

    @Override
    public void remove(Integer wordCollectionRelationshipID) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(wordCollectionRelationshipID);
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }
}
