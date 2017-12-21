import com.sschudakov.logging.LogsRemover;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Main {

    private static final String ADJECTIVE_MIT_PRÄPOSITIONEN_DOCX = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Präpositionen.docx";

    public static void main(String[] args) {
        System.out.println(Charset.defaultCharset());
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
