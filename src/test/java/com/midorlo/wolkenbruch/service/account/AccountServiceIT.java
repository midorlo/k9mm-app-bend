//package com.midorlo.k9.service.account;
//
//import com.midorlo.k9.common.annotation.IntegrationTest;
//import com.midorlo.k9.domain.security.Account;
//import com.midorlo.k9.repository.security.AccountRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@IntegrationTest
//@Transactional
//class AccountsResourceServiceIT {
//
//    private Account account;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AccountsResourceService accountsResourceService;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @BeforeEach
//    public void init() {
//        account = new Account()
//                .setName("test")
//                .setPasswordHash(passwordEncoder.encode("test"))
//                .setLogin("test@localhost");
//    }
//
//    @Test
//    @Transactional
//    void assertThatAccountsCanBeCreated() {
//        long accountsCount = accountRepository.count();
//        accountsResourceService.create(account);
//        assertThat(accountRepository.count()).isGreaterThan(accountsCount);
//    }
//}
