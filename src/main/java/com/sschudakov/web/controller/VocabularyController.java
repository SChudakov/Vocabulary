package com.sschudakov.web.controller;

import com.sschudakov.dao.springdata.LanguageRepository;
import com.sschudakov.dao.springdata.WordCollectionRepository;
import com.sschudakov.dao.springdata.WordRepository;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordMeaningRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
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
    public ModelAndView getWords(Model model) {
        model.addAttribute("languages", languageRepository.findAll());
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
        model.addAttribute("languages", languageRepository.findAll());
        Optional<Language> optionalLanguage = this.languageRepository.getByName(languageName);
        ModelAndView modelAndView = new ModelAndView();

        if (optionalLanguage.isPresent()) {
            Language language = optionalLanguage.get();
            Optional<Word> optionalWord = this.wordRepository.getByValueAndLanguage(value, language);

            if (optionalWord.isPresent()) {
                Word word = optionalWord.get();
                model.addAttribute("word", word);
                model.addAttribute("collections",
                        wordCollectionRepository.getCollectionsByWord(word));
                model.addAttribute("meanings",
                        wordRepository.getMeaningsByWord(word));
            } else {
                model.addAttribute("languageName", languageName);
                model.addAttribute("wordValue", value);
            }
        } else {
            model.addAttribute("languageName", languageName);
        }
        modelAndView.setViewName("/vocabulary/words");
        return modelAndView;
    }

    @RequestMapping(value = "/collections", method = RequestMethod.GET)
    public ModelAndView getCollections(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/vocabulary/collections");
        model.addAttribute("collections", wordCollectionRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/collections/collectionInfo", method = RequestMethod.GET)
    public ModelAndView getCollectionInfo(
            @RequestParam("collectionName") String collectionsName,
            Model model
    ) {
        Optional<WordCollection> optionalCollection = this.wordCollectionRepository.getByCollectionName(collectionsName);
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("collections", wordCollectionRepository.findAll());
        if (optionalCollection.isPresent()) {
            WordCollection collection = optionalCollection.get();
            List<Word> words = this.wordRepository.getByCollection(collection);
            model.addAttribute("collection", collection);
            model.addAttribute("words", words);
        } else {
            model.addAttribute("collectionName", collectionsName);
            modelAndView.setViewName("/collectionNotFound");
        }
        modelAndView.setViewName("/vocabulary/collections");
        return modelAndView;
    }

    @RequestMapping(value = "/languages", method = RequestMethod.GET)
    public ModelAndView getLanguages(Model model) {
        model.addAttribute("languages", languageRepository.findAll());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/vocabulary/languages");
        return modelAndView;
    }

    @RequestMapping(value = "/languages/languageInfo", method = RequestMethod.GET)
    public ModelAndView getLanguageInfo(
            @RequestParam("languageName") String languageName,
            Model model
    ) {
        model.addAttribute("languages", languageRepository.findAll());
        Optional<Language> optionalLanguage = this.languageRepository.getByName(languageName);
        ModelAndView modelAndView = new ModelAndView();

        if (optionalLanguage.isPresent()) {
            Language language = optionalLanguage.get();
            List<Word> words = this.wordRepository.getByLanguage(language);
            model.addAttribute("words", words);
            Map<Word, String> meanings = new HashMap<>();
            for (WordMeaningRelationship wmr : wordRepository.getAllMeaningsByLanguage(language)) {
                if (meanings.containsKey(wmr.getWord())) {
                    meanings.put(wmr.getWord(),
                            meanings.get(wmr.getWord()) + ", " +
                                    wmr.getMeaning().getCapitalizedValue());
                } else {
                    meanings.put(wmr.getWord(), wmr.getMeaning().getCapitalizedValue());
                }
            }
            model.addAttribute("meanings", meanings);
        } else {
            model.addAttribute("languageName", languageName);
        }
        modelAndView.setViewName("/vocabulary/languages");
        return modelAndView;
    }
}
