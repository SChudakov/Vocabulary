package com.sschudakov.service

import com.sschudakov.database.DatabaseManager
import com.sschudakov.entity.*
import spock.lang.Shared
import spock.lang.Specification

import java.sql.PreparedStatement
import java.sql.SQLException

class WordSrvSpec extends Specification {
    //--------------- language ------------------//
    private static final int unifiedLanguageId = 10000
    private static final String unifiedLanguage = "unified language"

    //--------------- word classes ------------------//
    private static final int firstWordClassId = 10000
    private static final int secondWordClassId = 10001
    private static final String firstWordClass = "first word class"
    private static final String secondWordClass = "second word class"
    //--------------- collections ------------------//
    private static final int firstCollectionId = 10000
    private static final int secondCollectionId = 10001
    private static final int thirdCollectionId = 10002
    private static final String firstCollection = "collection 1"
    private static final String secondCollection = "collection 2"
    private static final String thirdCollection = "collection 3"
    //--------------- words ------------------//
    private static final int wordId = 10000
    private static final String wordValue = "vollkommenn"
    private static final int wordClass = 10000
    private static final int wordLanguage = unifiedLanguageId

    private static final int firstMeaningId = 10001
    private static final String firstMeaningValue = "полныйй"
    private static final int firstMeaningClass = 10000
    private static final int firstMeaningLanguage = unifiedLanguageId

    private static final int secondMeaningId = 10002
    private static final String secondMeaningValue = "абсоютныйй"
    private static final int secondMeaningClass = 10000
    private static final int secondMeaningLanguage = unifiedLanguageId

    private static final int thirdMeaningId = 10003
    private static final String thirdMeaningValue = "совершенныйй"
    private static final int thirdMeaningClass = 10000
    private static final int thirdMeaningLanguage = unifiedLanguageId

    private static final int additionalMeaningId = 10004
    private static final String additionalMeaningValue = "entiree"
    private static final int additionalMeaningClass = 10000
    private static final int additionalMeaningLanguage = unifiedLanguageId

    private static final int wordWithNoMeaningsId = 10005
    private static final String wordWithNoMeaningsValue = "word with no meaning"
    private static final int wordWithNoMeaningsClass = 10000
    private static final int wordWithNoMeaningsLanguage = unifiedLanguageId
    //--------------- word meaning relationships ids ------------------//
    private static final int wordFirstMeaningRelationshipId = 10000
    private static final int firstMeaningWordRelationshipId = 10001
    private static final int wordSecondMeaningRelationshipId = 10002
    private static final int wordThirdMeaningRelationshipId = 10003
    //--------------- word collection relationships ids ------------------//
    private static final int wordFirstCollectionRelationshipId = 10000
    private static final int wordSecondCollectionRelationshipId = 10001
    private static final int wordThirdCollectionRelationshipId = 10002
    private static final int firstMeaningFirstCollectionRelationshipId = 10003
    private static final int secondMeaningFirstCollectionRelationshipId = 10004
    private static final int thirdMeaningFirstCollectionRelationshipId = 10005

    @Shared
    WordSrv wordSrv

