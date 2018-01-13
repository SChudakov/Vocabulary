package com.sschudakov.factory.dao;

import com.sschudakov.dao.impl.WordDaoImpl;
import com.sschudakov.dao.interf.WordDao;

public class DaoFactory {

    public WordDao wordDao(){
        return new WordDaoImpl();
    }

}
