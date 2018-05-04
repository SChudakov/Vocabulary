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

    /*----------- stuff --------------*/

    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {


         /**
          * UserDetailsServiceRetrieves implementation which retrieves the
          * user details (username, password, enabled flag, and authorities)
          * from a database using JDBC queries.
         */

        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(this.dataSource);
        jdbcDao.setUsersByUsernameQuery("select name as username ,password, 1 as enabled from users where name=?");
        jdbcDao.setAuthoritiesByUsernameQuery("select uu.name as username, ro.name as role from users uu  join user_role ur on uu.user_id=ur.user join roles ro on ro.role_id=ur.role where uu.name=?");
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
