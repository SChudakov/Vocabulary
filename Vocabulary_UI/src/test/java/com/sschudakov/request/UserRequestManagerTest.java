package com.sschudakov.request;

import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.entity.WordMeaningRelationship;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRequestManagerTest {
    private UserRequestManager userRequestManager;


    private static final int englishID = 10000;
    private static final int russianID = 10001;
    private static final int germanID = 10002;
    private static final String english = "English";
    private static final String russian = "Russian";
    private static final String german = "German";
    private static final int firstCollectionId = 10000;
    private static final int secondCollectionId = 10001;
    private static final int thirdCollectionId = 10002;
    private static final String firstCollection = "collections 1";
    private static final String secondCollection = "collections 2";
    private static final String thirdCollection = "collections 3";
    private static final int nounID = 10000;
    private static final int verbID = 10001;
    private static final int adverbID = 10002;
    private static final String noun = "noun";
    private static final String verb = "verb";
    private static final String adverb = "adverb";
    private static final int wordId = 10000;
    private static final String wordValue = "vollkommen";
    private static final int wordClass = 0;
    private static final int wordLanguage = 10002;

    private static final int firstMeaningId = 10001;
    private static final String firstMeaningValue = "полный";
    private static final int firstMeaningClass = 0;
    private static final int firstMeaningLanguage = 10001;

    private static final int secondMeaningId = 10002;
    private static final String secondMeaningValue = "абсоютный";
    private static final int secondMeaningClass = 0;
    private static final int secondMeaningLanguage = 10001;

    private static final int thirdMeaningId = 10003;
    private static final String thirdMeaningValue = "совершенный";
    private static final int thirdMeaningClass = 0;
    private static final int thirdMeaningLanguage = 10001;
    private static final int wordFirstMeaningRelationshipId = 10000;
    private static final int wordSecondMeaningRelationshipId = 10001;
    private static final int wordThirdMeaningRelationshipId = 10002;
    private static final int wordFirstCollectionRelationshipId = 10000;
    private static final int wordSecondCollectionRelationshipId = 10001;
    private static final int wordThirdCollectionRelationshipId = 10002;
    private static final int firstMeaningFirstCollectionRelationshipId = 10003;
    private static final int secondMeaningFirstCollectionRelationshipId = 10004;
    private static final int thirdMeaningFirstCollectionRelationshipId = 10005;

    @BeforeClass
    public static void setup() {
        try {
            insertLanguages();
            insertCollections();
            insertWordClasses();
            insertWords();
            insertWordMeaningRelationships();
            insertWordCollectionsRelationship();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void clean() {
        try {
            deleteLanguages();
            deleteCollections();
            deleteWordClasses();
            deleteWords();
            deleteWordMeaningRelationships();
            deleteWordCollectionsRelationship();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        userRequestManager = new UserRequestManager();
    }

    private static void insertLanguages() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO languages ")
                .append(" VALUES ")
                .append("(").append(englishID).append(",").append("\'" + english + "\'").append(")").append(",")
                .append("(").append(russianID).append(",").append("\'" + russian + "\'").append(")").append(",")
                .append("(").append(germanID).append(",").append("\'" + german + "\'").append(")").append(";");
        System.out.println("insert query:\n" + insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    private static void insertCollections() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_collections ")
                .append(" VALUES ")
                .append("(").append(firstCollectionId).append(",").append("\'" + firstCollection + "\'").append(")").append(",")
                .append("(").append(secondCollectionId).append(",").append("\'" + secondCollection + "\'").append(")").append(",")
                .append("(").append(thirdCollectionId).append(",").append("\'" + thirdCollection + "\'").append(")").append(";");
        System.out.println("insert query:\n" + insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    private static void insertWordClasses() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_classes ")
                .append(" VALUES ")
                .append("(").append(nounID).append(",").append("\'" + noun + "\'").append(")").append(",")
                .append("(").append(verbID).append(",").append("\'" + verb + "\'").append(")").append(",")
                .append("(").append(adverbID).append(",").append("\'" + adverb + "\'").append(")").append(";");
        System.out.println("insert query:\n" + insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    private static void insertWords() throws SQLException {
        StringBuilder insertWordsQuery = new StringBuilder("");
        insertWordsQuery.append("INSERT INTO words")
                .append(" VALUES ")
                .append("(")
                .append(wordId).append(",")
                .append("\'" + wordValue + "\'").append(",")
                .append(wordLanguage).append(",")
                .append(wordClass)
                .append(")").append(",")
                .append("(")
                .append(firstMeaningId).append(",")
                .append("\'" + firstMeaningValue + "\'").append(",")
                .append(firstMeaningLanguage).append(",")
                .append(firstMeaningClass)
                .append(")").append(",")
                .append("(")
                .append(secondMeaningId).append(",")
                .append("\'" + secondMeaningValue + "\'").append(",")
                .append(secondMeaningLanguage).append(",")
                .append(secondMeaningClass)
                .append(")").append(",")
                .append("(")
                .append(thirdMeaningId).append(",")
                .append("\'" + thirdMeaningValue + "\'").append(",")
                .append(thirdMeaningLanguage).append(",")
                .append(thirdMeaningClass)
                .append(")").append(";")
        ;
        System.out.println("insert query:\n" + insertWordsQuery.toString());
        PreparedStatement insertWordsStatement = DatabaseManager.connection.prepareStatement(insertWordsQuery.toString());
        insertWordsStatement.execute();
    }

    private static void insertWordMeaningRelationships() throws SQLException {
        StringBuilder insertRelationshipsQuery = new StringBuilder("");
        insertRelationshipsQuery.append("INSERT INTO word_meaning_relationships ")
                .append(" VALUES ")
                .append("(")
                .append(wordFirstMeaningRelationshipId).append(",")
                .append(wordId).append(",")
                .append(firstMeaningId)
                .append(")").append(",")
                .append("(")
                .append(wordSecondMeaningRelationshipId).append(",")
                .append(wordId).append(",")
                .append(secondMeaningId)
                .append(")").append(",")
                .append("(")
                .append(wordThirdMeaningRelationshipId).append(",")
                .append(wordId).append(",")
                .append(thirdMeaningId)
                .append(")").append(";");
        System.out.println("insert query:\n" + insertRelationshipsQuery.toString());
        PreparedStatement insertRelationshipsStatement = DatabaseManager.connection.prepareStatement(insertRelationshipsQuery.toString());
        insertRelationshipsStatement.execute();
    }

    private static void insertWordCollectionsRelationship() throws SQLException {
        StringBuilder insertCollectionsRelationshipsQuery = new StringBuilder("");
        insertCollectionsRelationshipsQuery
                .append("INSERT INTO word_collection_relationships")
                .append(" VALUES ")
                .append("(").append(wordFirstCollectionRelationshipId).append(",").append(wordId).append(",").append(firstCollectionId).append(")").append(",")
                .append("(").append(wordSecondCollectionRelationshipId).append(",").append(wordId).append(",").append(secondCollectionId).append(")").append(",")
                .append("(").append(wordThirdCollectionRelationshipId).append(",").append(wordId).append(",").append(thirdCollectionId).append(")").append(",")
                .append("(").append(firstMeaningFirstCollectionRelationshipId).append(",").append(firstMeaningId).append(",").append(firstCollectionId).append(")").append(",")
                .append("(").append(secondMeaningFirstCollectionRelationshipId).append(",").append(secondMeaningId).append(",").append(firstCollectionId).append(")").append(",")
                .append("(").append(thirdMeaningFirstCollectionRelationshipId).append(",").append(thirdMeaningId).append(",").append(firstCollectionId).append(")").append(";");
        System.out.println("insert query:\n" + insertCollectionsRelationshipsQuery);
        PreparedStatement insertCollectionsRelationshipsStatement = DatabaseManager.connection.prepareStatement(insertCollectionsRelationshipsQuery.toString());
        insertCollectionsRelationshipsStatement.execute();
    }


    private static void deleteLanguages() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM languages")
                .append(" WHERE ")
                .append("(").append(Language.ID_FIELD_COLUMN_NAME).append("=").append(englishID)
                .append(" AND ")
                .append(Language.NAME_FIELD_COLUMN_NAME).append("=").append("\'" + english + "\'").append(")")
                .append(" OR ")
                .append("(").append(Language.ID_FIELD_COLUMN_NAME).append("=").append(russianID)
                .append(" AND ")
                .append(Language.NAME_FIELD_COLUMN_NAME).append("=").append("\'" + russian + "\'").append(")")
                .append(" OR ")
                .append("(").append(Language.ID_FIELD_COLUMN_NAME).append("=").append(germanID)
                .append(" AND ")
                .append(Language.NAME_FIELD_COLUMN_NAME).append("=").append("\'" + german + "\'").append(")");
        System.out.println("delete query:\n" + deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    private static void deleteCollections() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM word_collections")
                .append(" WHERE ")
                .append("(").append(WordCollection.ID_COLUMN_NAME).append("=").append(firstCollectionId)
                .append(" AND ")
                .append(WordCollection.COLLECTION_NAME_COLUMN_NAME).append("=").append("\'" + firstCollection + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordCollection.ID_COLUMN_NAME).append("=").append(secondCollectionId)
                .append(" AND ")
                .append(WordCollection.COLLECTION_NAME_COLUMN_NAME).append("=").append("\'" + secondCollection + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordCollection.ID_COLUMN_NAME).append("=").append(thirdCollectionId)
                .append(" AND ")
                .append(WordCollection.COLLECTION_NAME_COLUMN_NAME).append("=").append("\'" + thirdCollection + "\'").append(")");
        System.out.println("delete query:\n" + deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    private static void deleteWordClasses() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM word_classes")
                .append(" WHERE ")
                .append("(").append(WordClass.ID_COLUMN_NAME).append("=").append(nounID)
                .append(" AND ")
                .append(WordClass.CLASS_NAME_COLUMN_NAME).append("=").append("\'" + noun + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordClass.ID_COLUMN_NAME).append("=").append(verbID)
                .append(" AND ")
                .append(WordClass.CLASS_NAME_COLUMN_NAME).append("=").append("\'" + verb + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordClass.ID_COLUMN_NAME).append("=").append(adverbID)
                .append(" AND ")
                .append(WordClass.CLASS_NAME_COLUMN_NAME).append("=").append("\'" + adverb + "\'").append(")");
        System.out.println("delete query:\n" + deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    private static void deleteWords() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM words")
                .append(" WHERE ")
                .append(Word.ID_COLUMN_NAME).append("=").append(wordId)
                .append(" OR ")
                .append(Word.ID_COLUMN_NAME).append("=").append(firstMeaningId)
                .append(" OR ")
                .append(Word.ID_COLUMN_NAME).append("=").append(secondMeaningId)
                .append(" OR ")
                .append(Word.ID_COLUMN_NAME).append("=").append(thirdMeaningId);
        System.out.println("delete query:\n" + deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    private static void deleteWordMeaningRelationships() throws SQLException {
        StringBuilder deleteWordMeaningRelationshipsQuery = new StringBuilder("");
        deleteWordMeaningRelationshipsQuery.append("DELETE FROM word_meaning_relationships ")
                .append(" WHERE ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordFirstMeaningRelationshipId)
                .append(" OR ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordSecondMeaningRelationshipId)
                .append(" OR ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordThirdMeaningRelationshipId);
        System.out.println("delete query:\n" + deleteWordMeaningRelationshipsQuery.toString());
        PreparedStatement insertRelationshipsStatement = DatabaseManager.connection.prepareStatement(deleteWordMeaningRelationshipsQuery.toString());
        insertRelationshipsStatement.execute();
    }

    private static void deleteWordCollectionsRelationship() throws SQLException {

        StringBuilder deleteCollectionsRelationshipsQuery = new StringBuilder("");
        deleteCollectionsRelationshipsQuery
                .append("DELETE FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(wordFirstCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(wordSecondMeaningRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(wordThirdCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(firstMeaningFirstCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(secondMeaningFirstCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(thirdMeaningFirstCollectionRelationshipId);
        System.out.println("delete query:\n" + deleteCollectionsRelationshipsQuery);
        PreparedStatement insertCollectionsRelationshipsStatement = DatabaseManager.connection.prepareStatement(deleteCollectionsRelationshipsQuery.toString());
        insertCollectionsRelationshipsStatement.execute();
    }


    @Test
    public void getLanguages() throws SQLException {
        List<String> languages = this.userRequestManager.getLanguages();
        for (String s : languages) {
            System.out.println(s);
        }
        Assert.assertEquals(true, languages.contains(english));
        Assert.assertEquals(true, languages.contains(russian));
        Assert.assertEquals(true, languages.contains(german));
    }

    @Test
    public void getCollections() throws SQLException {
        List<String> collections = this.userRequestManager.getCollections();
        for (String s : collections) {
            System.out.println(s);
        }
        Assert.assertEquals(true, collections.contains(firstCollection));
        Assert.assertEquals(true, collections.contains(secondCollection));
        Assert.assertEquals(true, collections.contains(thirdCollection));
    }

    @Test
    public void getClasses() throws SQLException {
        List<String> classes = this.userRequestManager.getClasses();
        for (String s : classes) {
            System.out.println(s);
        }
        Assert.assertEquals(true, classes.contains(noun));
        Assert.assertEquals(true, classes.contains(verb));
        Assert.assertEquals(true, classes.contains(adverb));
    }

    @Test
    public void getMeaningsByWord() throws SQLException {
        List<String> meanings = this.userRequestManager.getWordMeanings(wordValue, german, russian);
        for (String s : meanings) {
            System.out.println(s);
        }
        Assert.assertEquals(true, meanings.contains(firstMeaningValue));
        Assert.assertEquals(true, meanings.contains(secondMeaningValue));
        Assert.assertEquals(true, meanings.contains(thirdMeaningValue));
    }

    @Test
    public void getWordsByLanguageName() throws SQLException {
        List<String> words = this.userRequestManager.getWordsByLanguageName(russian);
        for (String word : words) {
            System.out.println(word);
        }
        Assert.assertEquals(true, words.contains(firstMeaningValue));
        Assert.assertEquals(true, words.contains(secondMeaningValue));
        Assert.assertEquals(true, words.contains(thirdMeaningValue));
    }

    @Test
    public void getCollectionsByWord() throws SQLException {
        /*Map<String, Boolean> map = this.userRequestManager.getCollectionsByWord("apple", "English");
        for (String collection : map.keySet()) {
            System.out.println(collection + " " + map.get(collection));
        }*/
    }

    @Test
    public void getWordsByCollectionName() throws SQLException {
        /*for (String kek : this.userRequestManager.getWordsByCollectionName("kek")) {
            System.out.println(kek);
        }*/
    }

    @Test
    public void createWord() {
    }

    @Test
    public void createCollection() {
    }

    @Test
    public void deleteWord() {
    }

    @Test
    public void deleteCollection() {
    }

    @Test
    public void addMeaningToWord() {
    }

    @Test
    public void addWordToCollection() {
    }

    @Test
    public void changeWordClassTo() {
    }

    @Test
    public void wordExists() {
    }

    @Test
    public void collectionExists() {
    }

    @Test
    public void languageExists() {
    }

    @Test
    public void classExists() {
    }

    @Test
    public void removeMeaning() {

    }

    @Test
    public void removeWordFromCollection() {
    }
}