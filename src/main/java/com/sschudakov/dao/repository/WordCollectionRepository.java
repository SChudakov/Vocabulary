package com.sschudakov.dao.repository;

import com.sschudakov.entity.WordCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordCollectionRepository extends JpaRepository<WordCollection, Integer> {
}
