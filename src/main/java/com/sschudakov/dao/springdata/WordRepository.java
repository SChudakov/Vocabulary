package com.sschudakov.dao.springdata;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Integer> {
    Optional<Word> getByValueAndLanguage(String value, Language language);

    List<Word> getByLanguage(Language language);

    /*@Query(value = "")
    List<Word> findByCollection()*/
}
