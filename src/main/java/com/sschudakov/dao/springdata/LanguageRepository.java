package com.sschudakov.dao.springdata;

import com.sschudakov.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> getByName(String name);
}
