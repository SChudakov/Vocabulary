package com.sschudakov.desktop.words.parsing

import spock.lang.Shared
import spock.lang.Specification

class FileParserSpec extends Specification {
    private static
    final String PATH_TO_FILE = "D:\\Workspace.java\\Vocabulary\\words_collections\\ger\\Adjective aus Kopien.txt"

    @Shared
    FileParser fileParser

    def setupSpec() {
        this.fileParser = new FileParser()
    }

    def "test parse"() {

        when:
        def readCollection = this.fileParser.parse(PATH_TO_FILE)
        for (Map.Entry<String, List<String>> entry : readCollection.entrySet() ) {
            println "word: " + entry.getKey() + " meanings: " + entry.getValue()
        }
        then:
        notThrown(Exception)
    }
}
