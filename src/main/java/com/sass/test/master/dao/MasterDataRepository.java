package com.sass.test.master.dao;

import com.sass.test.master.model.DataSourceDetails;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MasterDataRepository {

    @Autowired
    @Qualifier("masterSessionFactory")
    private SessionFactory sessionFactory;

    @Transactional(transactionManager = "masterTransactionManager")
    public List<DataSourceDetails> getAll(){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DataSourceDetails.class);
        return criteria.list();
    }
}
