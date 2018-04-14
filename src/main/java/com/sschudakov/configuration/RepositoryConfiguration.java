package com.sschudakov.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories(basePackages = {"com.sschudakov.dao.impl.hibernate"})
@EntityScan(basePackages = {"com.sschudakov.entity"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
