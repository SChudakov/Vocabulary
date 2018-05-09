package com.sschudakov.desktop.factory;

import com.sschudakov.dao.impl.hibernate.LanguageDaoHbnImpl;
import com.sschudakov.dao.impl.hibernate.WCRDaoHbnImpl;
import com.sschudakov.dao.impl.hibernate.WMRDaoHbnImpl;
import com.sschudakov.dao.impl.hibernate.WordClassDaoHbnImpl;
import com.sschudakov.dao.impl.hibernate.WordCollectionDaoHbnImpl;
import com.sschudakov.dao.impl.hibernate.WordDaoHbnImpl;
import com.sschudakov.dao.impl.jdbc.LanguageDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WCRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WMRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordClassDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordCollectionDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordDaoJdbcImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.dao.interf.WordDao;

public class DaoFactory {
    private enum DaoType {JDBC(), HIBERNATE()}

    private static DaoType daoType = DaoType.JDBC;

    public static LanguageDao createLanguageDao() {
        if (daoType == DaoType.JDBC) {
            return new LanguageDaoJdbcImpl();
        } else {
            return new LanguageDaoHbnImpl();
        }
    }

    public static WordClassDao createWordClassDao() {
        if (daoType == DaoType.JDBC) {
            return new WordClassDaoJdbcImpl();
        } else {
            return new WordClassDaoHbnImpl();
        }
    }

    public static WordCollectionDao createWordCollectionDao() {
        if (daoType == DaoType.JDBC) {
            return new WordCollectionDaoJdbcImpl();
        } else {
            return new WordCollectionDaoHbnImpl();
        }
    }

    public static WordDao createWordDao() {
        if (daoType == DaoType.JDBC) {
            return new WordDaoJdbcImpl(
                    createLanguageDao(),
                    createWordClassDao()
            );
        } else {
            return new WordDaoHbnImpl();
        }
    }

    public static WMRDao createWMRDao() {
        if (daoType == DaoType.JDBC) {
            return new WMRDaoJdbcImpl(
                    createWordDao()
            );
        } else {
            return new WMRDaoHbnImpl();
        }
    }

    public static WCRDao createWCRDao() {
        if (daoType == DaoType.JDBC) {
            return new WCRDaoJdbcImpl(
                    createWordDao(),
                    createWordCollectionDao()
            );
        } else {
            return new WCRDaoHbnImpl();
        }
    }
}
