package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordClass_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class WordClassDaoHbnImpl implements WordClassDao {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public WordClassDaoHbnImpl() {
        this.entityManagerFactory = Persistence.
                createEntityManagerFactory("org.hibernate.tutorial.jpa");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(WordClass wordClass) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WordClass findById(Integer id) throws SQLException {
        /*CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<WordClass> criteriaQuery = criteriaBuilder.createQuery(WordClass.class);
        Root<WordClass> root = criteriaQuery.from(WordClass.class);

        Path<Integer> wordClassIdPath = root.get(WordClass_.wordClassID);
        Path<String> wordClassNamePath = root.get(WordClass_.wordClassName);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordClass.class,
                        wordClassIdPath,
                        wordClassNamePath
                )
        ).where(criteriaBuilder.equal(root.get(WordClass_.wordClassID), id));
        return this.entityManager.createQuery(criteriaQuery).getSingleResult();*/
        return this.entityManager.find(WordClass.class, 2);
    }

    @Override
    public WordClass findByName(String name) throws SQLException {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<WordClass> criteriaQuery = criteriaBuilder.createQuery(WordClass.class);
        Root<WordClass> root = criteriaQuery.from(WordClass.class);

        Path<Integer> wordClassIdPath = root.get(WordClass_.wordClassID);
        Path<String> wordClassNamePath = root.get(WordClass_.wordClassName);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordClass.class,
                        wordClassIdPath,
                        wordClassNamePath
                )
        ).where(criteriaBuilder.equal(root.get(WordClass_.wordClassName), name));
        return this.entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<WordClass> findAll() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void openEntityManager() {
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public void openEntityManagerFactory() {
        this.entityManagerFactory = Persistence.
                createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    public void closeEntityManager() {
        this.entityManager.close();
    }

    public void closeEntityManagerFactory() {
        this.entityManagerFactory.close();
    }


}
