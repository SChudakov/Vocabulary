package com.sschudakov.desktop.words.parsing;

import org.junit.Test;

/**
 * Created by Semen Chudakov on 15.12.2017.
 */
public class FileParserTest {

    private static final String ADJECTIVE_MIT_PRAPOSITIONEN_DOCX = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Prapositionen.docx";
    private static final String ADJECTIVE_MIT_PRAPOSITIONEN_TXT = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Prapositionen.txt";
    private static final String ADJECTIVE_TXT = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective.txt";


    @Test
    public void parseTest() {
        /*LogsRemover.removeLogs();
        HashMap<Word, List<Word>> result = FileParser.parse(ADJECTIVE_TXT);
        for (Word word : result.keySet()) {
            System.out.println("word: " + word + " meanings: " + result.get(word));
        }*/
    }

}
