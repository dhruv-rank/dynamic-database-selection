package com.sass.test.master.config;

import com.sass.test.master.properties.MasterDataDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Order(1)
public class MasterDataConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Autowired
    private MasterDataDetails masterDataDetails;

    @Bean(name = "masterTransactionManager")                      // creating transaction manager factory
    public HibernateTransactionManager getTransactionManager() {
        return new HibernateTransactionManager(
                getMasterSessionFactory().getObject());
    }

    private DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(masterDataDetails.getUrl());
        dataSourceBuilder.username(masterDataDetails.getUsername());
        dataSourceBuilder.password(masterDataDetails.getPassword());
        dataSourceBuilder.driverClassName(masterDataDetails.getDriverClassName());
        return dataSourceBuilder.build();
    }

    private Properties hibernateProperties() {                  // configure hibernate properties
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        return properties;
    }

    @Primary
    @Bean(name = "masterSessionFactory")
    public LocalSessionFactoryBean getMasterSessionFactory() {            // creating session factory
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("com.sass.test.master.model");
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

}
