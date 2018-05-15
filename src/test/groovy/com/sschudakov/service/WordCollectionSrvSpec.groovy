package com.sschudakov.service

import com.sschudakov.desktop.database.DatabaseManager
import com.sschudakov.entity.WordCollection
import com.sschudakov.service.dao.WordCollectionSrv
import org.junit.Ignore
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.sql.PreparedStatement

@Stepwise
class WordCollectionSrvSpec extends Specification {

    private static final int firstCollectionId = 10000
    private static final int secondCollectionId = 10001
    private static final int thirdCollectionId = 10002
    private static final String firstCollection = "collection 1"
    private static final String secondCollection = "collection 2"
    /*During stepwise invocation of specification
    methods this collection will be created, updated an then deleted*/
    private static final String thirdCollection = "collection 3"
    private static final String thirdCollectionNewName = "third collection new name"
    private static final String notExistingCollection = "a not existing collection"
    private static final String notExistingCollectionNewName = "a not existing collection ne name"

    @Shared
    WordCollectionSrv wordCollectionSrv

    def setupSpec() {
        this.wordCollectionSrv = new WordCollectionSrv()

        StringBuilder insertQuery = new StringBuilder("")
        insertQuery.append("INSERT INTO word_collections ")
                .append("(").append(WordCollection.ID_CN).append(",").append(WordCollection.NAME_CN).append(")")
                .append(" VALUES ")
                .append("(").append(firstCollectionId).append(",").append("\'" + firstCollection + "\'").append(")").append(",")
                .append("(").append(secondCollectionId).append(",").append("\'" + secondCollection + "\'").append(")").append("")
        println insertQuery
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString())
        insertStatement.execute()
    }

    def cleanupSpec() {
        StringBuilder deleteQuery = new StringBuilder("")
        deleteQuery.append("DELETE FROM word_collections")
                .append(" WHERE ")
                .append("(").append(WordCollection.ID_CN).append("=").append(firstCollectionId)
                .append(" AND ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + firstCollection + "\'").append(")")
                .append(" OR ")
                .append("(").append(WordCollection.ID_CN).append("=").append(secondCollectionId)
                .append(" AND ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + secondCollection + "\'").append(")")
        println deleteQuery
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString())
        deleteStatement.execute()
    }

    def "test create on not existing word collection"() {
        given: "a query that checks that a word collection has been created"
        def checkQuery = new StringBuilder("")
        checkQuery.append("SELECT * FROM word_collections")
                .append(" WHERE ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + thirdCollection + "\'")
        def checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString())
        def resultSet

        when: "a word collection is being created"
        this.wordCollectionSrv.create(thirdCollection)
        checkStatement.execute()
        resultSet = checkStatement.getResultSet()

        then: "there is exactly one such word collection in the database"
        resultSet.next()
        !resultSet.next()
    }

    def "test creation existing word collection"() {
        given: "a query that checks that a duplicate word collection has not been created"
        def checkQuery = new StringBuilder("")
        checkQuery.append("SELECT * FROM word_collections")
                .append(" WHERE ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + firstCollection + "\'")
        def checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString())
        def resultSet

        when: "a duplicate word collection is tried to be created"
        this.wordCollectionSrv.create(firstCollection)

        then: "an illegal argument exception is thrown"
        thrown(IllegalArgumentException)


        when: "check statement is executed"
        checkStatement.execute()
        resultSet = checkStatement.getResultSet()

        then: "there is exactly one such word collection in the database"
        resultSet.next()
        !resultSet.next()
    }

    @Ignore
    def "test update on existing collection"() {
        given: "a query that checks that a word collection has been updated correctly"
        def checkQuery = new StringBuilder("")
        checkQuery.append("SELECT * FROM word_collections")
                .append(" WHERE ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + thirdCollectionNewName + "\'")
        def checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString())
        def resultSet

        // service lair methods have been changed

        when: "a word collection is being created"
        this.wordCollectionSrv.update(thirdCollection, thirdCollectionNewName)
        checkStatement.execute()
        resultSet = checkStatement.getResultSet()

        then: "there is exactly one such word collection in the database"
        resultSet.next()
        !resultSet.next()

    }

    @Ignore
    def "test update on not existing collection"() {
        // service lair methods have been changed
        /*when: "a not existing collection is being tried to be updated"
        this.wordCollectionRepository.update(notExistingCollection, notExistingCollectionNewName)

        then: "an IllegalArgumentException is thrown"
        thrown(IllegalArgumentException)*/
    }


    def "test delete on existing collection"() {
        given: "a query that checks that a collection is deleted from the database"
        def checkQuery = new StringBuilder("")
        checkQuery.append("SELECT * FROM word_collections")
                .append(" WHERE ")
                .append(WordCollection.NAME_CN).append("=").append("\'" + thirdCollectionNewName + "\'")
        def checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString())
        def resultSet

        when: "a collection is deleted"
        this.wordCollectionSrv.deleteByName(thirdCollectionNewName)
        and: "he check query is executed"
        checkStatement.execute()
        resultSet = checkStatement.getResultSet()

        then: "there is no collection in the database"
        !resultSet.next()
    }

    def "test delete on not existing collection"() {
        when: "a not existing collection is being tried to be deleted"
        this.wordCollectionSrv.deleteByName(notExistingCollection)

        then: "an IllegalArgumentException is thrown"
        thrown(IllegalArgumentException)
    }


    def "test finding existing word collection by id"() {
        when: "an existing word collection is being searches by id"
        def foundCollection = this.wordCollectionSrv.findById(firstCollectionId)

        then: "it is found"
        foundCollection != null
        foundCollection.getId() == firstCollectionId
        foundCollection.getCollectionName() == firstCollection
    }

    def "test finding not existing word collection by id"() {
        when: "a not existing word collection is being searched by id"
        def foundCollection = this.wordCollectionSrv.findById(thirdCollectionId)

        then: "no exception is thrown"
        notThrown(Exception)
        and: "no word collection is found"
        foundCollection == null
    }


    def "test finding existing word collection by name"() {
        when: "an existing word collection is being searches by name"
        def foundCollection = this.wordCollectionSrv.findByName(firstCollection)

        then: "it is found"
        foundCollection != null
        foundCollection.getId() == firstCollectionId
        foundCollection.getCollectionName() == firstCollection
    }

    def "test finding not existing word collection by name"() {
        when: "a not existing word collection is being searches by name"
        def foundCollection = this.wordCollectionSrv.findByName(thirdCollection)

        then: "no exception is thrown"
        notThrown(Exception)
        and: "not word collection is found"
        foundCollection == null
    }


    def "test finding all languages"() {
        when: "all word collection are searched for"
        def wordCollections = this.wordCollectionSrv.findAll();

        then: "a resulting list contains all existing word collections"
        wordCollections.contains(firstCollection)
        wordCollections.contains(secondCollection)
    }

}
