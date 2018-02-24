package com.sschudakov.parsing;

import com.sschudakov.entity.Word;
import com.sschudakov.logging.LogsRemover;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Semen Chudakov on 15.12.2017.
 */
public class FileParserTest {

    private static final String ADJECTIVE_MIT_PRÄPOSITIONEN_DOCX = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Präpositionen.docx";
    private static final String ADJECTIVE_MIT_PRÄPOSITIONEN_TXT = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Präpositionen.txt";
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
