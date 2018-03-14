package com.sschudakov;

import com.sschudakov.factory.ServiceFactory;
import com.sschudakov.service.LanguageSrv;
import com.sschudakov.service.WordClassSrv;
import com.sschudakov.words.WordsCollectionsManager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;

public class Main {

    private static final String ADJECTIVE_MIT_PRAPOSITIONEN_DOCX = "D:\\Workspace.java\\Vocabulary" +
            "\\test_files\\Adjective mit Prapositionen.docx";

    public static void main(String[] args) {
        /*runHibernate();*/
        insertData();
    }

    private static void insertData(){
        LanguageSrv languageSrv = ServiceFactory.createLanguageService();
        WordClassSrv wordClassSrv = ServiceFactory.createWordClassService();
        try {
            WordsCollectionsManager.persistCollectionIntoDatabase(
                    "D:\\Workspace.java\\Vocabulary\\words_collections\\ger\\Adjective aus Kopien.txt",
                    languageSrv.findByName("German"),
                    languageSrv.findByName("Russian"),
                    wordClassSrv.findByName("adkective")
            );
            WordsCollectionsManager.persistCollectionIntoDatabase(
                    "D:\\Workspace.java\\Vocabulary\\words_collections\\ger\\Adjective mit Präpositionen.txt",
                    languageSrv.findByName("German"),
                    languageSrv.findByName("Russian"),
                    wordClassSrv.findByName("adkective")
            );
            WordsCollectionsManager.persistCollectionIntoDatabase(
                    "D:\\Workspace.java\\Vocabulary\\words_collections\\ger\\Antonymen.txt",
                    languageSrv.findByName("German"),
                    languageSrv.findByName("Russian"),
                    wordClassSrv.findByName("noun")
            );
            WordsCollectionsManager.persistCollectionIntoDatabase(
                    "D:\\Workspace.java\\Vocabulary\\words_collections\\eng\\The Financier.txt",
                    languageSrv.findByName("English"),
                    languageSrv.findByName("Russian"),
                    wordClassSrv.findByName("expression")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void runHibernate(){
         EntityManager entityManager = Persistence
                 .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                 .createEntityManager();
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
}
