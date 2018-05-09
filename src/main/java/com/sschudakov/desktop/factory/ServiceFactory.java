package com.sschudakov.desktop.factory;

import com.sschudakov.service.LanguageSrv;
import com.sschudakov.service.WordClassSrv;
import com.sschudakov.service.WordCollectionSrv;
import com.sschudakov.service.WordSrv;

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
