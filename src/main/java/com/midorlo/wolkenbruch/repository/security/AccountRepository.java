package com.midorlo.wolkenbruch.repository.security;

import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.repository.ApplicationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends ApplicationRepository<Account> {

    Optional<Account> findOneByLogin(String mail);

    Optional<Account> findByNameEqualsIgnoreCase(String name);


//    List<Account> findAllByIdNotNullAndActivatedIsFalse();
}
