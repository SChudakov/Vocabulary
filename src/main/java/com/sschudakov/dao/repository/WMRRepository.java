package com.sschudakov.dao.repository;

import com.sschudakov.entity.WordMeaningRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WMRRepository extends JpaRepository<WordMeaningRelationship, Integer> {
}
