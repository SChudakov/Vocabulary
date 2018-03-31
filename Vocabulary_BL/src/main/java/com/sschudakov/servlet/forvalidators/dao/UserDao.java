package com.sschudakov.servlet.forvalidators.dao;

import com.sschudakov.servlet.forvalidators.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 * on  11.07.2017 for springJConfInterHiber project.
 */
public class UserDao {

    /*@Autowired
    Logger logger;*/

    @PersistenceContext
    private EntityManager entityManager;


    public List<User> getAll() {
//        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("FROM com.sschudakov.servlet.forconfig.entity.User", User.class).getResultList();
//        entityManager.getTransaction().commit();
        return users;
    }

    @Transactional
    public User getUserByLoginAndPassword(String login, String password) {

//        entityManager.getTransaction().begin();
        List<User> us = entityManager.createQuery("SELECT u FROM com.sschudakov.servlet.forconfig.entity.User u "
                + "WHERE u.login = :login AND u.password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList();
        if (us.isEmpty()) {
//            entityManager.getTransaction().commit();
            /*logger.warn("User not found");*/
            return null;
        }
//        entityManager.getTransaction().commit();
        /*logger.info("User found! ");*/
        return us.get(0);
    }

    public void create(User u) {
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
//        entityManager.getTransaction().commit();
    }

}
