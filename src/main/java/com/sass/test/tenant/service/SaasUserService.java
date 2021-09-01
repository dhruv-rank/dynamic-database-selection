package com.sass.test.tenant.service;

import com.sass.test.tenant.dao.SaasUserRepository;
import com.sass.test.tenant.model.SaasUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "appTransactionManager")
public class SaasUserService {

    @Autowired
    private SaasUserRepository saasUserRepository;

    public List<SaasUser> getUsers() {
        return saasUserRepository.getAll();
    }
}

