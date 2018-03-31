package com.sschudakov.dao.repository;

import com.sschudakov.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

}
