package com.midorlo.wolkenbruch.service.account;

import com.midorlo.wolkenbruch.common.annotation.IntegrationTest;
import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.repository.security.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@Transactional
class AccountServiceIT {

    private Account account;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void init() {
        account = new Account()
                .setName("test")
                .setPasswordHash(passwordEncoder.encode("test"))
                .setLogin("test@localhost");
    }

    @Test
    @Transactional
    void assertThatAccountsCanBeCreated() {
        long accountsCount = accountRepository.count();
        accountService.create(account);
        assertThat(accountRepository.count()).isGreaterThan(accountsCount);
    }
}
