package com.sschudakov.desktop.database;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.WordClass;
import com.sschudakov.desktop.factory.ServiceFactory;
import com.sschudakov.service.dao.LanguageSrv;
import com.sschudakov.service.dao.WordClassSrv;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseCache {
    private static DatabaseCache ourInstance = new DatabaseCache();

    private LanguageSrv languageSrv;
    private WordClassSrv wordClassSrv;

    private Map<Integer, Language> cachedLanguages;
    private Map<Integer, WordClass> cachedWordClasses;

    public static DatabaseCache getInstance() {
        return ourInstance;
    }

    private DatabaseCache() {
        this.languageSrv = ServiceFactory.createLanguageService();
        this.wordClassSrv = ServiceFactory.createWordClassService();

        this.cachedLanguages = new HashMap<>();
        this.cachedWordClasses = new HashMap<>();

        cacheLanguages();
        cacheWordClasses();
    }

    private void cacheLanguages() {
        try {
            this.languageSrv.findAllObjects().forEach(
                    language -> this.cachedLanguages.put(language.getId(), language)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cacheWordClasses() {
        try {
            this.wordClassSrv.findAllObjects().forEach(
                    wordClass -> this.cachedWordClasses.put(wordClass.getId(), wordClass)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Language getLanguageBzId(Integer id) {
        return this.cachedLanguages.get(id);
    }

    public WordClass getWordClassBzId(Integer id) {
        return this.cachedWordClasses.get(id);
    }
}
