package com.sschudakov.dao.springdata;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Integer> {
    Optional<Word> getByValueAndLanguage(String value, Language language);

    @Query(value = "SELECT wcr.word FROM WordCollectionRelationship wcr JOIN wcr.word JOIN wcr.wordCollection WHERE wcr.wordCollection = :collection")
    List<Word> getByCollection(@Param(value = "collection") WordCollection collection);

    @Query(value = "SELECT word FROM Word word WHERE word.language = :language")
    List<Word> getByLanguage(@Param(value = "language") Language language);
}
