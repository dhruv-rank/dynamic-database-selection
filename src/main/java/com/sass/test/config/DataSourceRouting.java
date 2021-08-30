package com.sass.test.config;

import com.sass.test.model.SaasNumber;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceRouting extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return SaasContextHolder.getSaasNumber();
    }

    public void initDatasource(DataSource saas1, DataSource saas2) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(SaasNumber.SAAS_1, saas1);
        targetDataSources.put(SaasNumber.SAAS_2, saas2);
        this.setTargetDataSources(targetDataSources);
        this.setDefaultTargetDataSource(saas2);
    }

}
