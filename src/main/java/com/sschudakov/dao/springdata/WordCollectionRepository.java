package com.sschudakov.dao.springdata;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordCollectionRepository extends JpaRepository<WordCollection, Integer> {
    Optional<WordCollection> getByCollectionName(String collectionName);

    @Query(value =
            "SELECT wordCollection FROM WordCollection wordCollection " +
                    "WHERE wordCollection IN (" +
                    "SELECT wcr.wordCollection FROM  WordCollectionRelationship wcr " +
                    "WHERE wcr.word = :word" +
                    ")")
    List<WordCollection> getCollectionsByWord(@Param(value = "word") Word word);
}
