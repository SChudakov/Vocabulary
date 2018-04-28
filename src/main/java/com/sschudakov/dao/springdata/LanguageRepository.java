package com.sschudakov.dao.springdata;

import com.sschudakov.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> getByName(String name);
}
