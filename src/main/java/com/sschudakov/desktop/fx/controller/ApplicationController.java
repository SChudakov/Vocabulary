package com.sschudakov.desktop.fx.controller;

import com.sschudakov.desktop.fx.dao.FXAppDao;
import com.sschudakov.entity.Word;
import com.sschudakov.desktop.factory.DaoFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ApplicationController {

    private FXAppDao fxAppDao;
    private InputChecker inputChecker;
    private NumberConverter numberConverter;

    @FXML
    private TextField numOfMeaningsTF;
    @FXML
    private TextField minNumOfMeaningsTF;
    @FXML
    private TextField meaningTF;
    @FXML
    private TextField collectionTF;
    @FXML
    private TextField languageTF;

    @FXML
    private TextField atLeastSameMeaningsAsTF;
    @FXML
    private TextField sameCollectionsAsTF;
    @FXML
    private TextField noCommonMeaningsWithTF;

    @FXML
    private TableView<Word> mainTV;

    @FXML
    private TableColumn<Word, Integer> idColumn;
    @FXML
    private TableColumn<Word, String> wordColumn;
    @FXML
    private TableColumn<Word, String> wordClassColumn;
    @FXML
    private TableColumn<Word, String> languageColumn;


    public ApplicationController() {
        this.fxAppDao = new FXAppDao(DaoFactory.createLanguageDao(), DaoFactory.createWordClassDao());
        this.inputChecker = new InputChecker();
        this.numberConverter = new NumberConverter();
    }

    @FXML
    private void initialize() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.wordColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.wordClassColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()
                .getWordClass().getWordClassName()));
        this.languageColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()
                .getLanguage().getName()));
    }


    @FXML
    private void numOfMeaningsBClicked() {
        try {
            int numOfMeanings = this.numberConverter.convertNumber(this.numOfMeaningsTF.getText());
            this.inputChecker.checkNumOfMeanings(numOfMeanings);

            List<Word> list = this.fxAppDao.findWordsByNumOfMeanings(numOfMeanings);

            displayWordsList(list);
        } catch (Exception e) {
            showExceptionAlert(e);
        }
    }

    @FXML
    private void minNumOfMeaningsBClicked() {
        try {
            int minNumOfMeanings = this.numberConverter.convertNumber(this.minNumOfMeaningsTF.getText());
            this.inputChecker.checkMinNumOfMeanings(minNumOfMeanings);

            List<Word> list = this.fxAppDao.findWordsByMinNumOfMeanings(minNumOfMeanings);

            displayWordsList(list);
        } catch (Exception e) {
            showExceptionAlert(e);
        }
    }


    @FXML
    private void meaningBClicked() {
        try {
            String inputMeaning = this.meaningTF.getText();
            this.inputChecker.checkStringInput(inputMeaning);

            List<Word> list = this.fxAppDao.findWordsByMeaning(inputMeaning);
            displayWordsList(list);
        } catch (Exception e) {
            showExceptionAlert(e);
        }
    }

    @FXML
    private void collectionBClicked() {
        try {
            String inputCollectionName = this.collectionTF.getText();
            this.inputChecker.checkStringInput(inputCollectionName);

            List<Word> list = this.fxAppDao.findWordsByCollectionsName(inputCollectionName);
            displayWordsList(list);
        } catch (Exception e) {
            showExceptionAlert(e);
        }
    }

    @FXML
    private void languageBClicked() {
        try {
            String inputLanguageName = this.languageTF.getText();
            this.inputChecker.checkStringInput(inputLanguageName);

            List<Word> list = this.fxAppDao.findWordsByLanguageName(inputLanguageName);
            displayWordsList(list);
        } catch (Exception e) {
            showExceptionAlert(e);
        }
    }

    @FXML
    private void atLeastSameMeaningsAsBClicked() {
        try {
            String inputWord = this.atLeastSameMeaningsAsTF.getText();
            this.inputChecker.checkStringInput(inputWord);

            List<Word> list = this.fxAppDao.findWordsWithAtLeastSameMeaningsSet(inputWord);
            displayWordsList(list);
        } catch (Exception e) {
            showExceptionAlert(e);
        }
    }

    @FXML
    private void sameMeaningsAsBClicked() {
        try {
            String inputWord = this.sameCollectionsAsTF.getText();
            this.inputChecker.checkStringInput(inputWord);

            List<Word> list = this.fxAppDao.findWordsWithSameCollectionsSet(inputWord);
            displayWordsList(list);
        } catch (Exception e) {
            showExceptionAlert(e);
        }
    }

    @FXML
    private void meaningsInCommonBClicked() {
        try {
            String inputWord = noCommonMeaningsWithTF.getText();
            this.inputChecker.checkStringInput(inputWord);

            List<Word> list = this.fxAppDao.findWordsWithMeaningsInCommon(inputWord);
            displayWordsList(list);
        } catch (Exception e) {
            showExceptionAlert(e);
        }
    }

    private void displayWordsList(List<Word> words) {
        ObservableList<Word> wordsData = FXCollections.observableArrayList();
        wordsData.addAll(words);
        this.mainTV.setItems(wordsData);
    }

    private void showExceptionAlert(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception");
        alert.setContentText(exception.getMessage());
        alert.showAndWait();

        exception.printStackTrace();
    }
}
