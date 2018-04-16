package com.sschudakov.desktop.fx.dao;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.entity.WordMeaningRelationship;
import com.sschudakov.desktop.logging.LoggersManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FXAppDao {

    //-------------- helping dao objects ---------------//

    private LanguageDao languageDao;
    private WordClassDao wordClassDao;


    //-------------- constructor ---------------//

    public FXAppDao(LanguageDao languageDao, WordClassDao wordClassDao) {
        this.languageDao = languageDao;
        this.wordClassDao = wordClassDao;
    }


    //-------------- parametrized aueries ---------------//

    public List<Word> findWordsByNumOfMeanings(int numOfMeanings) throws SQLException {
        String query = "\nSELECT * \n" +
                "FROM words \n" +
                "WHERE \n" +
                "\t(SELECT COUNT(" + WordMeaningRelationship.MEANING_COLUMN_NAME + ") \n" +
                "\tFROM word_meaning_relationships \n" +
                "\tWHERE " + WordMeaningRelationship.WORD_COLUMN_NAME + " = words.word_id)=?";
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query);
        selectStatement.setInt(1, numOfMeanings);
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();
        return formWordsList(resultSet);
    }

    public List<Word> findWordsByMinNumOfMeanings(int minNumOfMeanings) throws SQLException {
        String query = "\nSELECT * \n" +
                "FROM words \n" +
                "WHERE \n" +
                "\t(SELECT COUNT(" + WordMeaningRelationship.MEANING_COLUMN_NAME + ") \n" +
                "\tFROM word_meaning_relationships \n" +
                "\tWHERE " + WordMeaningRelationship.WORD_COLUMN_NAME + " = words.word_id)>=?";
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query);
        selectStatement.setInt(1, minNumOfMeanings);
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();
        return formWordsList(resultSet);
    }

    public List<Word> findWordsByMeaning(String meaning) throws SQLException {
        String query = "\nSELECT * \n" +
                "FROM words W  \n" +
                "WHERE EXISTS \n" +
                "\t(SELECT * \n" +
                "\tFROM word_meaning_relationships INNER JOIN words \n" +
                "\tON " + WordMeaningRelationship.MEANING_COLUMN_NAME + " = " + Word.ID_CN + " \n" +
                "\tWHERE " + WordMeaningRelationship.WORD_COLUMN_NAME + " = W." + Word.ID_CN + " \n" +
                "\tAND value = ?)";
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query);
        selectStatement.setString(1, meaning);
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();
        return formWordsList(resultSet);
    }

    public List<Word> findWordsByCollectionsName(String collectionsName) throws SQLException {
        String query = "\nSELECT * \n" +
                "FROM words \n" +
                "WHERE EXISTS \n" +
                "\t(SELECT * \n" +
                "\tFROM word_collection_relationships INNER JOIN word_collections \n" +
                "\tON " + WordCollectionRelationship.COLLECTION_CN + " = " + WordCollection.ID_CN + " \n" +
                "\tWHERE " + WordCollectionRelationship.WORD_CN + " = " + Word.ID_CN + " \n" +
                "\tAND " + WordCollection.NAME_CN + " = ?)";
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query);
        selectStatement.setString(1, collectionsName);
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();
        return formWordsList(resultSet);
    }

    public List<Word> findWordsByLanguageName(String languageName) throws SQLException {
        String query = "\nSELECT * \n" +
                "FROM words INNER JOIN languages \n" +
                "ON " + Word.LANGUAGE_CN + " = " + Language.ID_CN + " \n" +
                "WHERE " + Language.NAME_CN + " = ?";
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query);
        selectStatement.setString(1, languageName);
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();
        return formWordsList(resultSet);
    }


    //-------------- set comparison queries ---------------//

    public List<Word> findWordsWithAtLeastSameMeaningsSet(String word) throws SQLException {
        String query = "\nSELECT *\n" +
                "FROM words\n" +
                "WHERE NOT EXISTS\n" +
                "\t(SELECT *\n" +
                "\tFROM word_meaning_relationships\n" +
                "\tWHERE " + WordMeaningRelationship.WORD_COLUMN_NAME + " = " + Word.ID_CN + "\n" +
                "\tAND " + WordMeaningRelationship.MEANING_COLUMN_NAME + " NOT IN\n" +
                "\t\t(SELECT WMR." + WordMeaningRelationship.MEANING_COLUMN_NAME + "\n" +
                "\t\tFROM word_meaning_relationships WMR INNER JOIN words W \n" +
                "\t\tON W." + Word.ID_CN + " = WMR." + WordMeaningRelationship.WORD_COLUMN_NAME + "\n" +
                "\t\tWHERE W." + Word.VALUE_CN + " = ?))";
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query);
        selectStatement.setString(1, word);
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();
        return formWordsList(resultSet);
    }

    public List<Word> findWordsWithSameCollectionsSet(String word) throws SQLException {
        String query = "\nSELECT *\n" +
                "FROM words\n" +
                "WHERE NOT EXISTS \n" +
                "\t(SELECT *\n" +
                "\tFROM word_collection_relationships\n" +
                "\tWHERE " + WordCollectionRelationship.WORD_CN + " = " + Word.ID_CN + "\n" +
                "\tAND " + WordCollectionRelationship.COLLECTION_CN + " NOT IN\n" +
                "\t\t(SELECT WCR1." + WordCollectionRelationship.COLLECTION_CN + "\n" +
                "\t\tFROM word_collection_relationships WCR1 INNER JOIN words W1 \n" +
                "\t\tON W1." + Word.ID_CN + " = WCR1." + WordCollectionRelationship.WORD_CN + "\n" +
                "\t\tWHERE W1." + Word.VALUE_CN + " = ?))\n" +
                "AND NOT EXISTS\n" +
                "\t(SELECT WCR2." + WordCollectionRelationship.COLLECTION_CN + "\n" +
                "\tFROM word_collection_relationships WCR2 INNER JOIN words W2 \n" +
                "\tON W2." + Word.ID_CN + " = WCR2." + WordCollectionRelationship.WORD_CN + "\n" +
                "\tWHERE W2." + Word.VALUE_CN + " = ?\n" +
                "\tAND WCR2." + WordCollectionRelationship.COLLECTION_CN + " NOT IN\n" +
                "\t\t(SELECT WCR3." + WordCollectionRelationship.COLLECTION_CN + "\n" +
                "\t\tFROM word_collection_relationships WCR3\n" +
                "\t\tWHERE WCR3." + WordCollectionRelationship.WORD_CN + " = " + Word.ID_CN + "))";
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query);
        selectStatement.setString(1, word);
        selectStatement.setString(2, word);
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();
        return formWordsList(resultSet);
    }

    public List<Word> findWordsWithMeaningsInCommon(String word) throws SQLException {
        String query = "\nSELECT *\n" +
                "FROM words\n" +
                "WHERE NOT EXISTS \n" +
                "\t(SELECT *\n" +
                "\tFROM word_meaning_relationships\n" +
                "\tWHERE " + WordMeaningRelationship.WORD_COLUMN_NAME + " = " + Word.ID_CN + "\n" +
                "\tAND " + WordMeaningRelationship.MEANING_COLUMN_NAME + " IN\n" +
                "\t\t(SELECT WMR." + WordMeaningRelationship.MEANING_COLUMN_NAME + "\n" +
                "\t\tFROM word_meaning_relationships WMR INNER JOIN words W \n" +
                "\t\tON W." + Word.ID_CN + " = WMR." + WordMeaningRelationship.WORD_COLUMN_NAME + "\n" +
                "\t\tWHERE W." + Word.VALUE_CN + " = ?))";
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query);
        selectStatement.setString(1, word);
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();
        return formWordsList(resultSet);
    }

    private List<Word> formWordsList(ResultSet resultSet) throws SQLException {
        List<Word> result = new ArrayList<>();
        Word word;
        while (resultSet.next()) {
            word = new Word();
            word.setId(resultSet.getInt(Word.ID_CN));
            word.setValue(resultSet.getString(Word.VALUE_CN));
            word.setWordClass(this.wordClassDao.findById(resultSet.getInt(Word.WORD_CLASS_CN)));
            word.setLanguage(this.languageDao.findById(resultSet.getInt(Word.LANGUAGE_CN)));
            result.add(word);
        }
        return result;
    }

}
