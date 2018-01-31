package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;

public interface WMRSrv {

    public void create(Word word, Word meaning) throws SQLException;

    public void delete(Integer wmrId) throws SQLException;

    WordMeaningRelationship update(WordMeaningRelationship wmr) throws SQLException;
}
