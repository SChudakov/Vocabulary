package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.WCRDaoImpl;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.service.interf.WCRSrv;

import java.sql.SQLException;

public class WCRSrvImpl implements WCRSrv {

    private WCRDao wcrDao;

    public WCRSrvImpl() {
        this.wcrDao = new WCRDaoImpl();
    }

    @Override
    public void create(Word word, WordCollection wordCollection) throws SQLException {
        this.wcrDao.save(new WordCollectionRelationship(word, wordCollection));
    }

    @Override
    public void delete(Integer wcrId) throws SQLException {
         this.wcrDao.remove(wcrId);
    }

    @Override
    public WordCollectionRelationship update(WordCollectionRelationship wcr) throws SQLException {
        return this.wcrDao.update(wcr);
    }
}
