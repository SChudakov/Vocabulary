import com.j256.ormlite.table.TableUtils;
import com.sschudakov.daos.relationships.WordMeaningRelationship;
import com.sschudakov.database.DatabaseManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;

public class Main {

    private static final String ADJECTIVE_MIT_PRÄPOSITIONEN_DOCX = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Präpositionen.docx";

    public static void main(String[] args) {
        try {
            TableUtils.dropTable(DatabaseManager.getConnectionSource(), WordMeaningRelationship.class, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void readUsingAllCharsets() {
        //x-Big5-HKSCS-2001
        for (String s : Charset.availableCharsets().keySet()) {
//            System.out.println(Charset.availableCharsets().get(s));
            if (Charset.availableCharsets().get(s).toString().contains("windows") ||
                    Charset.availableCharsets().get(s).toString().contains("UTF")) {
                readFile(ADJECTIVE_MIT_PRÄPOSITIONEN_DOCX, Charset.availableCharsets().get(s));
            }
        }
    }

    private static void readFile(String path, Charset charset) {

        System.out.println("\ncharset: " + charset + "\n");

        String line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(path), charset))) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
