package com.sschudakov.request

import com.sschudakov.factory.UserRequestManagerFactory
import spock.lang.Shared
import spock.lang.Specification

class UserRequestManagerSpec extends Specification {

    @Shared
    UserRequestManager userRequestManager

    def setupSpec() {
        this.userRequestManager = UserRequestManagerFactory.createRequestManager()

    }

    def cleanupSpec() {

    }

    def "test getLanguages"() {

    }

    def "test getCollections"() {

    }

    def "test getClasses"() {

    }

    def "test getWordMeanings"() {

    }

    def "test getWordsByLanguageName"() {

    }

    def "test getWordClassByWord"() {

    }

    def "test getWordCollections"() {

    }

    def "test getCollectionWords"() {

    }

    def "test createCollection"() {

    }

    def "test deleteWord"() {

    }

    def "test deleteCollection"() {

    }

    def "test createWord"() {

    }

    def "test updateWord"() {

    }

    def "test addMeaning"() {

    }

    def "test removeMeaning"() {

    }

    def "test putInCollection"() {

    }

    def "test removeFromCollection"() {

    }

    def "test wordExists"() {

    }
}
