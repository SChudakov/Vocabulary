package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.WordDaoImpl;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Word;
import com.sschudakov.service.interf.WordSrv;

import java.sql.SQLException;
import java.util.Collection;

public class WordSrvImpl implements WordSrv {

    private WordDao wordDao;
    private WMRDao wmrDao;
    private WCRDao wcrDao;


    public WordSrvImpl() {
        this.wordDao = new WordDaoImpl();
    }

    @Override
    public void create(String word, String wordClass,
                       Collection<String> collectionsNames, Collection<String> meanings) throws SQLException {
        //TODO: implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer wordId) throws SQLException {
        this.wordDao.remove(wordId);
    }

    @Override
    public Word update(Word word) throws SQLException {
        //TODO: implement
        throw new UnsupportedOperationException();
    }
}
