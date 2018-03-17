package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.entity.WordCollectionRelationship_;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class WCRDaoHbnImpl implements WCRDao {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public WCRDaoHbnImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }

    @Override
    public void save(WordCollectionRelationship wordCollectionRelationship) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(wordCollectionRelationship);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(wordCollectionRelationship);
        this.entityManager.getTransaction().commit();
        return wordCollectionRelationship;
    }

    @Override
    public WordCollectionRelationship findById(Integer id) {
        CriteriaQuery<WordCollectionRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordCollectionRelationship.class);
        Root<WordCollectionRelationship> root = criteriaQuery.from(WordCollectionRelationship.class);

        Path<Integer> wcrIdPath = root.get(WordCollectionRelationship_.id);
        Path<Word> wcrWordPath = root.get(WordCollectionRelationship_.word);
        Path<WordCollection> wcrCollectionPath = root.get(WordCollectionRelationship_.wordCollection);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordCollectionRelationship.class,
                        wcrIdPath,
                        wcrWordPath,
                        wcrCollectionPath
                )
        ).where(criteriaBuilder.equal(root.get(WordCollectionRelationship_.id), id));

        WordCollectionRelationship result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public Collection<WordCollectionRelationship> findRelationshipsByWord(Word word) {
        CriteriaQuery<WordCollectionRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordCollectionRelationship.class);
        Root<WordCollectionRelationship> root = criteriaQuery.from(WordCollectionRelationship.class);

        Path<Integer> wcrIdPath = root.get(WordCollectionRelationship_.id);
        Path<Word> wcrWordPath = root.get(WordCollectionRelationship_.word);
        Path<WordCollection> wcrCollectionPath = root.get(WordCollectionRelationship_.wordCollection);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordCollectionRelationship.class,
                        wcrIdPath,
                        wcrWordPath,
                        wcrCollectionPath
                )
        ).where(criteriaBuilder.equal(root.get(WordCollectionRelationship_.word), word));

        List<WordCollectionRelationship> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    @Override
    public Collection<WordCollectionRelationship> findWordsByCollection(WordCollection wordCollection) {
        CriteriaQuery<WordCollectionRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordCollectionRelationship.class);
        Root<WordCollectionRelationship> root = criteriaQuery.from(WordCollectionRelationship.class);

        Path<Integer> wcrIdPath = root.get(WordCollectionRelationship_.id);
        Path<Word> wcrWordPath = root.get(WordCollectionRelationship_.word);
        Path<WordCollection> wcrCollectionPath = root.get(WordCollectionRelationship_.wordCollection);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordCollectionRelationship.class,
                        wcrIdPath,
                        wcrWordPath,
                        wcrCollectionPath
                )
        ).where(criteriaBuilder.equal(root.get(WordCollectionRelationship_.wordCollection), wcrCollectionPath));

        List<WordCollectionRelationship> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    @Override
    public WordCollectionRelationship findByWordAndCollection(Word word, WordCollection collection) {
        CriteriaQuery<WordCollectionRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordCollectionRelationship.class);
        Root<WordCollectionRelationship> root = criteriaQuery.from(WordCollectionRelationship.class);

        Path<Integer> wcrIdPath = root.get(WordCollectionRelationship_.id);
        Path<Word> wcrWordPath = root.get(WordCollectionRelationship_.word);
        Path<WordCollection> wcrCollectionPath = root.get(WordCollectionRelationship_.wordCollection);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get(WordCollectionRelationship_.word), word),
                criteriaBuilder.equal(root.get(WordCollectionRelationship_.wordCollection), collection)
        );

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordCollectionRelationship.class,
                        wcrIdPath,
                        wcrWordPath,
                        wcrCollectionPath
                )
        ).where(predicate);

        WordCollectionRelationship result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public List<WordCollectionRelationship> findAll() {
        this.entityManager.getTransaction().begin();

        CriteriaQuery<WordCollectionRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordCollectionRelationship.class);
        Root<WordCollectionRelationship> root = criteriaQuery.from(WordCollectionRelationship.class);

        Path<Integer> wcrIdPath = root.get(WordCollectionRelationship_.id);
        Path<Word> wcrWordPath = root.get(WordCollectionRelationship_.word);
        Path<WordCollection> wcrCollectionPath = root.get(WordCollectionRelationship_.wordCollection);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordCollectionRelationship.class,
                        wcrIdPath,
                        wcrWordPath,
                        wcrCollectionPath
                )
        );

        List<WordCollectionRelationship> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    @Override
    public void remove(Integer wordCollectionRelationshipID) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(this.entityManager.getReference(
                WordCollectionRelationship.class,
                wordCollectionRelationshipID)
        );
        this.entityManager.getTransaction().commit();
    }

}
