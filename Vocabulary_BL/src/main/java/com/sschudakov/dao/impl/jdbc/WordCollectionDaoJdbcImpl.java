package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordCollection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordCollectionDaoJdbcImpl implements WordCollectionDao {


    //-------------- save  ---------------//

    @Override
    public void save(WordCollection wordCollection) throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_collections ")
                .append("(").append(WordCollection.NAME_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append("\'" + wordCollection.getCollectionName() + "\'").append(")").append(";");
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }


    //-------------- update ---------------//

    @Override
    public WordCollection update(WordCollection wordCollection) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("UPDATE word_collections")
                .append(" SET ")
                .append(WordCollection.NAME_COLUMN_NAME).append("=").append("\'" + wordCollection.getCollectionName() + "\'")
                .append(" WHERE ")
                .append(WordCollection.ID_COLUMN_NAME).append("=").append(wordCollection.getId()).append(";");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        return wordCollection;
    }


    //-------------- find ---------------//

    @Override
    public WordCollection findById(Integer id) throws SQLException {
        StringBuilder selectQuery = new StringBuilder("");
        selectQuery.append("SELECT * FROM word_collections ")
                .append(" WHERE ")
                .append(WordCollection.ID_COLUMN_NAME).append("=").append(id);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(selectQuery.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWordCollection(resultSet);
    }

    @Override
    public WordCollection findByName(String name) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collections")
                .append(" WHERE ")
                .append(WordCollection.NAME_COLUMN_NAME).append("=").append("\'" + name + "\'");

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }

        WordCollection result = formWordCollection(resultSet);

        if (resultSet.next()) {
            throw new IllegalArgumentException("multiple word collections match the name " + name);
        }

        return result;
    }

    private WordCollection formWordCollection(ResultSet resultSet) throws SQLException {
        WordCollection result = new WordCollection();
        result.setId(resultSet.getInt(WordCollection.ID_COLUMN_NAME));
        result.setCollectionName(resultSet.getString(WordCollection.NAME_COLUMN_NAME));
        return result;
    }

    @Override
    public List<WordCollection> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_collections");

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWordCollections(resultSet);
    }

    private List<WordCollection> formWordCollections(ResultSet resultSet) throws SQLException {
        List<WordCollection> result = new ArrayList<>();
        while (resultSet.next()) {
            WordCollection wordCollection = new WordCollection();
            wordCollection.setId(resultSet.getInt(WordCollection.ID_COLUMN_NAME));
            wordCollection.setCollectionName(resultSet.getString(WordCollection.NAME_COLUMN_NAME));
            result.add(wordCollection);
        }
        return result;
    }


    //-------------- remove ---------------//

    @Override
    public void remove(Integer wordCollectionID) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM word_collections")
                .append(" WHERE ")
                .append(WordCollection.ID_COLUMN_NAME).append("=").append(wordCollectionID);

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }
}
