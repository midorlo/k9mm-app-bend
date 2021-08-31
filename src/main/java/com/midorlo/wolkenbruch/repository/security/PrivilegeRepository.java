package com.midorlo.wolkenbruch.repository.security;

import com.midorlo.wolkenbruch.domain.security.Privilege;
import com.midorlo.wolkenbruch.repository.ApplicationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends ApplicationRepository<Privilege> {
}
