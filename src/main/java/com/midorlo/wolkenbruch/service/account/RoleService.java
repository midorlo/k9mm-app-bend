package com.midorlo.wolkenbruch.service.account;

import com.midorlo.wolkenbruch.domain.security.Privilege;
import com.midorlo.wolkenbruch.domain.security.Role;
import com.midorlo.wolkenbruch.repository.security.RoleRepository;
import com.midorlo.wolkenbruch.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * Service Class for Managing Roles.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Service
@Slf4j
public class RoleService extends ApplicationService<Role, RoleRepository> {

   public RoleService(RoleRepository roleRepository) {
      super(roleRepository);
   }

   @Transactional
   public Role create(String name, Collection<Privilege> privileges) {
      return create(new Role(name, privileges));
   }

   public Role addAllPrivileges(Role record, Privilege... privilegesRecords) {
      record.getPrivileges().addAll(Set.of(privilegesRecords));
      return update(record);
   }
}

