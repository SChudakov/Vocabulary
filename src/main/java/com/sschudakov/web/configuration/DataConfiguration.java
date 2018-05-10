package com.sschudakov.web.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:app.properties")
public class DataConfiguration {

    private final DataSource dataSource;

    @Autowired
    public DataConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*----------- stuff --------------*/

    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(this.dataSource);
        jdbcDao.setUsersByUsernameQuery(
                "SELECT name AS username, password, 1 AS enabled " +
                        "FROM users " +
                        "WHERE name=?"
        );
        jdbcDao.setAuthoritiesByUsernameQuery(
                "SELECT users.name AS username, roles.name AS role" +
                        "FROM users JOIN user_role ON users.user_id=user_role.user JOIN roles ON roles.role_id=user_role.role" +
                        "WHERE users.name=?"
        );
        return jdbcDao;
    }

    @Bean
    @Qualifier(value = "jpaTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Logger getLogger() {
        return LogManager.getLogger("com.sschudakov.dao");
    }


}
