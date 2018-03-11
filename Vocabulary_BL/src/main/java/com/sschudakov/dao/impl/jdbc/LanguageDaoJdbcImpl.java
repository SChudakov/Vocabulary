package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDaoJdbcImpl implements LanguageDao {


    //-------------- save  ---------------//

    @Override
    public void save(Language language) throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO languages ")
                .append("(").append(Language.NAME_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append("\'" + language.getLanguageName() + "\'").append(")").append(";");
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }


    //-------------- find  ---------------//

    @Override
    public Language findById(Integer id) throws SQLException {
        StringBuilder selectQuery = new StringBuilder("");
        selectQuery.append("SELECT * FROM languages ")
                .append(" WHERE ")
                .append(Language.ID_COLUMN_NAME).append("=").append(id);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(selectQuery.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formLanguage(resultSet);
    }

    @Override
    public Language findByName(String name) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM languages")
                .append(" WHERE ")
                .append(Language.NAME_COLUMN_NAME).append("=").append("\'" + name + "\'");

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }

        Language result = formLanguage(resultSet);

        if (resultSet.next()) {
            throw new IllegalArgumentException("multiple languages match the name " + name);
        }
        return result;
    }

    private Language formLanguage(ResultSet resultSet) throws SQLException {
        Language result = new Language();
        result.setId(resultSet.getInt(Language.ID_COLUMN_NAME));
        result.setLanguageName(resultSet.getString(Language.NAME_COLUMN_NAME));
        return result;
    }


    @Override
    public List<Language> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM languages");

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formLanguagesList(resultSet);
    }

    private List<Language> formLanguagesList(ResultSet resultSet) throws SQLException {
        List<Language> result = new ArrayList<>();
        while (resultSet.next()) {
            Language language = new Language();
            language.setId(resultSet.getInt(Language.ID_COLUMN_NAME));
            language.setLanguageName(resultSet.getString(Language.NAME_COLUMN_NAME));
            result.add(language);
        }
        return result;
    }
}
