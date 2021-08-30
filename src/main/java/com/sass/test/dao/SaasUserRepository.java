package com.sass.test.dao;

import com.sass.test.model.SaasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaasUserRepository extends JpaRepository<SaasUser, Long> {
}
