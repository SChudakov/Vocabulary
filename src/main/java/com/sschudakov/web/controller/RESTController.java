package com.sschudakov.web.controller;


import com.sschudakov.dao.springdata.LanguageRepository;
import com.sschudakov.dao.springdata.WordCollectionRepository;
import com.sschudakov.dao.springdata.WordRepository;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RESTController {

    /*private final WordRepository wordRepository;
    private final WordCollectionRepository wordCollectionRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public RESTController(WordRepository wordRepository, LanguageRepository languageRepository, WordCollectionRepository wordCollectionRepository) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.wordCollectionRepository = wordCollectionRepository;
    }

    @RequestMapping(value = "/words/wordInfo", method = RequestMethod.GET)
    public ResponseEntity<Word> getWordInfo(
            @RequestParam("value") String value,
            @RequestParam("language") String languageName
    ) {
        ResponseEntity<Word> result;

        Optional<Language> language = this.languageRepository.getByName(languageName);

        if (language.isPresent()) {
            Optional<Word> word = this.wordRepository.getByValueAndLanguage(value, language.get());

            result = word.map(word1 -> new ResponseEntity<>(word1, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @RequestMapping(value = "/collections/collectionInfo", method = RequestMethod.GET)
    public ResponseEntity<List<Word>> getCollectionInfo(
            @RequestParam("collectionName") String collectionsName
    ) {
        ResponseEntity<List<Word>> result;

        Optional<WordCollection> optionalWordCollection = this.wordCollectionRepository.getByCollectionName(collectionsName);

        if (optionalWordCollection.isPresent()) {
            List<Word> words = this.wordRepository.getByCollection(optionalWordCollection.get());
            result = new ResponseEntity<>(words, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return result;
    }

    @RequestMapping(value = "/languages/languageInfo", method = RequestMethod.GET)
    public ResponseEntity<List<Word>> getLanguageInfo(
            @RequestParam("languageName") String languageName
    ) {
        ResponseEntity<List<Word>> result;

        Optional<Language> language = this.languageRepository.getByName(languageName);

        if (language.isPresent()) {
            List<Word> words = this.wordRepository.getByLanguage(language.get());
            result = new ResponseEntity<>(words, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return result;
    }*/
}
