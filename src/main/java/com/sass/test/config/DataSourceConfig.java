package com.sass.test.config;

import com.sass.test.model.SaasUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.sass.test",
        transactionManagerRef = "transactionManager",
        entityManagerFactoryRef = "entityManager")
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    private Saas1Detail saas1Detail;

    @Autowired
    private Saas2Detail saas2Detail;

    @Bean
    @Primary
    @Autowired
    public DataSource dataSource() {
        DataSourceRouting routingDataSource = new DataSourceRouting();
        routingDataSource.initDatasource(saas1Datasource(), saas2Datasource());
        return routingDataSource;
    }

    public DataSource saas1Datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(saas1Detail.getUrl());
        dataSource.setUsername(saas1Detail.getUsername());
        dataSource.setPassword(saas1Detail.getPassword());
        return dataSource;
    }

    public DataSource saas2Datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(saas2Detail.getUrl());
        dataSource.setUsername(saas2Detail.getUsername());
        dataSource.setPassword(saas2Detail.getPassword());
        return dataSource;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource()).packages(SaasUser.class).build();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(
            @Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
