package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordClass_;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

public class WordClassDaoHbnImpl implements WordClassDao {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;


    //-------------- constructor  ---------------//

    public WordClassDaoHbnImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    //-------------- save  ---------------//

    @Override
    public void save(WordClass wordClass) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(wordClass);
        this.entityManager.getTransaction().commit();
    }


    //-------------- find ---------------//

    @Override
    public WordClass findById(Integer id) {
        CriteriaQuery<WordClass> criteriaQuery = this.criteriaBuilder.createQuery(WordClass.class);
        Root<WordClass> root = criteriaQuery.from(WordClass.class);

        Path<Integer> wordClassIdPath = root.get(WordClass_.id);
        Path<String> wordClassNamePath = root.get(WordClass_.wordClassName);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordClass.class,
                        wordClassIdPath,
                        wordClassNamePath
                )
        ).where(criteriaBuilder.equal(root.get(WordClass_.id), id));

        WordClass result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public WordClass findByName(String name) {
        CriteriaQuery<WordClass> criteriaQuery = this.criteriaBuilder.createQuery(WordClass.class);
        Root<WordClass> root = criteriaQuery.from(WordClass.class);

        Path<Integer> wordClassIdPath = root.get(WordClass_.id);
        Path<String> wordClassNamePath = root.get(WordClass_.wordClassName);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordClass.class,
                        wordClassIdPath,
                        wordClassNamePath
                )
        ).where(criteriaBuilder.equal(root.get(WordClass_.wordClassName), name));

        WordClass result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public List<WordClass> findAll() {
        CriteriaQuery<WordClass> criteriaQuery = this.criteriaBuilder.createQuery(WordClass.class);
        Root<WordClass> root = criteriaQuery.from(WordClass.class);

        Path<Integer> wordClassIdPath = root.get(WordClass_.id);
        Path<String> wordClassNamePath = root.get(WordClass_.wordClassName);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        WordClass.class,
                        wordClassIdPath,
                        wordClassNamePath
                )
        );
        List<WordClass> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }
}
