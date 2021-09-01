package com.sass.test.tenant.dao;

import com.sass.test.tenant.model.SaasUser;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaasUserRepository {

    @Autowired
    @Qualifier("entityManagerFactory")
    SessionFactory sessionFactory;

    public List<SaasUser> getAll(){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SaasUser.class);
        return criteria.list();
    }
}
