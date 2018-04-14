package com.sschudakov.dao.interf;

import com.sschudakov.entity.WordClass;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface WordClassDao {


    //-------------- save  ---------------//

    void save(WordClass wordClass) throws SQLException;


    //-------------- find ---------------//

    WordClass findById(Integer id) throws SQLException;

    WordClass findByName(String name) throws SQLException;

    List<WordClass> findAll() throws SQLException;

    /**
     * Data about word classes should never been changed or removed.
     * So update and remove operations are not present in this DAO.
     */
}
