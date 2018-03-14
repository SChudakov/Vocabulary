package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;
import com.sschudakov.entity.WordMeaningRelationship_;
import com.sschudakov.entity.Word_;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Integer> findWordMeaningsIds(Word word, Language meaningsLanguage) {
        CriteriaQuery<WordMeaningRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordMeaningRelationship.class);
        Root<WordMeaningRelationship> root = criteriaQuery.from(WordMeaningRelationship.class);

        Path<Integer> wmrIdPath = root.get(WordMeaningRelationship_.id);
        Path<Word> wmrWordPath = root.get(WordMeaningRelationship_.word);
        Path<Word> wmrMeaningPath = root.get(WordMeaningRelationship_.meaning);

        Join<WordMeaningRelationship, Word> wmrAndMeaningJoin = root.join(WordMeaningRelationship_.meaning);

        Path<Language> wordLanguagePath = wmrAndMeaningJoin.get(Word_.language);

        criteriaQuery.select(criteriaBuilder.construct(
                WordMeaningRelationship.class,
                wmrIdPath,
                wmrWordPath,
                wmrMeaningPath
        )).where(criteriaBuilder.equal(wordLanguagePath, meaningsLanguage));

        return entityManager.createQuery(criteriaQuery).getResultList()
                .stream().map(a -> a.getMeaning().getId()).collect(Collectors.toList());
    }

    @Override
    public Map<Word, List<Word>> findWordsMeanings(List<Word> words, Language meaningsLanguage) throws SQLException {
        throw new UnsupportedOperationException();
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
