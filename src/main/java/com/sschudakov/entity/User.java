package com.sschudakov.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    public static final String ID_CN = "user_id";
    public static final String NAME_CN = "name";
    public static final String PASSWORD_CN = "password";
    public static final String EMAIL_CN = "email";

    @Id
    @Column(name = ID_CN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = NAME_CN)
    private String name;

    @Column(name = PASSWORD_CN)
    private String password;

    @Column(name = EMAIL_CN)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user")},
            inverseJoinColumns = {@JoinColumn(name = "role")}
    )
    private List<Role> roles;

    @CreationTimestamp
    private LocalDateTime created;

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public User() {
        this.roles = new ArrayList<>();
    }

    public void addToRole(Role one) {
        this.roles.add(one);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.id)
                .append(this.name)
                .append(this.password)
                .append(this.email)
                .append(this.roles)
                .append(this.created)
                .build();
    }
}
