package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Language_;
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

public class LanguageDaoHbnImpl implements LanguageDao {
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;


    //-------------- constructor  ---------------//

    public LanguageDaoHbnImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    //-------------- save  ---------------//

    @Override
    public void save(Language language) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(language);
        this.entityManager.getTransaction().commit();
    }


    //-------------- find  ---------------//

    @Override
    public Language findById(Integer id) {
        CriteriaQuery<Language> criteriaQuery = this.criteriaBuilder.createQuery(Language.class);
        Root<Language> root = criteriaQuery.from(Language.class);

        Path<Integer> languageIdPath = root.get(Language_.id);
        Path<String> languageNamePath = root.get(Language_.name);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        Language.class,
                        languageIdPath,
                        languageNamePath
                )
        ).where(criteriaBuilder.equal(root.get(Language_.id), id));

        Language result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public Language findByName(String name) {
        CriteriaQuery<Language> criteriaQuery = this.criteriaBuilder.createQuery(Language.class);
        Root<Language> root = criteriaQuery.from(Language.class);

        Path<Integer> languageIdPath = root.get(Language_.id);
        Path<String> languageNamePath = root.get(Language_.name);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        Language.class,
                        languageIdPath,
                        languageNamePath
                )
        ).where(criteriaBuilder.equal(root.get(Language_.name), name));

        Language result = null;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public List<Language> findAll() {
        CriteriaQuery<Language> criteriaQuery = this.criteriaBuilder.createQuery(Language.class);
        Root<Language> root = criteriaQuery.from(Language.class);

        Path<Integer> languageIdPath = root.get(Language_.id);
        Path<String> languageNamePath = root.get(Language_.name);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        Language.class,
                        languageIdPath,
                        languageNamePath
                )
        );

        List<Language> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }
}
