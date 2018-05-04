package com.sschudakov.web.controller;

import com.sschudakov.dao.springdata.LanguageRepository;
import com.sschudakov.dao.springdata.WordCollectionRepository;
import com.sschudakov.dao.springdata.WordRepository;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class VocabularyController {

    private LanguageRepository languageRepository;
    private WordCollectionRepository wordCollectionRepository;
    private WordRepository wordRepository;

    @Autowired
    public VocabularyController(LanguageRepository languageRepository,
                                WordCollectionRepository wordCollectionRepository,
                                WordRepository wordRepository) {
        this.languageRepository = languageRepository;
        this.wordCollectionRepository = wordCollectionRepository;
        this.wordRepository = wordRepository;
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
    ) {
        Optional<Language> optionalLanguage = this.languageRepository.getByName(languageName);
        ModelAndView modelAndView = new ModelAndView();

        if (optionalLanguage.isPresent()) {

            Language language = optionalLanguage.get();
            Optional<Word> optionalWord = this.wordRepository.getByValueAndLanguage(value, language);

            if (optionalWord.isPresent()) {

                Word word = optionalWord.get();

                model.addAttribute("word", word);
                modelAndView.setViewName("/vocabulary/wordInfo");

            } else {

                model.addAttribute("languageName", languageName);
                model.addAttribute("wordValue", value);
                modelAndView.setViewName("/wordNotFound");

            }

        } else {

            model.addAttribute("languageName", languageName);
            modelAndView.setViewName("/languageNotFound");

        }

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
    ) {

        Optional<WordCollection> optionalCollection = this.wordCollectionRepository.getByCollectionName(collectionsName);
        ModelAndView modelAndView = new ModelAndView();


        if (optionalCollection.isPresent()) {

            WordCollection collection = optionalCollection.get();
            List<Word> words = this.wordRepository.getByCollection(collection);
            List<String> wordsValues = words.stream().map(Word::getValue).collect(Collectors.toList());

            model.addAttribute("words", wordsValues);
            modelAndView.setViewName("/vocabulary/collectionInfo");

        } else {

            model.addAttribute("collectionName", collectionsName);
            modelAndView.setViewName("/collectionNotFound");

        }
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
    ) {

        Optional<Language> optionalLanguage = this.languageRepository.getByName(languageName);
        ModelAndView modelAndView = new ModelAndView();


        if (optionalLanguage.isPresent()) {

            Language language = optionalLanguage.get();
            List<Word> words = this.wordRepository.getByLanguage(language);
            List<String> wordsValues = words.stream().map(Word::getValue).collect(Collectors.toList());


            model.addAttribute("words", wordsValues);
            modelAndView.setViewName("/vocabulary/languageInfo");
        } else {

            model.addAttribute("languageName", languageName);
            modelAndView.setViewName("/languageNotFound");

        }

        return modelAndView;
    }
}
