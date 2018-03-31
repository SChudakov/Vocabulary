package com.sschudakov.dao.repository;

import com.sschudakov.entity.WordCollectionRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WCRRepositroy extends JpaRepository<WordCollectionRelationship, Integer> {
}
