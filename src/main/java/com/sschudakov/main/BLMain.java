package com.sschudakov.main;

import com.sschudakov.database.DatabaseManager;
import com.sschudakov.database.DatabaseSetup;
import com.sschudakov.factory.ServiceFactory;
import com.sschudakov.service.LanguageSrv;
import com.sschudakov.service.WordClassSrv;
import com.sschudakov.desktop.words.WordsCollectionsManager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class BLMain {

    private static final String ADJECTIVE_MIT_PRAPOSITIONEN_DOCX = "D:\\Workspace.java\\Vocabulary\\test_files\\Adjective mit Prapositionen.docx";
    private static final String BL_SRC_DIRECTORY = "D:\\Workspace.java\\Vocabulary\\Vocabulary_BL\\src";
    private static final String UI_SRC_DIRECTORY = "D:\\Workspace.java\\Vocabulary\\Vocabulary_UI\\src";
    private static final String JAVA_EXTENSION = ".java";
    private static final String GROOVY_EXTENSION = ".groovy";

    public static void main(String[] args) {
        try {
            insertData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void renewDatabaseContent() {
        try {
            dropTables();
            runHibernate();
            insertLanguagesAndClassesInfo();
            insertData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dropTables() throws SQLException {
        String dropWMR = "DROP TABLE word_meaning_relationships;";
        String dropWCR = "DROP TABLE word_collection_relationships;";
        String dropWords = "DROP TABLE words;";
        String dropClasses = "DROP TABLE word_classes;";
        String dropCollections = "DROP TABLE word_collections;";
        String dropLanguages = "DROP TABLE languages;";

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(dropWMR);

        statement.addBatch(dropWCR);
        statement.addBatch(dropWords);
        statement.addBatch(dropClasses);
        statement.addBatch(dropCollections);
        statement.addBatch(dropLanguages);

        statement.executeBatch();
    }

    private static void runHibernate() {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
    }

    private static void insertLanguagesAndClassesInfo() throws SQLException {
        DatabaseSetup databaseSetup = new DatabaseSetup();
        databaseSetup.setUpDatabase();

    }

    private static void insertData() throws SQLException {
        LanguageSrv languageSrv = ServiceFactory.createLanguageService();
        WordClassSrv wordClassSrv = ServiceFactory.createWordClassService();
        WordsCollectionsManager.persistCollectionIntoDatabase(
                "D:\\desktop\\words_collections\\ger\\Adjective aus Kopien.txt",
                languageSrv.findByName("German"),
                languageSrv.findByName("Russian"),
                wordClassSrv.findByName("adjective")
        );
        WordsCollectionsManager.persistCollectionIntoDatabase(
                "D:\\desktop\\words_collections\\ger\\Adjective mit Präpositionen.txt",
                languageSrv.findByName("German"),
                languageSrv.findByName("Russian"),
                wordClassSrv.findByName("adjective")
        );
        WordsCollectionsManager.persistCollectionIntoDatabase(
                "D:\\desktop\\words_collections\\ger\\Antonymen.txt",
                languageSrv.findByName("German"),
                languageSrv.findByName("Russian"),
                wordClassSrv.findByName("noun")
        );
        WordsCollectionsManager.persistCollectionIntoDatabase(
                "D:\\desktop\\words_collections\\eng\\The Financier.txt",
                languageSrv.findByName("English"),
                languageSrv.findByName("Russian"),
                wordClassSrv.findByName("expression")
        );

    }


    private static void countNumOfLinesInProject() {
        try {
            System.out.println(
                    numOfLines(new File(BL_SRC_DIRECTORY)) + numOfLines(new File(UI_SRC_DIRECTORY))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int numOfLines(File file) throws IOException {
        if (file.isDirectory()) {
            int result = 0;
            for (File file1 : Objects.requireNonNull(file.listFiles())) {
                result += numOfLines(file1);
            }
            return result;
        }
        return isJavaFile(file.getPath())
                || isGroovyFile(file.getPath()) ? countLines(file) : 0;
    }

    private static int countLines(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        int numOfLines = 0;
        while (reader.readLine() != null) numOfLines++;
        reader.close();
        System.out.println(file.getName() + " " + numOfLines);
        return numOfLines;
    }

    private static void readUsingAllCharsets() {
        //x-Big5-HKSCS-2001
        for (String s : Charset.availableCharsets().keySet()) {
//            System.out.println(Charset.availableCharsets().get(s));
            if (Charset.availableCharsets().get(s).toString().contains("windows") ||
                    Charset.availableCharsets().get(s).toString().contains("UTF")) {
                readFile(ADJECTIVE_MIT_PRAPOSITIONEN_DOCX, Charset.availableCharsets().get(s));
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

    private static boolean isJavaFile(String path) {
        return path.endsWith(JAVA_EXTENSION);
    }

    private static boolean isGroovyFile(String path) {
        return path.endsWith(GROOVY_EXTENSION);
    }
}
