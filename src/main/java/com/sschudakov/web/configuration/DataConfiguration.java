package com.sschudakov.web.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(this.dataSource);
        jdbcDao.setUsersByUsernameQuery(
                "SELECT users.name AS username, users.password AS password, 1 AS enabled " +
                        "FROM users " +
                        "WHERE users.name=?"
        );
        jdbcDao.setAuthoritiesByUsernameQuery(
                "SELECT users.name AS username, roles.name " +
                        "FROM users JOIN user_role ON users.user_id=user_role.user JOIN roles ON roles.role_id=user_role.role " +
                        "WHERE users.name=?"
        );
        return jdbcDao;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Logger getLogger() {
        return LogManager.getLogger("com.sschudakov.dao");
    }


}
