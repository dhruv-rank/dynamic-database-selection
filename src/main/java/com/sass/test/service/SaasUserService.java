package com.sass.test.service;

import com.sass.test.dao.SaasUserRepository;
import com.sass.test.model.SaasUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaasUserService {

    @Autowired
    private SaasUserRepository saasUserRepository;

    public List<SaasUser> getUsers() {
        return saasUserRepository.findAll();
    }
}

