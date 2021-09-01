package com.sass.test.tenant.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DataSourceRouting extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContextHolder.getTenantId();
    }

}
