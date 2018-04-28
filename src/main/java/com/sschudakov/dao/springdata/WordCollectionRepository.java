package com.sschudakov.dao.springdata;

import com.sschudakov.entity.WordCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordCollectionRepository extends JpaRepository<WordCollection, Integer> {
}
