package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.database.DatabaseCache;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;
import com.sschudakov.logging.LoggersManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class LanguageDaoJdbcImpl implements LanguageDao {


    //-------------- save  ---------------//

    @Override
    public void save(Language language) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("INSERT INTO languages ")
                .append("(").append(Language.NAME_CN).append(")")
                .append(" VALUES ")
                .append("(").append("\'" + language.getLanguageName() + "\'").append(")").append(";");
        LoggersManager.getSqlLogger().info(query);
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(query.toString());
        insertStatement.execute();
    }


    //-------------- find  ---------------//

    @Override
    public Language findById(Integer id) throws SQLException {
        /*StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM languages ")
                .append(" WHERE ")
                .append(Language.ID_CN).append("=").append(id);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query.toString());
        selectStatement.execute();
        LoggersManager.getSqlLogger().info(query);
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formLanguage(resultSet);*/
        return DatabaseCache.getInstance().getLanguageBzId(id);
    }

    @Override
    public Language findByName(String name) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM languages")
                .append(" WHERE ")
                .append(Language.NAME_CN).append("=").append("\'" + name + "\'");
        LoggersManager.getSqlLogger().info(query);
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
        result.setId(resultSet.getInt(Language.ID_CN));
        result.setLanguageName(resultSet.getString(Language.NAME_CN));
        return result;
    }


    @Override
    public List<Language> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM languages");
        LoggersManager.getSqlLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formLanguagesList(resultSet);
    }

    private List<Language> formLanguagesList(ResultSet resultSet) throws SQLException {
        List<Language> result = new ArrayList<>();
        while (resultSet.next()) {
            Language language = new Language();
            language.setId(resultSet.getInt(Language.ID_CN));
            language.setLanguageName(resultSet.getString(Language.NAME_CN));
            result.add(language);
        }
        return result;
    }
}
