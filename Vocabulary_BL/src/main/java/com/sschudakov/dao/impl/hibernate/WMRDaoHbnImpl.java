package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WMRDaoHbnImpl implements WMRDao {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;


    //-------------- constructor ---------------//

    public WMRDaoHbnImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    //-------------- save  ---------------//

    @Override
    public void save(WordMeaningRelationship wordMeaningRelationship) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(wordMeaningRelationship);
        this.entityManager.getTransaction().commit();
    }


    //-------------- update ---------------//

    @Override
    public WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(wordMeaningRelationship);
        this.entityManager.getTransaction().commit();
        return wordMeaningRelationship;
    }


    //-------------- find ---------------//

    @Override
    public WordMeaningRelationship findById(Integer id) {
        CriteriaQuery<WordMeaningRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordMeaningRelationship.class);
        Root<WordMeaningRelationship> root = criteriaQuery.from(WordMeaningRelationship.class);

        Path<Integer> wmrIdPath = root.get(WordMeaningRelationship_.id);
        Path<Word> wmrWordPath = root.get(WordMeaningRelationship_.word);
        Path<Word> wmrMeaningPath = root.get(WordMeaningRelationship_.meaning);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordMeaningRelationship.class,
                        wmrIdPath,
                        wmrWordPath,
                        wmrMeaningPath
                )
        ).where(criteriaBuilder.equal(root.get(WordMeaningRelationship_.id), id));

        WordMeaningRelationship result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public List<WordMeaningRelationship> findByWord(Word word) {
        CriteriaQuery<WordMeaningRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordMeaningRelationship.class);
        Root<WordMeaningRelationship> root = criteriaQuery.from(WordMeaningRelationship.class);

        Path<Integer> wmrIdPath = root.get(WordMeaningRelationship_.id);
        Path<Word> wmrWordPath = root.get(WordMeaningRelationship_.word);
        Path<Word> wmrMeaningPath = root.get(WordMeaningRelationship_.meaning);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordMeaningRelationship.class,
                        wmrIdPath,
                        wmrWordPath,
                        wmrMeaningPath
                )
        ).where(criteriaBuilder.equal(root.get(WordMeaningRelationship_.word), word));

        List<WordMeaningRelationship> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    @Override
    public List<WordMeaningRelationship> findByMeaning(Word meaning) {
        CriteriaQuery<WordMeaningRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordMeaningRelationship.class);
        Root<WordMeaningRelationship> root = criteriaQuery.from(WordMeaningRelationship.class);

        Path<Integer> wmrIdPath = root.get(WordMeaningRelationship_.id);
        Path<Word> wmrWordPath = root.get(WordMeaningRelationship_.word);
        Path<Word> wmrMeaningPath = root.get(WordMeaningRelationship_.meaning);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordMeaningRelationship.class,
                        wmrIdPath,
                        wmrWordPath,
                        wmrMeaningPath
                )
        ).where(criteriaBuilder.equal(root.get(WordMeaningRelationship_.meaning), meaning));

        List<WordMeaningRelationship> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    @Override
    public WordMeaningRelationship findByWordAndMeaning(Word word, Word meaning) {
        CriteriaQuery<WordMeaningRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordMeaningRelationship.class);
        Root<WordMeaningRelationship> root = criteriaQuery.from(WordMeaningRelationship.class);


        Path<Integer> wmrIdPath = root.get(WordMeaningRelationship_.id);
        Path<Word> wmrWordPath = root.get(WordMeaningRelationship_.word);
        Path<Word> wmrMeaningPath = root.get(WordMeaningRelationship_.meaning);

        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(
                root.get(WordMeaningRelationship_.word), word),
                criteriaBuilder.equal(root.get(WordMeaningRelationship_.meaning), meaning)
        );

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordMeaningRelationship.class,
                        wmrIdPath,
                        wmrWordPath,
                        wmrMeaningPath
                )
        ).where(predicate);

        WordMeaningRelationship result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public List<Word> findWordMeanings(Word word, Language meaningsLanguage) {
        CriteriaQuery<Word> criteriaQuery = this.criteriaBuilder
                .createQuery(Word.class);
        Root<WordMeaningRelationship> root = criteriaQuery.from(WordMeaningRelationship.class);

        Path<Word> wmrWordPath = root.get(WordMeaningRelationship_.word);
        Path<Word> wmrMeaningPath = root.get(WordMeaningRelationship_.meaning);

        Join<WordMeaningRelationship, Word> wmrAndMeaningJoin = root.join(WordMeaningRelationship_.meaning);

        Path<Language> meaningsLanguagePath = wmrAndMeaningJoin.get(Word_.language);

        Predicate wordAndMeaningLanguage = criteriaBuilder.and(
                criteriaBuilder.equal(wmrWordPath, word),
                criteriaBuilder.equal(meaningsLanguagePath, meaningsLanguage)
        );

        criteriaQuery.select(wmrMeaningPath).where(wordAndMeaningLanguage);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Map<Word, List<Word>> findWordsMeanings(List<Word> words, Language meaningsLanguage) throws SQLException {

        Map<Word, List<Word>> result = new HashMap<>();

        CriteriaQuery<Word> criteriaQuery = this.criteriaBuilder
                .createQuery(Word.class);
        Root<WordMeaningRelationship> root = criteriaQuery.from(WordMeaningRelationship.class);

        Path<Word> wmrWordPath = root.get(WordMeaningRelationship_.word);
        Path<Word> wmrMeaningPath = root.get(WordMeaningRelationship_.meaning);

        Join<WordMeaningRelationship, Word> wmrAndMeaningJoin = root.join(WordMeaningRelationship_.meaning);

        Path<Language> meaningsLanguagePath = wmrAndMeaningJoin.get(Word_.language);

        for (Word word : words) {
            Predicate wordAndMeaningLanguage = criteriaBuilder.and(
                    criteriaBuilder.equal(wmrWordPath, word),
                    criteriaBuilder.equal(meaningsLanguagePath, meaningsLanguage)
            );

            criteriaQuery.select(wmrMeaningPath).where(wordAndMeaningLanguage);
            result.put(word, entityManager.createQuery(criteriaQuery).getResultList());
        }


        return result;
    }

    @Override
    public List<WordMeaningRelationship> findAll() {
        CriteriaQuery<WordMeaningRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordMeaningRelationship.class);
        Root<WordMeaningRelationship> root = criteriaQuery.from(WordMeaningRelationship.class);

        Path<Integer> wmrIdPath = root.get(WordMeaningRelationship_.id);
        Path<Word> wmrWordPath = root.get(WordMeaningRelationship_.word);
        Path<Word> wmrMeaningPath = root.get(WordMeaningRelationship_.meaning);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordMeaningRelationship.class,
                        wmrIdPath,
                        wmrWordPath,
                        wmrMeaningPath
                )
        );

        List<WordMeaningRelationship> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }


    //-------------- remove ---------------//

    @Override
    public void remove(Integer wordMeaningRelationshipID) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(this.entityManager.getReference(
                WordMeaningRelationship.class,
                wordMeaningRelationshipID)
        );
        this.entityManager.getTransaction().commit();
    }
}
