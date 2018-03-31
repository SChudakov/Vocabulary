package com.sschudakov.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  05.07.2017 for springData project.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.sschudakov.dao.repository"})
@EntityScan(basePackages = {"com.sschudakov.entity"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}