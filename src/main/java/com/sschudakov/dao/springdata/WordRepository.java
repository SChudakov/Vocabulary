package com.sschudakov.dao.springdata;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordMeaningRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    Optional<Word> getByValueAndLanguage(String value, Language language);

    @Query(value = "SELECT wcr.word FROM WordCollectionRelationship wcr JOIN wcr.word JOIN wcr.wordCollection WHERE wcr.wordCollection = :collection")
    List<Word> getByCollection(@Param(value = "collection") WordCollection collection);

    @Query(value = "SELECT word FROM Word word WHERE word.language = :language")
    List<Word> getByLanguage(@Param(value = "language") Language language);

    @Query(value = "SELECT meaning FROM Word meaning WHERE " +
            " meaning IN (" +
            "SELECT wmr.meaning FROM WordMeaningRelationship wmr " +
            "WHERE wmr.word = :word"+
            ") AND meaning.language = :language")
    List<Word> getMeaningsByWordAndLanguage(
            @Param(value = "word") Word word,
            @Param(value = "language") Language language);

    @Query(value = "SELECT wmr.meaning FROM WordMeaningRelationship wmr WHERE wmr.word = :word")
    List<Word> getMeaningsByWord(@Param(value = "word") Word word);


    //todo: move to WMRRepository
    @Query(value = "SELECT wmr FROM WordMeaningRelationship wmr WHERE wmr.word.language = :language")
    List<WordMeaningRelationship> getAllMeaningsByLanguage(@Param(value = "language") Language language);
}
