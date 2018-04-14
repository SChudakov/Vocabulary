package com.sschudakov.service;

import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.entity.WordClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class WordClassSrv {


    //-------------- dao object  ---------------//
    @Autowired
    private WordClassDao wordClassDao;


    //-------------- constructor  ---------------//

    public WordClassSrv(WordClassDao wordClassDao) {
        this.wordClassDao = wordClassDao;
    }


    //-------------- create  ---------------//

    public void create(String wordClassName) throws SQLException {
        if (wordClassExists(wordClassName)) {
            throw new IllegalArgumentException("There is already word class with name " + wordClassName);
        } else {
            this.wordClassDao.save(new WordClass(wordClassName));
        }
    }


    //-------------- find ---------------//

    public WordClass findById(Integer id) throws SQLException {
        return this.wordClassDao.findById(id);
    }


    public WordClass findByName(String name) throws SQLException {
        return this.wordClassDao.findByName(name);
    }


    public List<String> findAll() throws SQLException {
        return this.wordClassDao.findAll().stream().map(WordClass::getWordClassName).collect(Collectors.toList());
    }

    public List<WordClass> findAllObjects() throws SQLException {
        return this.wordClassDao.findAll();
    }


    //-------------- exist query ---------------//

    private boolean wordClassExists(String wordClassName) throws SQLException {
        return this.wordClassDao.findByName(wordClassName) != null;
    }

}
