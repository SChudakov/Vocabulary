package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollection_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

public class WordCollectionDaoHbnImpl implements WordCollectionDao {
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;


    //-------------- constructor ---------------//

    public WordCollectionDaoHbnImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    //-------------- save  ---------------//

    @Override
    public void save(WordCollection wordCollection) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(wordCollection);
        this.entityManager.getTransaction().commit();
    }


    //-------------- update ---------------//

    @Override
    public WordCollection update(WordCollection wordCollection) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(wordCollection);
        this.entityManager.getTransaction().commit();
        return wordCollection;
    }


    //-------------- find ---------------//

    @Override
    public WordCollection findById(Integer id) {
        CriteriaQuery<WordCollection> criteriaQuery = this.criteriaBuilder.createQuery(WordCollection.class);
        Root<WordCollection> root = criteriaQuery.from(WordCollection.class);

        Path<Integer> wordCollectionIdPath = root.get(WordCollection_.id);
        Path<String> wordCollectionNamePath = root.get(WordCollection_.collectionName);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordCollection.class,
                        wordCollectionIdPath,
                        wordCollectionNamePath
                )
        ).where(criteriaBuilder.equal(root.get(WordCollection_.id), id));

        WordCollection result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public WordCollection findByName(String name) {
        CriteriaQuery<WordCollection> criteriaQuery = this.criteriaBuilder.createQuery(WordCollection.class);
        Root<WordCollection> root = criteriaQuery.from(WordCollection.class);

        Path<Integer> wordCollectionIdPath = root.get(WordCollection_.id);
        Path<String> wordCollectionNamePath = root.get(WordCollection_.collectionName);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordCollection.class,
                        wordCollectionIdPath,
                        wordCollectionNamePath
                )
        ).where(criteriaBuilder.equal(root.get(WordCollection_.collectionName), name));

        WordCollection result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public List<WordCollection> findAll() {
        CriteriaQuery<WordCollection> criteriaQuery = this.criteriaBuilder.createQuery(WordCollection.class);
        Root<WordCollection> root = criteriaQuery.from(WordCollection.class);

        Path<Integer> wordCollectionIdPath = root.get(WordCollection_.id);
        Path<String> wordCollectionNamePath = root.get(WordCollection_.collectionName);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordCollection.class,
                        wordCollectionIdPath,
                        wordCollectionNamePath
                )
        );

        List<WordCollection> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }


    //-------------- remove ---------------//

    @Override
    public void remove(Integer wordCollectionID) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(this.entityManager.getReference(
                WordCollection.class,
                wordCollectionID)
        );
        this.entityManager.getTransaction().commit();
    }
}
