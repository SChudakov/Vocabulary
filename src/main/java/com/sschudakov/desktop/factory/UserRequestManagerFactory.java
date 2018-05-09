package com.sschudakov.desktop.factory;

import com.sschudakov.desktop.swing.request.UserRequestManager;
import com.sschudakov.service.LanguageSrv;
import com.sschudakov.service.WordClassSrv;
import com.sschudakov.service.WordCollectionSrv;
import com.sschudakov.service.WordSrv;

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
