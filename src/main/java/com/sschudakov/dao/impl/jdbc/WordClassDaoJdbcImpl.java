package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.desktop.database.DatabaseManager;
import com.sschudakov.entity.WordClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WordClassDaoJdbcImpl implements WordClassDao {

    private static Logger logger = LogManager.getLogger(WordClassDaoJdbcImpl.class);

    //-------------- save  ---------------//

    @Override
    public void save(WordClass wordClass) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("INSERT INTO word_classes ")
                .append("(").append(WordClass.NAME_CN).append(")")
                .append(" VALUES ")
                .append("(").append("\'").append(wordClass.getWordClassName()).append("\'").append(")").append(";");
        logger.info(query);
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(query.toString());
        insertStatement.execute();
    }


    //-------------- find ---------------//

    @Override
    public WordClass findById(Integer id) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_classes ")
                .append(" WHERE ")
                .append(WordClass.ID_CN).append("=").append(id);
        logger.info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWordClass(resultSet);
        /*return DatabaseCache.getInstance().getWordClassBzId(id);*/
    }

    @Override
    public WordClass findByName(String name) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_classes WHERE ")
                .append(WordClass.NAME_CN).append("=").append("\'" + name + "\'");
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }

        WordClass result = formWordClass(resultSet);

        if (resultSet.next()) {
            throw new IllegalArgumentException("multiple word classes match the name " + name);
        }

        return result;
    }

    private WordClass formWordClass(ResultSet resultSet) throws SQLException {
        WordClass result = new WordClass();
        result.setId(resultSet.getInt(WordClass.ID_CN));
        result.setWordClassName(resultSet.getString(WordClass.NAME_CN));
        return result;
    }

    @Override
    public List<WordClass> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_classes");
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWordClasses(resultSet);
    }

    private List<WordClass> formWordClasses(ResultSet resultSet) throws SQLException {
        List<WordClass> result = new ArrayList<>();
        while (resultSet.next()) {
            WordClass wordClass = new WordClass();
            wordClass.setId(resultSet.getInt(WordClass.ID_CN));
            wordClass.setWordClassName(resultSet.getString(WordClass.NAME_CN));
            result.add(wordClass);
        }
        return result;
    }
}
