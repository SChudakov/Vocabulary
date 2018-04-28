package com.sschudakov.web.configuration;

import com.sschudakov.dao.impl.jdbc.LanguageDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WCRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WMRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordClassDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordCollectionDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordDaoJdbcImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.dao.interf.WordDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
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

    @Resource
    private Environment env;


    /*------------ jdbc dao beans ------------------*/
    @Primary
    @Bean
    public LanguageDao languageJdbcDao() {
        return new LanguageDaoJdbcImpl();
    }

    @Primary
    @Bean
    public WordCollectionDao wordCollectionJdbcDao() {
        return new WordCollectionDaoJdbcImpl();
    }

    @Primary
    @Bean
    public WordClassDao wordClassJdbcDao() {
        return new WordClassDaoJdbcImpl();
    }

    @Primary
    @Bean
    public WordDao wordDao() {
        return new WordDaoJdbcImpl(
                languageJdbcDao(),
                wordClassJdbcDao()
        );
    }

    @Primary
    @Bean
    public WMRDao wmrJdbcDao() {
        return new WMRDaoJdbcImpl(wordDao());
    }

    @Primary
    @Bean
    public WCRDao wcrJdbcDao() {
        return new WCRDaoJdbcImpl(wordDao(), wordCollectionJdbcDao());
    }


    /*------------ hibernate dao beans ------------------*/

    /*@Bean
    public LanguageDao languageHbnDao() {
        return new LanguageDaoHbnImpl();
    }

    @Bean
    public WordCollectionDao wordCollectionHbnDao() {
        return new WordCollectionDaoHbnImpl();
    }

    @Bean
    public WordClassDao wordClassHbnDao() {
        return new WordClassDaoHbnImpl();
    }

    @Bean
    public WordDao wordHbnDao() {
        return new WordDaoHbnImpl();
    }

    @Bean
    public WMRDao wmrHbnDao() {
        return new WMRDaoHbnImpl();
    }

    @Bean
    public WCRDao wcrHbnDao() {
        return new WCRDaoHbnImpl();
    }*/


    /*----------- other stuff --------------*/


    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {
        // UserDetailsServiceRetrieves implementation which retrieves the
        // user details (username, password, enabled flag, and authorities) from a database using JDBC queries.

        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setUsersByUsernameQuery("select username ,password, enabled from sps_users where username=?");
        jdbcDao.setAuthoritiesByUsernameQuery("select b.username, a.role from sps_user_roles a join sps_users b on a.userid=b.userid where b.username=?");
        return jdbcDao;
    }

    @Bean
    @Qualifier(value = "jpaTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public Logger getLogger() {
        return LogManager.getLogger("com.sschudakov.dao");
    }
}
