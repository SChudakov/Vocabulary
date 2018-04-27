package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.Word_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;
@Repository
public class WordDaoHbnImpl implements WordDao {
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;


    //-------------- constructor  ---------------//

    public WordDaoHbnImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    //-------------- save  ---------------//

    @Override
    public void save(Word word) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(word);
        this.entityManager.getTransaction().commit();
    }


    //-------------- update ---------------//

    @Override
    public Word update(Word word) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(word);
        this.entityManager.getTransaction().commit();
        return word;
    }


    //-------------- find ---------------//

    @Override
    public Word findById(Integer id) {
        CriteriaQuery<Word> criteriaQuery = this.criteriaBuilder.createQuery(Word.class);
        Root<Word> root = criteriaQuery.from(Word.class);

        Path<Integer> idPath = root.get(Word_.id);
        Path<String> wordValuePath = root.get(Word_.value);
        Path<WordClass> wordClassPath = root.get(Word_.wordClass);
        Path<Language> wordLanguagePath = root.get(Word_.language);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        Word.class,
                        idPath,
                        wordValuePath,
                        wordClassPath,
                        wordLanguagePath
                )
        ).where(criteriaBuilder.equal(root.get(Word_.id), id));

        Word result;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public Word findByValueAndLanguage(String value, Language language) {
        CriteriaQuery<Word> criteriaQuery = this.criteriaBuilder.createQuery(Word.class);
        Root<Word> root = criteriaQuery.from(Word.class);

        Path<Integer> idPath = root.get(Word_.id);
        Path<String> wordValuePath = root.get(Word_.value);
        Path<WordClass> wordClassPath = root.get(Word_.wordClass);
        Path<Language> wordLanguagePath = root.get(Word_.language);

        Predicate predicate = this.criteriaBuilder.and(
                criteriaBuilder.equal(root.get(Word_.value), value),
                criteriaBuilder.equal(root.get(Word_.language), language)
        );

        criteriaQuery.select(
                criteriaBuilder.construct(
                        Word.class,
                        idPath,
                        wordValuePath,
                        wordClassPath,
                        wordLanguagePath
                )
        ).where(predicate);

        Word result;
        try {
            result = this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public List<Word> findByLanguage(Language language) {
        CriteriaQuery<Word> criteriaQuery = this.criteriaBuilder.createQuery(Word.class);
        Root<Word> root = criteriaQuery.from(Word.class);

        Path<Integer> idPath = root.get(Word_.id);
        Path<String> wordValuePath = root.get(Word_.value);
        Path<WordClass> wordClassPath = root.get(Word_.wordClass);
        Path<Language> wordLanguagePath = root.get(Word_.language);


        criteriaQuery.select(
                criteriaBuilder.construct(
                        Word.class,
                        idPath,
                        wordValuePath,
                        wordClassPath,
                        wordLanguagePath
                )
        ).where(this.criteriaBuilder.equal(root.get(Word_.language), language));

        List<Word> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    @Override
    public Collection<Word> findAll() {


        CriteriaQuery<Word> criteriaQuery = this.criteriaBuilder.createQuery(Word.class);
        Root<Word> root = criteriaQuery.from(Word.class);

        Path<Integer> idPath = root.get(Word_.id);
        Path<String> wordValuePath = root.get(Word_.value);
        Path<WordClass> wordClassPath = root.get(Word_.wordClass);
        Path<Language> wordLanguagePath = root.get(Word_.language);

        criteriaQuery.select(
                criteriaBuilder.construct(
                        Word.class,
                        idPath,
                        wordValuePath,
                        wordClassPath,
                        wordLanguagePath
                )
        );

        List<Word> result = this.entityManager.createQuery(criteriaQuery).getResultList();


        return result;
    }


    //-------------- remove ---------------//

    @Override
    public void remove(Integer id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(this.entityManager.getReference(
                Word.class,
                id)
        );
        this.entityManager.getTransaction().commit();
    }
}
