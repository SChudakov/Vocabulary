package com.sschudakov.dao.repository;

import com.sschudakov.entity.WordClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordClassRepository extends JpaRepository<WordClass, Integer> {
}
