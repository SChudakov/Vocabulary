package com.sschudakov.dao.repository;

import com.sschudakov.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
}
