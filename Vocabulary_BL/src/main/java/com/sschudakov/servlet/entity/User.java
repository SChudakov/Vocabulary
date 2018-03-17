package com.sschudakov.servlet.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  23.04.2017 for thirdSpringApp project.
 */
@Component
@Entity(name = "springUsers")
public class User {
    @Id
    @GeneratedValue
    Long id;

    private String name;
    private String login;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