    def setupSpec() {
        this.wordSrv = new WordSrv()

        try {
            insertLanguages();
            insertCollections();
            insertWordClasses();
            insertWords();
            insertWordCollectionsRelationship();
            insertWordMeaningRelationships();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    def cleanupSpec() {


        try {
            insertLanguages();
            insertCollections();
            insertWordClasses();
            insertWords();
            insertWordCollectionsRelationship();
            insertWordMeaningRelationships();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-------------  insert operations  ----------------//

    def insertLanguages() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO languages ")
                .append("(").append(Language.ID_CN).append(",").append(Language.NAME_CN).append(")")
                .append(" VALUES ")
                .append("(").append(unifiedLanguageId).append(",").append("\'" + unifiedLanguage + "\'").append(")").append(",")
                .append("(").append(russianID).append(",").append("\'" + russian + "\'").append(")").append(",")
                .append("(").append(germanID).append(",").append("\'" + german + "\'").append(")").append(";");
        printInsertQuery(insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    def insertCollections() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_collections ")
                .append("(").append(WordCollection.ID_CN).append(",").append(WordCollection.NAME_CN).append(")")
                .append(" VALUES ")
                .append("(").append(firstCollectionId).append(",").append("\'" + firstCollection + "\'").append(")").append(",")
                .append("(").append(secondCollectionId).append(",").append("\'" + secondCollection + "\'").append(")").append(",")
                .append("(").append(thirdCollectionId).append(",").append("\'" + thirdCollection + "\'").append(")").append(";");
        printInsertQuery(insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    def insertWordClasses() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_classes ")
                .append("(").append(WordClass.ID_CN).append(",").append(WordClass.NAME_CN).append(")")
                .append(" VALUES ")
                .append("(").append(firstWordClassId).append(",").append("\'" + firstWordClass + "\'").append(")").append(",")
                .append("(").append(secondWordClassId).append(",").append("\'" + secondWordClass + "\'").append(")").append(",")
                .append("(").append(adverbID).append(",").append("\'" + adverb + "\'").append(")").append(";");
        printInsertQuery(insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    def insertWords() throws SQLException {
        StringBuilder insertWordsQuery = new StringBuilder("");
        insertWordsQuery.append("INSERT INTO words")
                .append("(").append(Word.ID_CN).append(",").append(Word.VALUE_CN).append(",").append(Word.LANGUAGE_CN).append(",").append(Word.WORD_CLASS_CN).append(")")
                .append(" VALUES ")
                .append("(").append(wordId).append(",").append("\'" + wordValue + "\'").append(",").append(wordLanguage).append(",").append(wordClass).append(")").append(",")
                .append("(").append(firstMeaningId).append(",").append("\'" + firstMeaningValue + "\'").append(",").append(firstMeaningLanguage).append(",").append(firstMeaningClass).append(")").append(",")
                .append("(").append(secondMeaningId).append(",").append("\'" + secondMeaningValue + "\'").append(",").append(secondMeaningLanguage).append(",").append(secondMeaningClass).append(")").append(",")
                .append("(").append(thirdMeaningId).append(",").append("\'" + thirdMeaningValue + "\'").append(",").append(thirdMeaningLanguage).append(",").append(thirdMeaningClass).append(")").append(",")
                .append("(").append(additionalMeaningId).append(",").append("\'" + additionalMeaningValue + "\'").append(",").append(additionalMeaningLanguage).append(",").append(additionalMeaningClass).append(")").append(",")
                .append("(").append(wordWithNoMeaningsId).append(",").append("\'" + wordWithNoMeaningsValue + "\'").append(",").append(wordWithNoMeaningsLanguage).append(",").append(wordWithNoMeaningsClass).append(")").append(";");
        ;
        printInsertQuery(insertWordsQuery.toString());
        PreparedStatement insertWordsStatement = DatabaseManager.connection.prepareStatement(insertWordsQuery.toString());
        insertWordsStatement.execute();
    }

    def insertWordMeaningRelationships() throws SQLException {
        StringBuilder insertRelationshipsQuery = new StringBuilder("");
        insertRelationshipsQuery.append("INSERT INTO word_meaning_relationships ")
                .append("(").append(WordMeaningRelationship.ID_COLUMN_NAME).append(",").append(WordMeaningRelationship.WORD_COLUMN_NAME).append(",").append(WordMeaningRelationship.MEANING_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append(wordFirstMeaningRelationshipId).append(",").append(wordId).append(",").append(firstMeaningId).append(")").append(",")
                .append("(").append(firstMeaningWordRelationshipId).append(",").append(firstMeaningId).append(",").append(wordId).append(")").append(",")
                .append("(").append(wordSecondMeaningRelationshipId).append(",").append(wordId).append(",").append(secondMeaningId).append(")").append(",")
                .append("(").append(wordThirdMeaningRelationshipId).append(",").append(wordId).append(",").append(thirdMeaningId).append(")").append(";");
        printInsertQuery(insertRelationshipsQuery.toString());
        PreparedStatement insertRelationshipsStatement = DatabaseManager.connection.prepareStatement(insertRelationshipsQuery.toString());
        insertRelationshipsStatement.execute();
    }

    def insertWordCollectionsRelationship() throws SQLException {
        StringBuilder insertCollectionsRelationshipsQuery = new StringBuilder("");
        insertCollectionsRelationshipsQuery
                .append("INSERT INTO word_collection_relationships")
                .append("(").append(WordCollectionRelationship.ID_CN).append(",").append(WordCollectionRelationship.WORD_CN).append(",").append(WordCollectionRelationship.COLLECTION_CN).append(")")
                .append(" VALUES ")
                .append("(").append(wordFirstCollectionRelationshipId).append(",").append(wordId).append(",").append(firstCollectionId).append(")").append(",")
                .append("(").append(wordSecondCollectionRelationshipId).append(",").append(wordId).append(",").append(secondCollectionId).append(")").append(",")
                .append("(").append(wordThirdCollectionRelationshipId).append(",").append(wordId).append(",").append(thirdCollectionId).append(")").append(",")
                .append("(").append(firstMeaningFirstCollectionRelationshipId).append(",").append(firstMeaningId).append(",").append(firstCollectionId).append(")").append(",")
                .append("(").append(secondMeaningFirstCollectionRelationshipId).append(",").append(secondMeaningId).append(",").append(firstCollectionId).append(")").append(",")
                .append("(").append(thirdMeaningFirstCollectionRelationshipId).append(",").append(thirdMeaningId).append(",").append(firstCollectionId).append(")").append(";");
        printInsertQuery(insertCollectionsRelationshipsQuery.toString());
        PreparedStatement insertCollectionsRelationshipsStatement = DatabaseManager.connection.prepareStatement(insertCollectionsRelationshipsQuery.toString());
        insertCollectionsRelationshipsStatement.execute();
    }

    //-------------  delete operations  ----------------//

    def deleteLanguages() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM languages")
                .append(" WHERE ")
                .append("(").append(Language.ID_CN).append("=").append(unifiedLanguageId)
                .append(" AND ")
                .append(Language.NAME_CN).append("=").append("\'" + unifiedLanguage + "\'").append(")")
                .append(" OR ")
                .append("(").append(Language.ID_CN).append("=").append(russianID)
                .append(" AND ")
                .append(Language.NAME_CN).append("=").append("\'" + russian + "\'").append(")")
                .append(" OR ")
                .append("(").append(Language.ID_CN).append("=").append(germanID)
                .append(" AND ")
                .append(Language.NAME_CN).append("=").append("\'" + german + "\'").append(")");
        printDeleteQuery(deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    def deleteCollections() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM word_collections")
                .append(" WHERE ")
                .append("(").append(WordCollection.ID_CN).append("=").append(firstCollectionId)
                .append(" AND ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + firstCollection + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordCollection.ID_CN).append("=").append(secondCollectionId)
                .append(" AND ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + secondCollection + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordCollection.ID_CN).append("=").append(thirdCollectionId)
                .append(" AND ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + thirdCollection + "\'").append(")");
        printDeleteQuery(deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    def deleteWordClasses() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM word_classes")
                .append(" WHERE ")
                .append("(").append(WordClass.ID_CN).append("=").append(firstWordClassId)
                .append(" AND ")
                .append(WordClass.NAME_CN).append("=").append("\'" + firstWordClass + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordClass.ID_CN).append("=").append(secondWordClassId)
                .append(" AND ")
                .append(WordClass.NAME_CN).append("=").append("\'" + secondWordClass + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordClass.ID_CN).append("=").append(adverbID)
                .append(" AND ")
                .append(WordClass.NAME_CN).append("=").append("\'" + adverb + "\'").append(")");
        printDeleteQuery(deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    def deleteWords() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM words")
                .append(" WHERE ")
                .append(Word.ID_CN).append("=").append(wordId)
                .append(" OR ")
                .append(Word.ID_CN).append("=").append(firstMeaningId)
                .append(" OR ")
                .append(Word.ID_CN).append("=").append(secondMeaningId)
                .append(" OR ")
                .append(Word.ID_CN).append("=").append(thirdMeaningId)
                .append(" OR ")
                .append(Word.ID_CN).append("=").append(additionalMeaningId)
                .append(" OR ")
                .append(Word.ID_CN).append("=").append(wordWithNoMeaningsId);
        printDeleteQuery(deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    def deleteWordMeaningRelationships() throws SQLException {
        StringBuilder deleteWordMeaningRelationshipsQuery = new StringBuilder("");
        deleteWordMeaningRelationshipsQuery.append("DELETE FROM word_meaning_relationships ")
                .append(" WHERE ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordFirstMeaningRelationshipId)
                .append(" OR ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(firstMeaningWordRelationshipId)
                .append(" OR ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordSecondMeaningRelationshipId)
                .append(" OR ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordThirdMeaningRelationshipId);
        printDeleteQuery(deleteWordMeaningRelationshipsQuery.toString());
        PreparedStatement insertRelationshipsStatement = DatabaseManager.connection.prepareStatement(deleteWordMeaningRelationshipsQuery.toString());
        insertRelationshipsStatement.execute();
    }

    def deleteWordCollectionsRelationship() throws SQLException {

        StringBuilder deleteCollectionsRelationshipsQuery = new StringBuilder("");
        deleteCollectionsRelationshipsQuery
                .append("DELETE FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(wordFirstCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(wordSecondMeaningRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(wordThirdCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(firstMeaningWordRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(firstMeaningFirstCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(secondMeaningFirstCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_CN).append("=").append(thirdMeaningFirstCollectionRelationshipId);
        printDeleteQuery(deleteCollectionsRelationshipsQuery.toString());
        PreparedStatement insertCollectionsRelationshipsStatement = DatabaseManager.connection.prepareStatement(deleteCollectionsRelationshipsQuery.toString());
        insertCollectionsRelationshipsStatement.execute();
    }

    //-------------  print queries  ----------------//

    def printInsertQuery(String query) {
        /*System.out.println("insert query: " + query);*/
    }

    def printDeleteQuery(String query) {
        /*System.out.println("delete query: " + query);*/
    }

    def "create not existing word"() {

    }

    def "create existing word"() {

    }


    def "update existing word"() {

    }

    def "update not existing word"() {

    }


    def "find existing word by id"() {

    }

    def "find not existing word by id"() {

    }


    def "find existing word by value and language"() {

    }

    def "find not existing word by value and language"() {

    }


    def "find words by language"() {

    }

    def "find words by not existing language"() {

    }


    def "find all words"() {

    }


    def "delete existing word"() {

    }

    def "delete not existing word"() {

    }


    def "get existing word meanings"() {

    }

    def "get not existing word meanings"() {

    }


    def "get existing word collections"() {

    }

    def "get not existing word collections"() {

    }


    def "get existing collection words"() {

    }

    def "get not existing collection words"() {

    }


    def "add not existing word meaning relationship with existing meaning"() {}

    def "add existing word meaning relationship"() {}

    def "add word meaning relationship with not existing word"() {}

    def "add word meaning relationship with not existing meaning"() {}


    def "remove existing word meaning relationship"() {}

    def "remove not existing word meaning relationship with existing meaning"() {}

    def "remove word meaning relationship with not existing word"() {}

    def "remove word meaning relationship with not existing meaning"() {}


    def "add not existing word collection relationship"() {}

    def "add existing word collection relationship"() {}

    def "add word collection relationship with not existing word"() {}

    def "add word collection relationship with not existing collection"() {}


    def "remove existing word collection relationship"() {}

    def "remove not existing word collection relationship"() {}

    def "remove word collection relationship with not existing word"() {}

    def "remove word collection relationship with not existing collection"() {}
}
