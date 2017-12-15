package com.sschudakov.parsing;

import com.sschudakov.bins.Word;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Semen Chudakov on 15.12.2017.
 */
public class FileParserTest {

    private static final String ADJECTIVE_MIT_PRÄPOSITIONEN_DOCX = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Präpositionen.doc";
    private static final String ADJECTIVE_MIT_PRÄPOSITIONEN_TXT = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Präpositionen.txt";
    private static final String ADJECTIVE_TXT = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective.txt";


    @Test
    public void parseTest() {
        HashMap<Word, List<Word>> result = FileParser.parse(ADJECTIVE_MIT_PRÄPOSITIONEN_DOCX);
        for (Word word : result.keySet()) {
            System.out.println("word: " + word + " meanings: " + result.get(word));
        }
    }

}
