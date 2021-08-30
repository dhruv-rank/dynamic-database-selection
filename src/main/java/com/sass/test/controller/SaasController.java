package com.sass.test.controller;

import com.sass.test.model.SaasUser;
import com.sass.test.service.SaasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaasController {

    @Autowired
    private SaasUserService saasUserService;

    @GetMapping("s_user")
    public List<SaasUser> getSaasUsers() {
        return this.saasUserService.getUsers();
    }
}
