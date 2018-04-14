package com.sschudakov.database;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.WordClass;
import com.sschudakov.factory.ServiceFactory;
import com.sschudakov.service.LanguageSrv;
import com.sschudakov.service.WordClassSrv;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseSetup {

    private LanguageSrv languageSrv;
    private WordClassSrv wordClassSrv;

    private List<String> languages;
    private List<String> wordClasses;

    public DatabaseSetup() {
        this.languageSrv = ServiceFactory.createLanguageService();
        this.wordClassSrv = ServiceFactory.createWordClassService();

        this.languages = new ArrayList<>();
        this.wordClasses = new ArrayList<>();

        this.languages.add("English");
        this.languages.add("Russian");
        this.languages.add("German");

        this.wordClasses.add("noun");
        this.wordClasses.add("verb");
        this.wordClasses.add("adjective");
        this.wordClasses.add("adverb");
        this.wordClasses.add("pronoun");
        this.wordClasses.add("preposition");
        this.wordClasses.add("conjunction");
        this.wordClasses.add("collocation");
        this.wordClasses.add("expression");
    }

    public void setUpDatabase() throws SQLException {
        setUpLanguages();
        setUpWordClasses();
    }

    private void setUpLanguages() throws SQLException {
        List<String> existingLanguages = languageSrv.findAll();
        List<String> languagesToBeAdded = this.languages.stream().filter(a -> !existingLanguages.contains(a))
                .collect(Collectors.toList());
        List<String> languagesToDeleted = existingLanguages.stream().filter(a -> !this.languages.contains(a))
                .collect(Collectors.toList());

        for (String s : languagesToBeAdded) {
            this.languageSrv.create(s);
        }
        for (String s : languagesToDeleted) {
            deleteLanguage(s);
        }
    }

    private void deleteLanguage(String languageName) throws SQLException {
        StringBuilder insert = new StringBuilder("");
        insert.append("DELETE FROM languages")
                .append(" WHERE ")
                .append(Language.NAME_CN).append("=").append("\'" + languageName + "\'");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(insert.toString());
        statement.execute();
    }

    private void setUpWordClasses() throws SQLException {
        List<String> existingClasses = this.wordClassSrv.findAll();
        List<String> classesToBeAdded = this.wordClasses.stream().filter(a -> !existingClasses.contains(a))
                .collect(Collectors.toList());
        List<String> classesToDeleted = existingClasses.stream().filter(a -> !this.wordClasses.contains(a))
                .collect(Collectors.toList());

        for (String s : classesToBeAdded) {
            this.wordClassSrv.create(s);
        }
        for (String s : classesToDeleted) {
            deleteWordClass(s);
        }
    }

    private void deleteWordClass(String className) throws SQLException {
        StringBuilder insert = new StringBuilder("");
        insert.append("DELETE FROM word_classes")
                .append(" WHERE ")
                .append(WordClass.NAME_CN).append("=").append("\'" + className + "\'");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(insert.toString());
        statement.execute();
    }
}
