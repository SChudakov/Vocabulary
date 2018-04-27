package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.entity.WordCollectionRelationship_;
import com.sschudakov.entity.Word_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class WCRDaoHbnImpl implements WCRDao {
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;


    //-------------- constructor ---------------//

    public WCRDaoHbnImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    //-------------- save  ---------------//

    @Override
    public void save(WordCollectionRelationship wordCollectionRelationship) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(wordCollectionRelationship);
        this.entityManager.getTransaction().commit();
    }


    //-------------- update ---------------//

    @Override
    public WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(wordCollectionRelationship);
        this.entityManager.getTransaction().commit();
        return wordCollectionRelationship;
    }


    //-------------- find ---------------//

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
    public List<WordCollectionRelationship> findRelationshipsByWord(Word word) {
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
    public List<WordCollectionRelationship> findByCollection(WordCollection wordCollection) {
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
    public List<WordCollectionRelationship> findByCollectionAndLanguage(WordCollection collection, Language language) {
        CriteriaQuery<WordCollectionRelationship> criteriaQuery = this.criteriaBuilder
                .createQuery(WordCollectionRelationship.class);
        Root<WordCollectionRelationship> root = criteriaQuery.from(WordCollectionRelationship.class);

        Path<Integer> wcrIdPath = root.get(WordCollectionRelationship_.id);
        Path<Word> wcrWordPath = root.get(WordCollectionRelationship_.word);
        Path<WordCollection> wcrCollectionPath = root.get(WordCollectionRelationship_.wordCollection);

        Join<WordCollectionRelationship, Word> wmrAndMeaningJoin = root.join(WordCollectionRelationship_.word);

        Path<Language> wordLanguagePath = wmrAndMeaningJoin.get(Word_.language);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get(WordCollectionRelationship_.wordCollection), collection),
                criteriaBuilder.equal(wordLanguagePath, language)
        );

        criteriaQuery.select(criteriaBuilder.construct(
                WordCollectionRelationship.class,
                wcrIdPath,
                wcrWordPath,
                wcrCollectionPath
        )).where(predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
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


    //-------------- remove ---------------//

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
