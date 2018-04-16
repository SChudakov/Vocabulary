package com.sschudakov.desktop.swing.request

import com.sschudakov.service.LanguageSrv
import com.sschudakov.service.WordClassSrv
import com.sschudakov.service.WordCollectionSrv
import com.sschudakov.service.WordSrv
import spock.lang.Shared
import spock.lang.Specification

class UserRequestManagerSpec extends Specification {

    @Shared
    UserRequestManager userRequestManager
    @Shared
    LanguageSrv languageSrv
    @Shared
    WordClassSrv wordClassSrv
    @Shared
    WordCollectionSrv wordCollectionSrv
    @Shared
    WordSrv wordSrv

    def setupSpec() {
        this.languageSrv = Mock()
        this.wordClassSrv = Mock()
        this.wordCollectionSrv = Mock()
        this.wordSrv = Mock()
        this.userRequestManager = new UserRequestManager(
                this.languageSrv,
                this.wordClassSrv,
                this.wordCollectionSrv,
                this.wordSrv
        )
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
