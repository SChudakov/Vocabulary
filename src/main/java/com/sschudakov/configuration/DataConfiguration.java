package com.sschudakov.configuration;

import com.sschudakov.servlet.forvalidators.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 * on  11.07.2017 for springJConfInterHiber project.
 */
//@Configuration
//@EnableTransactionManagement
//@PropertySource("classpath:app.properties")
public class DataConfiguration {

    /*@Resource
    private Environment env;

    @Bean
    @Qualifier(value = "jpaTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty("db.entitymanager.packages.to.scan"));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect",
                env.getRequiredProperty("db.hibernate.dialect"));
        properties.put("hibernate.show_sql",
                env.getRequiredProperty("db.hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto",
                env.getRequiredProperty("db.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.connection.CharSet",
                env.getRequiredProperty("db.hibernate.connection.CharSet"));
        properties.put("hibernate.connection.characterEncoding",
                env.getRequiredProperty("db.hibernate.connection.characterEncoding"));
        properties.put("hibernate.connection.useUnicode",
                env.getRequiredProperty("db.hibernate.connection.useUnicode"));

        return properties;
    }


    @Bean
    public Logger getLogger() {
        return LogManager.getLogger("ua.com.univerpulse.loggers.model");
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDao();
    }*/

}
