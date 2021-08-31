package com.midorlo.wolkenbruch.service.account;

import com.midorlo.wolkenbruch.domain.security.Privilege;
import com.midorlo.wolkenbruch.repository.security.PrivilegeRepository;
import com.midorlo.wolkenbruch.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrivilegeService extends ApplicationService<Privilege, PrivilegeRepository> {

   public PrivilegeService(PrivilegeRepository privilegeRepository) {
      super(privilegeRepository);
   }
}
