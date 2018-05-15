package com.sschudakov.desktop.factory;

import com.sschudakov.desktop.swing.request.UserRequestManager;
import com.sschudakov.service.dao.LanguageSrv;
import com.sschudakov.service.dao.WordClassSrv;
import com.sschudakov.service.dao.WordCollectionSrv;
import com.sschudakov.service.dao.WordSrv;

public class UserRequestManagerFactory {

    public static UserRequestManager createRequestManager(){
        return new UserRequestManager(
                new LanguageSrv(DaoFactory.createLanguageDao()),
                new WordClassSrv(DaoFactory.createWordClassDao()),
                new WordCollectionSrv(
                        DaoFactory.createWordCollectionDao(),
                        DaoFactory.createWCRDao()
                ),
                new WordSrv(
                        DaoFactory.createWordDao(),
                        DaoFactory.createWMRDao(),
                        DaoFactory.createWCRDao()
                )
        );
    }
}
