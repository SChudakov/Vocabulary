package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.WordDaoImpl;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.service.interf.WordSrv;

public class WordSrvImpl implements WordSrv {

    private WordDao wordDao;

    private WMRDao wmrDao;

    private WCRDao wcrDao;


    public WordSrvImpl() {
        this.wordDao = new WordDaoImpl();
    }
}
