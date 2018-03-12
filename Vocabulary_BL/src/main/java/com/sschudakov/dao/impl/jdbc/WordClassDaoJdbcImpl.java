package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordClass;
import com.sschudakov.logging.LoggersManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordClassDaoJdbcImpl implements WordClassDao {


    //-------------- save  ---------------//

    @Override
    public void save(WordClass wordClass) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("INSERT INTO word_classes ")
                .append("(").append(WordClass.NAME_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append("\'" + wordClass.getWordClassName() + "\'").append(")").append(";");
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(query.toString());
        insertStatement.execute();
    }


    //-------------- find ---------------//

    @Override
    public WordClass findById(Integer id) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_classes ")
                .append(" WHERE ")
                .append(WordClass.ID_COLUMN_NAME).append("=").append(id);
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWordClass(resultSet);
    }

    @Override
    public WordClass findByName(String name) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_classes WHERE ")
                .append(WordClass.NAME_COLUMN_NAME).append("=").append("\'" + name + "\'");
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }

        WordClass result = formWordClass(resultSet);

        if(resultSet.next()){
            throw new IllegalArgumentException("multiple word classes match the name " + name);
        }

        return result;
    }

    private WordClass formWordClass(ResultSet resultSet) throws SQLException {
        WordClass result = new WordClass();
        result.setId(resultSet.getInt(WordClass.ID_COLUMN_NAME));
        result.setWordClassName(resultSet.getString(WordClass.NAME_COLUMN_NAME));
        return result;
    }

    @Override
    public List<WordClass> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_classes");
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWordClasses(resultSet);
    }

    private List<WordClass> formWordClasses(ResultSet resultSet) throws SQLException {
        List<WordClass> result = new ArrayList<>();
        while (resultSet.next()) {
            WordClass wordClass = new WordClass();
            wordClass.setId(resultSet.getInt(WordClass.ID_COLUMN_NAME));
            wordClass.setWordClassName(resultSet.getString(WordClass.NAME_COLUMN_NAME));
            result.add(wordClass);
        }
        return result;
    }
}
