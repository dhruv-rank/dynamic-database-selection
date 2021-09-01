package com.sass.test.tenant.config;

import com.sass.test.master.dao.MasterDataRepository;
import com.sass.test.master.model.DataSourceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Autowired
    private MasterDataRepository masterDataRepository;

    public DataSource routeDataSource(List<DataSourceDetails> dataSourceDetailsList) {
        DataSourceRouting routingDataSource = new DataSourceRouting();
        Map<Object, Object> DataSourceMap = new HashMap<>();
        for (DataSourceDetails dataSourceDetails : dataSourceDetailsList) {
            DataSourceMap.put(dataSourceDetails.getKey(), new DriverManagerDataSource(dataSourceDetails.getUrl(), dataSourceDetails.getUsername(), dataSourceDetails.getPassword()));
        }
        routingDataSource.setTargetDataSources(DataSourceMap);
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    @Bean(name = "entityManagerFactory")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSourceDetail) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("com.sass.test.tenant.model");
        List<DataSourceDetails> dataSourceDetailsList = this.masterDataRepository.getAll();

        sessionFactory.setDataSource(routeDataSource(dataSourceDetailsList));
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {                  // configure hibernate properties
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        return properties;
    }

    @Bean(name = "appTransactionManager")
    @Order(2)
    @Primary
    public HibernateTransactionManager transactionManager(@Qualifier("entityManagerFactory") LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory.getObject());
        return txManager;
    }
}
