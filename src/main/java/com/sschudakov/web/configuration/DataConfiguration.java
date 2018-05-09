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

    @Autowired
    DataSource dataSource;

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
                "SELECT uu.name AS username, ro.name AS role " +
                        "FROM users uu  JOIN user_role ur on uu.user_id=ur.user JOIN roles ro on ro.role_id=ur.role " +
                        "WHERE uu.name=?"
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
