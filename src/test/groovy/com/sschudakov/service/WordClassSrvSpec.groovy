package com.sschudakov.service

import com.sschudakov.database.DatabaseManager
import com.sschudakov.entity.WordClass
import spock.lang.Shared
import spock.lang.Specification

import java.sql.PreparedStatement

class WordClassSrvSpec extends Specification {

    private static final int nounID = 10000
    private static final int verbID = 10001
    private static final int adverbID = 10002
    private static final String noun = "nounn"
    private static final String verb = "verbb"
    private static final String adverb = "adverbb"

    @Shared
    WordClassSrv wordClassSrv

    def setupSpec() {
        this.wordClassSrv = new WordClassSrv()
        StringBuilder insertQuery = new StringBuilder("")
        insertQuery.append("INSERT INTO word_classes ")
                .append("(").append(WordClass.ID_CN).append(",").append(WordClass.NAME_CN).append(")")
                .append(" VALUES ")
                .append("(").append(nounID).append(",").append("\'" + noun + "\'").append(")").append(",")
                .append("(").append(verbID).append(",").append("\'" + verb + "\'").append(")").append(";")
        println insertQuery
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString())
        insertStatement.execute()
    }

    def cleanupSpec() {
        StringBuilder deleteQuery = new StringBuilder("")
        deleteQuery.append("DELETE FROM word_classes")
                .append(" WHERE ")
                .append("(").append(WordClass.ID_CN).append("=").append(nounID)
                .append(" AND ")
                .append(WordClass.NAME_CN).append("=").append("\'" + noun + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordClass.ID_CN).append("=").append(verbID)
                .append(" AND ")
                .append(WordClass.NAME_CN).append("=").append("\'" + verb + "\'").append(")")
        println deleteQuery
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString())
        deleteStatement.execute()
    }


    def "test create on not existing word class"() {
        given: "a query that checks that a word class has been created"
        def checkQuery = new StringBuilder("")
        checkQuery.append("SELECT * FROM word_classes")
                .append(" WHERE ")
                .append(WordClass.NAME_CN).append("=").append("\'" + adverb + "\'")
        def checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString())
        and: "a query that deletes created word class"
        def deleteQuery = new StringBuilder("")
        deleteQuery.append("DELETE FROM word_classes")
                .append(" WHERE ")
                .append(WordClass.NAME_CN).append("=").append("\'" + adverb + "\'")
        def deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString())
        def resultSet

        when: "a word class is being created"
        this.wordClassSrv.create(adverb)
        checkStatement.execute()
        resultSet = checkStatement.getResultSet()
        deleteStatement.execute() // TODO: bad practice

        then: "there is exactly one such word class in the database"
        resultSet.next()
        !resultSet.next()
    }

    def "test creation existing word class"() {
        given: "a query that checks that a duplicate word class has not been created"
        def checkQuery = new StringBuilder("")
        checkQuery.append("SELECT * FROM word_classes")
                .append(" WHERE ")
                .append(WordClass.NAME_CN).append("=").append("\'" + noun + "\'")
        def checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString())
        def resultSet

        when: "a duplicate word class is tried to be created"
        this.wordClassSrv.create(noun)

        then: "an exception is thrown"
        thrown(IllegalArgumentException)


        when: "check statement is executed"
        checkStatement.execute()
        resultSet = checkStatement.getResultSet()

        then: "there is exactly one such word class in the database"
        resultSet.next()
        !resultSet.next()
    }


    def "test finding existing word class by id"() {
        when: "an existing word class is being searches by id"
        def foundClass = this.wordClassSrv.findById(nounID)

        then: "it is found"
        foundClass != null
        foundClass.getId() == nounID
        foundClass.getWordClassName() == noun
    }

    def "test finding not existing word class by id"() {
        when: "a not existing word class is being searched by id"
        def foundClass = this.wordClassSrv.findById(adverbID)

        then: "no exception is thrown"
        notThrown(Exception)
        and: "no word class is found"
        foundClass == null
    }


    def "test finding existing word class by name"() {
        when: "an existing word class is being searches by name"
        def foundWordClass = this.wordClassSrv.findByName(noun)

        then: "it is found"
        foundWordClass != null
        foundWordClass.getId() == nounID
        foundWordClass.getWordClassName() == noun
    }

    def "test finding not existing word class by name"() {
        when: "a not existing word class is being searches by name"
        def foundWordClass = this.wordClassSrv.findByName(adverb)

        then: "no exception is thrown"
        notThrown(Exception)
        and: "not word class is found"
        foundWordClass == null
    }


    def "test finding all languages"() {
        when: "all word classes are searched for"
        def wordClasses = this.wordClassSrv.findAll();

        then: "a resulting list contains all word classes"
        wordClasses.contains(noun)
        wordClasses.contains(verb)
    }
}
