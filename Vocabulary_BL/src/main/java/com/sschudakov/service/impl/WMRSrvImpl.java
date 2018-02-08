package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.WMRDaoImpl;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;
import com.sschudakov.service.interf.WMRSrv;

import java.sql.SQLException;

public class WMRSrvImpl implements WMRSrv {

    private WMRDao wmrDao;

    public WMRSrvImpl() {
        this.wmrDao = new WMRDaoImpl();
    }

    @Override
    public void create(Word word, Word meaning) throws SQLException {
        this.wmrDao.save(new WordMeaningRelationship(word, meaning));
    }

    @Override
    public void delete(Integer wmrId) throws SQLException {
        this.wmrDao.remove(wmrId);
    }

    @Override
    public WordMeaningRelationship update(WordMeaningRelationship wmr) throws SQLException {
        return this.wmrDao.update(wmr);
    }
}
