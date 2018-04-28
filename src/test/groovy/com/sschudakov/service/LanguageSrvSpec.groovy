package com.sschudakov.service

import com.sschudakov.database.DatabaseManager
import com.sschudakov.entity.Language
import spock.lang.Shared
import spock.lang.Specification

import java.sql.PreparedStatement
import java.sql.SQLException

class LanguageSrvSpec extends Specification {

    private static final int englishId = 10000
    private static final int russianId = 10001
    private static final int germanId = 10002
    private static final String english = "englishh"
    private static final String russian = "russiann"
    private static final String german = "germann"

    @Shared
    LanguageSrv languageSrv

    def setupSpec() {
        this.languageSrv = new LanguageSrv()
        StringBuilder insertQuery = new StringBuilder("")
        insertQuery.append("INSERT INTO languages ")
                .append("(").append(Language.ID_CN).append(",").append(Language.NAME_CN).append(")")
                .append(" VALUES ")
                .append("(").append(englishId).append(",").append("\'" + english + "\'").append(")").append(",")
                .append("(").append(russianId).append(",").append("\'" + russian + "\'").append(")").append("")
        println insertQuery
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString())
        insertStatement.execute()
    }

    def cleanupSpec() {
        StringBuilder deleteQuery = new StringBuilder("")
        deleteQuery.append("DELETE FROM languages")
                .append(" WHERE ")
                .append("(").append(Language.ID_CN).append("=").append(englishId)
                .append(" AND ")
                .append(Language.NAME_CN).append("=").append("\'" + english + "\'").append(")")
                .append(" OR ")
                .append("(").append(Language.ID_CN).append("=").append(russianId)
                .append(" AND ")
                .append(Language.NAME_CN).append("=").append("\'" + russian + "\'").append(")")
        println deleteQuery
        PreparedStatement deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString())
        deleteStatement.execute()
    }


    def "test creation not existing language"() {
        given: "a query that checks that a language has been created"
        def checkQuery = new StringBuilder("")
        checkQuery.append("SELECT * FROM languages")
                .append(" WHERE ")
                .append(Language.NAME_CN).append("=").append("\'" + german + "\'")
        def checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString())
        and: "a query that deletes created language"
        def deleteQuery = new StringBuilder("")
        deleteQuery.append("DELETE FROM languages")
                .append(" WHERE ")
                .append(Language.NAME_CN).append("=").append("\'" + german + "\'")
        def deleteStatement = DatabaseManager.connection.prepareStatement(deleteQuery.toString())
        def resultSet

        when: "a language is being created"
        this.languageSrv.create(german)
        checkStatement.execute()
        resultSet = checkStatement.getResultSet()
        deleteStatement.execute()// TODO: bad practice

        then: "there is only one such language in the database"
        notThrown(SQLException)
        resultSet.next()
        !resultSet.next()
    }

    def "test creation existing language"() {
        given: "a query that checks that a duplicate language has not been created"
        def checkQuery = new StringBuilder("")
        checkQuery.append("SELECT * FROM languages")
                .append(" WHERE ")
                .append(Language.NAME_CN).append("=").append("\'" + english + "\'")
        def checkStatement = DatabaseManager.connection.prepareStatement(checkQuery.toString())
        def resultSet

        when: "a duplicate language is tried to be created"
        this.languageSrv.create(english)

        then: "an illegal argument exception is thrown"
        thrown(IllegalArgumentException)

        when: "check statement is executed"
        checkStatement.execute()
        resultSet = checkStatement.getResultSet()

        then: "there is exactly one such language in the database"
        resultSet.next()
        !resultSet.next()
    }


    def "test finding existing language by id"() {
        when: "an existing language is being searches by id"
        def foundLanguage = this.languageSrv.findById(englishId)

        then: "it is found"
        foundLanguage != null
        foundLanguage.getId() == englishId
        foundLanguage.getName().equals(english)
    }

    def "test finding not existing language by id"() {
        when: "a not existing language is being searched by id"
        def foundLanguage = this.languageSrv.findById(germanId)

        then: "no exception is thrown"
        notThrown(Exception)
        and: "no language is found"
        foundLanguage == null
    }


    def "test finding existing language by name"() {
        when: "an existing language is being searches by name"
        def foundLanguage = this.languageSrv.findByName(russian)

        then: "it is found"
        foundLanguage != null
        foundLanguage.getId() == russianId
        foundLanguage.getName() == russian
    }

    def "test finding not existing language by name"() {
        when: "a not existing language is being searches by name"
        def foundLanguage = this.languageSrv.findByName(german)

        then: "no exception is thrown"
        notThrown(Exception)
        and: "not language is found"
        foundLanguage == null
    }


    def "test finding all languages"() {
        when: "all languages are searched for"
        def languages = this.languageSrv.findAll();

        then: "a resulting list contains all languages"
        languages.contains(english)
        languages.contains(russian)
    }
}
