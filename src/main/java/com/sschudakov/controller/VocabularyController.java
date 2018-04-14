package com.sschudakov.controller;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.service.LanguageSrv;
import com.sschudakov.service.WordCollectionSrv;
import com.sschudakov.service.WordSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class VocabularyController {

    private LanguageSrv languageSrv;
    private WordCollectionSrv wordCollectionSrv;
    private WordSrv wordSrv;

    @Autowired
    public VocabularyController(LanguageSrv languageSrv,
                                WordCollectionSrv wordCollectionSrv, WordSrv wordSrv) {
        this.languageSrv = languageSrv;
        this.wordCollectionSrv = wordCollectionSrv;
        this.wordSrv = wordSrv;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/words", method = RequestMethod.GET)
    public ModelAndView getWords() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/vocabulary/words");
        return modelAndView;
    }


    @RequestMapping(value = "/words/wordInfo", method = RequestMethod.GET)
    public ModelAndView getWordInfo(
            @RequestParam("value") String value,
            @RequestParam("language") String languageName,
            Model model
    ) throws SQLException {

        Language language = this.languageSrv.findByName(languageName);
        Word word = this.wordSrv.findByValueAndLanguage(value, language);

        model.addAttribute("word", word);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/vocabulary/wordInfo");

        return modelAndView;
    }


    @RequestMapping(value = "/collections", method = RequestMethod.GET)
    public ModelAndView getCollections() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/vocabulary/collections");
        return modelAndView;
    }

    @RequestMapping(value = "/collections/collectionInfo", method = RequestMethod.GET)
    public ModelAndView getCollectionInfo(
            @RequestParam("collectionName") String collectionsName,
            Model model
    ) throws SQLException {

        WordCollection collection = this.wordCollectionSrv.findByName(collectionsName);
        List<String> words = this.wordSrv.getCollectionWords(collection);

        model.addAttribute("words", words);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/vocabulary/collectionInfo");
        return modelAndView;
    }


    @RequestMapping(value = "/languages", method = RequestMethod.GET)
    public ModelAndView getLanguages() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/vocabulary/languages");
        return modelAndView;
    }

    @RequestMapping(value = "/languages/languageInfo", method = RequestMethod.GET)
    public ModelAndView getLanguageInfo(
            @RequestParam("languageName") String languageName,
            Model model
    ) throws SQLException {

        Language language = this.languageSrv.findByName(languageName);
        List<String> words = this.wordSrv.findByLanguage(language);

        model.addAttribute("words", words);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/vocabulary/languageInfo");
        return modelAndView;
    }
}
