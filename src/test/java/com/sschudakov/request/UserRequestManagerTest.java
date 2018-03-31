package com.sschudakov.request;

import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.*;
import com.sschudakov.factory.UserRequestManagerFactory;
import org.junit.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserRequestManagerTest {
    private static UserRequestManager userRequestManager;

    private static final int englishID = 10000;
    private static final int russianID = 10001;
    private static final int germanID = 10002;
    private static final String english = "englishh";
    private static final String russian = "russiann";
    private static final String german = "germann";

    private static final int firstCollectionId = 10000;
    private static final int secondCollectionId = 10001;
    private static final int thirdCollectionId = 10002;
    private static final String firstCollection = "collection 1";
    private static final String secondCollection = "collection 2";
    private static final String thirdCollection = "collection 3";

    private static final int nounID = 10000;
    private static final int verbID = 10001;
    private static final int adverbID = 10002;
    private static final String noun = "nounn";
    private static final String verb = "verbb";
    private static final String adverb = "adverbb";

    private static final int wordId = 10000;
    private static final String wordValue = "vollkommenn";
    private static final int wordClass = 10000;
    private static final int wordLanguage = germanID;

    private static final int firstMeaningId = 10001;
    private static final String firstMeaningValue = "полныйй";
    private static final int firstMeaningClass = 10000;
    private static final int firstMeaningLanguage = russianID;

    private static final int secondMeaningId = 10002;
    private static final String secondMeaningValue = "абсоютныйй";
    private static final int secondMeaningClass = 10000;
    private static final int secondMeaningLanguage = russianID;

    private static final int thirdMeaningId = 10003;
    private static final String thirdMeaningValue = "совершенныйй";
    private static final int thirdMeaningClass = 10000;
    private static final int thirdMeaningLanguage = russianID;

    private static final int additionalMeaningId = 10004;
    private static final String additionalMeaningValue = "entiree";
    private static final int additionalMeaningClass = 10000;
    private static final int additionalMeaningLanguage = englishID;

    private static final int wordWithNoMeaningsId = 10005;
    private static final String wordWithNoMeaningsValue = "word with no meaning";
    private static final int wordWithNoMeaningsClass = 10000;
    private static final int wordWithNoMeaningsLanguage = englishID;

    private static final int wordFirstMeaningRelationshipId = 10000;
    private static final int firstMeaningWordRelationshipId = 10001;
    private static final int wordSecondMeaningRelationshipId = 10002;
    private static final int wordThirdMeaningRelationshipId = 10003;

    private static final int wordFirstCollectionRelationshipId = 10000;
    private static final int wordSecondCollectionRelationshipId = 10001;
    private static final int wordThirdCollectionRelationshipId = 10002;
    private static final int firstMeaningFirstCollectionRelationshipId = 10003;
    private static final int secondMeaningFirstCollectionRelationshipId = 10004;
    private static final int thirdMeaningFirstCollectionRelationshipId = 10005;

    private static final String createdWordValue = "created word";
    private static final int createdWordLanguage = englishID;
    private static final int createdWordClass = nounID;

    private static final String createdCollectionName = "created collection";
    private static final String notExistingCollection = "not existing collections";

    private static final String notExistingWord = "not existing word";

    @BeforeClass
    public static void setup() {
        userRequestManager = UserRequestManagerFactory.createRequestManager();
    }

    @Before
    public void init() {
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

    @After
    public void clean() {
        try {
            deleteWordCollectionsRelationship();
            deleteWordMeaningRelationships();
            deleteWords();
            deleteLanguages();
            deleteCollections();
            deleteWordClasses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printInsertQuery(String query) {
        /*System.out.println("insert query: " + query);*/
    }

    private static void printDeleteQuery(String query) {
        /*System.out.println("delete query: " + query);*/
    }

    //insert operations
    private static void insertLanguages() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO languages ")
                .append("(").append(Language.ID_COLUMN_NAME).append(",").append(Language.NAME_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append(englishID).append(",").append("\'" + english + "\'").append(")").append(",")
                .append("(").append(russianID).append(",").append("\'" + russian + "\'").append(")").append(",")
                .append("(").append(germanID).append(",").append("\'" + german + "\'").append(")").append(";");
        printInsertQuery(insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    private static void insertCollections() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_collections ")
                .append("(").append(WordCollection.ID_COLUMN_NAME).append(",").append(WordCollection.NAME_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append(firstCollectionId).append(",").append("\'" + firstCollection + "\'").append(")").append(",")
                .append("(").append(secondCollectionId).append(",").append("\'" + secondCollection + "\'").append(")").append(",")
                .append("(").append(thirdCollectionId).append(",").append("\'" + thirdCollection + "\'").append(")").append(";");
        printInsertQuery(insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    private static void insertWordClasses() throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_classes ")
                .append("(").append(WordClass.ID_COLUMN_NAME).append(",").append(WordClass.NAME_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append(nounID).append(",").append("\'" + noun + "\'").append(")").append(",")
                .append("(").append(verbID).append(",").append("\'" + verb + "\'").append(")").append(",")
                .append("(").append(adverbID).append(",").append("\'" + adverb + "\'").append(")").append(";");
        printInsertQuery(insertQuery.toString());
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    private static void insertWords() throws SQLException {
        StringBuilder insertWordsQuery = new StringBuilder("");
        insertWordsQuery.append("INSERT INTO words")
                .append("(").append(Word.ID_COLUMN_NAME).append(",").append(Word.VALUE_COLUMN_NAME).append(",").append(Word.LANGUAGE_COLUMN_NAME).append(",").append(Word.WORD_CLASS_COLUMN_NAME).append(")")
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

    private static void insertWordMeaningRelationships() throws SQLException {
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

    private static void insertWordCollectionsRelationship() throws SQLException {
        StringBuilder insertCollectionsRelationshipsQuery = new StringBuilder("");
        insertCollectionsRelationshipsQuery
                .append("INSERT INTO word_collection_relationships")
                .append("(").append(WordCollectionRelationship.ID_COLUMN_NAME).append(",").append(WordCollectionRelationship.WORD_COLUMN_NAME).append(",").append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append(")")
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

    //delete operations
    private static void deleteLanguages() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM languages")
                .append(" WHERE ")
                .append("(").append(Language.ID_COLUMN_NAME).append("=").append(englishID)
                .append(" AND ")
                .append(Language.NAME_COLUMN_NAME).append("=").append("\'" + english + "\'").append(")")
                .append(" OR ")
                .append("(").append(Language.ID_COLUMN_NAME).append("=").append(russianID)
                .append(" AND ")
                .append(Language.NAME_COLUMN_NAME).append("=").append("\'" + russian + "\'").append(")")
                .append(" OR ")
                .append("(").append(Language.ID_COLUMN_NAME).append("=").append(germanID)
                .append(" AND ")
                .append(Language.NAME_COLUMN_NAME).append("=").append("\'" + german + "\'").append(")");
        printDeleteQuery(deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    private static void deleteCollections() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM word_collections")
                .append(" WHERE ")
                .append("(").append(WordCollection.ID_COLUMN_NAME).append("=").append(firstCollectionId)
                .append(" AND ")
                .append(WordCollection.NAME_COLUMN_NAME).append("=").append("\'" + firstCollection + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordCollection.ID_COLUMN_NAME).append("=").append(secondCollectionId)
                .append(" AND ")
                .append(WordCollection.NAME_COLUMN_NAME).append("=").append("\'" + secondCollection + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordCollection.ID_COLUMN_NAME).append("=").append(thirdCollectionId)
                .append(" AND ")
                .append(WordCollection.NAME_COLUMN_NAME).append("=").append("\'" + thirdCollection + "\'").append(")");
        printDeleteQuery(deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    private static void deleteWordClasses() throws SQLException {
        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM word_classes")
                .append(" WHERE ")
                .append("(").append(WordClass.ID_COLUMN_NAME).append("=").append(nounID)
                .append(" AND ")
                .append(WordClass.NAME_COLUMN_NAME).append("=").append("\'" + noun + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordClass.ID_COLUMN_NAME).append("=").append(verbID)
                .append(" AND ")
                .append(WordClass.NAME_COLUMN_NAME).append("=").append("\'" + verb + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordClass.ID_COLUMN_NAME).append("=").append(adverbID)
                .append(" AND ")
                .append(WordClass.NAME_COLUMN_NAME).append("=").append("\'" + adverb + "\'").append(")");
        printDeleteQuery(deleteQuery.toString());
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
                .append(Word.ID_COLUMN_NAME).append("=").append(thirdMeaningId)
                .append(" OR ")
                .append(Word.ID_COLUMN_NAME).append("=").append(additionalMeaningId)
                .append(" OR ")
                .append(Word.ID_COLUMN_NAME).append("=").append(wordWithNoMeaningsId);
        printDeleteQuery(deleteQuery.toString());
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
        deleteStatement.execute();
    }

    private static void deleteWordMeaningRelationships() throws SQLException {
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
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(firstMeaningWordRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(firstMeaningFirstCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(secondMeaningFirstCollectionRelationshipId)
                .append(" OR ")
                .append(WordCollectionRelationship.ID_COLUMN_NAME).append("=").append(thirdMeaningFirstCollectionRelationshipId);
        printDeleteQuery(deleteCollectionsRelationshipsQuery.toString());
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

    /**
     * Method throws an NPE if there is no such word in database
     *
     * @throws SQLException
     */
    @Ignore
    @Test
    public void getWordMeanings() throws SQLException {
        /*List<String> meanings = this.userRequestManager.getWordMeanings(wordValue, german, russian);
        for (String s : meanings) {
            System.out.println(s);
        }
        Assert.assertEquals(true, meanings.contains(firstMeaningValue));
        Assert.assertEquals(true, meanings.contains(secondMeaningValue));
        Assert.assertEquals(true, meanings.contains(thirdMeaningValue));*/
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
        Map<String, Boolean> collectionsByWord = this.userRequestManager.getWordCollections(wordValue, german);
        for (Map.Entry<String, Boolean> stringBooleanEntry : collectionsByWord.entrySet()) {
            System.out.println(stringBooleanEntry.getKey() + " - " + stringBooleanEntry.getValue());
        }
        Assert.assertEquals(true, collectionsByWord.get(firstCollection));
        Assert.assertEquals(true, collectionsByWord.get(secondCollection));
        Assert.assertEquals(true, collectionsByWord.get(thirdCollection));
    }

    @Test
    public void getWordsByCollectionName() throws SQLException {
        List<String> words = this.userRequestManager.getCollectionWords(firstCollection);
        for (String word : words) {
            System.out.println(word);
        }
        Assert.assertEquals(true, words.contains(wordValue));
        Assert.assertEquals(true, words.contains(firstMeaningValue));
        Assert.assertEquals(true, words.contains(secondMeaningValue));
        Assert.assertEquals(true, words.contains(thirdMeaningValue));
    }
    @Ignore
    @Test
    public void saveWordInformation() throws SQLException {

        StringBuilder checkQuery = new StringBuilder("");
        checkQuery.append("SELECT * FROM words")
                .append(" WHERE ")
                .append(Word.VALUE_COLUMN_NAME).append("=").append("\'" + createdWordValue + "\'")
                .append(" AND ")
                .append(Word.LANGUAGE_COLUMN_NAME).append("=").append(createdWordLanguage);

        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM words")
                .append(" WHERE ")
                .append(Word.VALUE_COLUMN_NAME).append("=").append("\'" + createdWordValue + "\'");

        String anotherWordClass = adverb;
        int anotherWordClassId = adverbID;

        try {
            /*this.userRequestManager.saveWordInformation(createdWordValue, noun, english);

            PreparedStatement checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString());
            checkStatement.execute();
            ResultSet resultSet = checkStatement.getResultSet();

            Assert.assertEquals(true, resultSet.next());
            Assert.assertEquals(createdWordValue, resultSet.getString(Word.VALUE_COLUMN_NAME));
            Assert.assertEquals(createdWordClass, resultSet.getInt(Word.WORD_CLASS_COLUMN_NAME));
            Assert.assertEquals(createdWordLanguage, resultSet.getInt(Word.LANGUAGE_COLUMN_NAME));
            Assert.assertEquals(false, resultSet.next());


            this.userRequestManager.saveWordInformation(createdWordValue, anotherWordClass, english);
            PreparedStatement secondCheckStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString());
            secondCheckStatement.execute();
            ResultSet secondResultSet = secondCheckStatement.getResultSet();

            Assert.assertEquals(true, secondResultSet.next());
            Assert.assertEquals(createdWordValue, secondResultSet.getString(Word.VALUE_COLUMN_NAME));
            Assert.assertEquals(anotherWordClassId, secondResultSet.getInt(Word.WORD_CLASS_COLUMN_NAME));
            Assert.assertEquals(createdWordLanguage, secondResultSet.getInt(Word.LANGUAGE_COLUMN_NAME));
            Assert.assertEquals(false, secondResultSet.next());*/
        } finally {
            PreparedStatement checkStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
            checkStatement.execute();
        }
    }


    @Test
    public void createExistingCollection() throws SQLException {
        try {
            this.userRequestManager.createCollection(firstCollection);
            throw new AssertionError("An IllegalArgumentException should be thrown if such collections already exists");
        } catch (IllegalArgumentException e) {
            /*nop*/
        }
    }

    @Test
    public void createNotExistingCollection() throws SQLException {
        StringBuilder checkQuery = new StringBuilder("");
        checkQuery.append("SELECT * FROM word_collections")
                .append(" WHERE ")
                .append(WordCollection.NAME_COLUMN_NAME).append("=").append("\'" + createdCollectionName + "\'");

        StringBuilder deleteQuery = new StringBuilder("");
        deleteQuery.append("DELETE FROM word_collections")
                .append(" WHERE ")
                .append(WordCollection.NAME_COLUMN_NAME).append("=").append("\'" + createdCollectionName + "\'");

        try {
            this.userRequestManager.createCollection(createdCollectionName);

            PreparedStatement checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString());
            checkStatement.execute();
            ResultSet resultSet = checkStatement.getResultSet();

            Assert.assertEquals(true, resultSet.next());
            Assert.assertEquals(createdCollectionName, resultSet.getString(WordCollection.NAME_COLUMN_NAME));
            Assert.assertEquals(false, resultSet.next());
        } finally {
            PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
            deleteStatement.execute();
        }
    }

    /**
     * Produces NPE if there is no such word or meaning in database
     *
     * @throws SQLException
     */
    @Ignore
    @Test
    public void addMeaning() throws SQLException {
/*
        this.userRequestManager.addMeaning(wordValue, german, additionalMeaningValue, english);

        // check that two relationships were created
        StringBuilder checkQuery;
        try {
            checkQuery = new StringBuilder("");
            checkQuery.append("SELECT * FROM word_meaning_relationships")
                    .append(" WHERE ")
                    .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                    .append(" AND ")
                    .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(additionalMeaningId).append(")")
                    .append(" OR ")
                    .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(additionalMeaningId)
                    .append(" AND ")
                    .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordId).append(")");
            PreparedStatement firstCheckStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString());
            firstCheckStatement.execute();
            ResultSet firstResultSet = firstCheckStatement.getResultSet();
            Assert.assertEquals(true, firstResultSet.next());
            Assert.assertEquals(true, firstResultSet.next());
            Assert.assertEquals(false, firstResultSet.next());
        } finally {
            StringBuilder deleteQuery = new StringBuilder("");
            deleteQuery.append("DELETE FROM word_meaning_relationships")
                    .append(" WHERE ")
                    .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                    .append(" OR ")
                    .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(additionalMeaningId);
            PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
            deleteStatement.execute();
        }*/
    }

    @Test
    public void addExistingMeaning() throws SQLException {
        try {
            this.userRequestManager.addMeaning(wordValue, german, firstMeaningValue, russian);
            throw new AssertionError("adding already existing meaning " +
                    "should cause an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            /*nop*/
        }
    }
    @Ignore
    @Test
    public void removeMeaning() throws SQLException {
        /*this.userRequestManager.removeMeaning(wordValue, german, firstMeaningValue, russian);
        StringBuilder firstCheckQuery = new StringBuilder("");
        firstCheckQuery.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(firstMeaningId);
        PreparedStatement firstCheckStatement = DatabaseManager.connection.prepareStatement(firstCheckQuery.toString());
        firstCheckStatement.execute();
        ResultSet firstResultSet = firstCheckStatement.getResultSet();
        Assert.assertEquals(false, firstResultSet.next());

        StringBuilder secondCheckQuery = new StringBuilder("");
        secondCheckQuery.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(firstMeaningId)
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordId);
        PreparedStatement secondCheckStatement = DatabaseManager.connection.prepareStatement(secondCheckQuery.toString());
        secondCheckStatement.execute();
        ResultSet secondResultSet = secondCheckStatement.getResultSet();
        Assert.assertEquals(false, secondResultSet.next());*/
    }

    @Test
    public void removeNotExistingMeaning() throws SQLException {
        try {
            this.userRequestManager.removeMeaning(wordValue, german, wordWithNoMeaningsValue, english);
            throw new AssertionError("removing not existing meaning " +
                    "should cause an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            /*nop*/
        }
    }

    @Test
    public void deleteWord() throws SQLException {
        this.userRequestManager.deleteWord(firstMeaningValue, russian);

        //assert there is no word information in the database
        StringBuilder zeroCheckQuery = new StringBuilder("");
        zeroCheckQuery.append("SELECT * FROM words")
                .append(" WHERE ")
                .append(Word.VALUE_COLUMN_NAME).append("=").append("\'" + firstMeaningValue + "\'")
                .append(" AND ")
                .append(Word.LANGUAGE_COLUMN_NAME).append("=").append(russianID);
        PreparedStatement zeroCheckStatement = DatabaseManager.connection.prepareStatement(zeroCheckQuery.toString());
        zeroCheckStatement.execute();
        ResultSet zeroResultSet = zeroCheckStatement.getResultSet();
        Assert.assertEquals(false, zeroResultSet.next());

        //assert there is no meaning relationships with deleted word
        StringBuilder firstCheckQuery = new StringBuilder("");
        firstCheckQuery.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(firstMeaningId);
        PreparedStatement firstCheckStatement = DatabaseManager.connection.prepareStatement(firstCheckQuery.toString());
        firstCheckStatement.execute();
        ResultSet firstResultSet = firstCheckStatement.getResultSet();
        Assert.assertEquals(false, firstResultSet.next());

        StringBuilder secondCheckQuery = new StringBuilder("");
        secondCheckQuery.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(firstMeaningId)
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordId);
        PreparedStatement secondCheckStatement = DatabaseManager.connection.prepareStatement(secondCheckQuery.toString());
        secondCheckStatement.execute();
        ResultSet secondResultSet = secondCheckStatement.getResultSet();
        Assert.assertEquals(false, secondResultSet.next());

        //assert there is no collection relationships with deleted word
        StringBuilder thirdCheckQuery = new StringBuilder("");
        thirdCheckQuery.append("SELECT * FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.WORD_COLUMN_NAME).append("=").append(firstMeaningId)
                .append(" AND ")
                .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append("=").append(firstCollectionId);
        PreparedStatement thirdCheckStatement = DatabaseManager.connection.prepareStatement(thirdCheckQuery.toString());
        thirdCheckStatement.execute();
        ResultSet thirdResultSet = secondCheckStatement.getResultSet();
        Assert.assertEquals(false, thirdResultSet.next());
    }
    @Ignore
    @Test
    public void deleteNotExistingWord() throws SQLException {
       /* try {
            this.userRequestManager.deleteWord(notExistingWord, english);
            throw new AssertionError("deleting not existing word " +
                    "should cause an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            *//*nop*//*
        }*/
    }

    @Test
    public void deleteExistingCollectionCollection() throws SQLException {
        this.userRequestManager.deleteCollection(firstCollection);
        StringBuilder checkQuery = new StringBuilder("");
        checkQuery.append("SELECT * FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append("=").append(firstCollectionId);
        PreparedStatement checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString());
        checkStatement.execute();
        ResultSet checkResultSet = checkStatement.getResultSet();
        Assert.assertEquals(false, checkResultSet.next());
    }

    @Test
    public void deleteNotExistingCollectionCollection() throws SQLException {
        try {
            this.userRequestManager.deleteCollection(notExistingCollection);
            throw new AssertionError("deleting not existing collection " +
                    "should cause an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            /*nop*/
        }
    }


    @Test
    public void putInCollection() throws SQLException {
        try {
            this.userRequestManager.putInCollection(firstMeaningValue, russian, secondCollection);
            StringBuilder checkQuery = new StringBuilder("");
            checkQuery.append("SELECT * FROM word_collection_relationships")
                    .append(" WHERE ")
                    .append(WordCollectionRelationship.WORD_COLUMN_NAME).append("=").append(firstMeaningId)
                    .append(" AND ")
                    .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append("=").append(secondCollectionId);
            PreparedStatement checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString());
            checkStatement.execute();
            ResultSet checkResultSet = checkStatement.getResultSet();
            Assert.assertEquals(true, checkResultSet.next());
            Assert.assertEquals(false, checkResultSet.next());
        } finally {
            StringBuilder deleteQuery = new StringBuilder("");
            deleteQuery.append("DELETE FROM word_collection_relationships")
                    .append(" WHERE ")
                    .append(WordCollectionRelationship.WORD_COLUMN_NAME).append("=").append(firstMeaningId)
                    .append(" AND ")
                    .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append("=").append(secondCollectionId);
            PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString());
            deleteStatement.execute();
        }
    }

    @Test
    public void putRepeatedlyInCollection() throws SQLException {
        try {
            this.userRequestManager.putInCollection(wordValue, german, firstCollection);
            throw new AssertionError("putting repeatedly a word in a collection " +
                    "should cause an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            /*nop*/
        }
    }

    @Test
    public void removeFromCollection() throws SQLException {
        this.userRequestManager.removeFromCollection(wordValue, german, firstCollection);
        StringBuilder checkQuery = new StringBuilder("");
        checkQuery.append("SELECT * FROM word_collection_relationships")
                .append(" WHERE ")
                .append(WordCollectionRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append(" AND ")
                .append(WordCollectionRelationship.COLLECTION_COLUMN_NAME).append("=").append(firstCollectionId);
        PreparedStatement checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString());
        checkStatement.execute();
        ResultSet checkResultSet = checkStatement.getResultSet();
        Assert.assertEquals(false, checkResultSet.next());
    }

    @Test
    public void removeFromCollectionWhereThereIsNoWord() throws SQLException {
        try {
            this.userRequestManager.removeFromCollection(firstMeaningValue, russian, secondCollection);
            throw new AssertionError("removing word from collection where there was no such word " +
                    "should cause an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            /*nop*/
        }
    }
}