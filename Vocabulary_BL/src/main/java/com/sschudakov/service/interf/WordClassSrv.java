package com.sschudakov.service.interf;

import com.sschudakov.entity.WordClass;

import java.sql.SQLException;

public interface WordClassSrv {
    void create(String wordClassName) throws SQLException;


}
