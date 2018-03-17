package com.sschudakov.servlet.dao;

import com.sschudakov.servlet.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 * on  23.04.2017 for thirdSpringApp project.
 */
@Component
public class UserDAO {
    private SessionFactory factory;
    private EntityManager em;

    protected UserDAO() {
        this.factory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        em = factory.createEntityManager();
    }

    public List<User> getAll() {
        em.getTransaction().begin();
        List<User> users = em.createQuery("FROM entity.User", User.class).getResultList();
        em.getTransaction().commit();
        return users;
    }

    public boolean isLoginExists(String login) {
        em.getTransaction().begin();
        List<User> us = em.createQuery("SELECT u FROM entity.User u "
                + "WHERE u.login = :login", User.class)
                .setParameter("login", login)
                .getResultList();
        if (us.isEmpty()) {
            em.getTransaction().commit();
            return false;
        }
        em.getTransaction().commit();
        return true;
    }

    public User getUserByLoginAndPassword(String login, String password) {
        em.getTransaction().begin();
        List<User> us = em.createQuery("SELECT u FROM entity.User u "
                + "WHERE u.login = :login AND u.password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList();
        if (us.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        em.getTransaction().commit();
        return us.get(0);
    }

    public void create(User u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

}
