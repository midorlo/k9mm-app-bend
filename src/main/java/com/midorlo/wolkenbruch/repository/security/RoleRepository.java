package com.midorlo.wolkenbruch.repository.security;

import com.midorlo.wolkenbruch.domain.security.Role;
import com.midorlo.wolkenbruch.repository.ApplicationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ApplicationRepository<Role> {
}
