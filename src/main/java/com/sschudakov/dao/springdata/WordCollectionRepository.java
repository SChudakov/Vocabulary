package com.sschudakov.dao.springdata;

import com.sschudakov.entity.WordCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordCollectionRepository extends JpaRepository<WordCollection, Integer> {
    Optional<WordCollection> getByCollectionName(String collectionName);
}
