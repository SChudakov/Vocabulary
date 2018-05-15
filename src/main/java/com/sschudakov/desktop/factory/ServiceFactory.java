package com.sschudakov.desktop.factory;

import com.sschudakov.service.dao.LanguageSrv;
import com.sschudakov.service.dao.WordClassSrv;
import com.sschudakov.service.dao.WordCollectionSrv;
import com.sschudakov.service.dao.WordSrv;

public class ServiceFactory {

    public static LanguageSrv createLanguageService() {
        return new LanguageSrv(DaoFactory.createLanguageDao());
    }

    public static WordClassSrv createWordClassService() {
        return new WordClassSrv(DaoFactory.createWordClassDao());
    }

    public static WordCollectionSrv createWordCollectionService() {
        return new WordCollectionSrv(
                DaoFactory.createWordCollectionDao(),
                DaoFactory.createWCRDao()
        );
    }

    public static WordSrv createWordService() {
        return new WordSrv(
                DaoFactory.createWordDao(),
                DaoFactory.createWMRDao(),
                DaoFactory.createWCRDao()
        );
    }
}
