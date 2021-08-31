package com.midorlo.wolkenbruch.rest.auth;

import com.midorlo.wolkenbruch.common.TestUtils;
import com.midorlo.wolkenbruch.common.annotation.IntegrationTest;
import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.model.security.AccountLoginDto;
import com.midorlo.wolkenbruch.repository.security.AccountRepository;
import com.midorlo.wolkenbruch.util.RandomPasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("unused")
@IntegrationTest
class AuthResourceIT {

    private Account      account;
    private AccountLoginDto dto;
    private String       randomPassword;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MockMvc restAccountMockMvc;

    @BeforeEach
    public void createTestAccount() {
        this.randomPassword = new RandomPasswordGenerator().generateSecureRandomPassword();
        this.account = accountRepository.saveAndFlush(new Account("test", "test@localhost", randomPassword, true,
                null));
        this.dto     = new AccountLoginDto(account.getName(), randomPassword, "", true);
    }

    @Test
    @Transactional
    void correctLogin() throws Exception {
        restAccountMockMvc.perform(TestUtils.post(
                                  "/api/auth",
                                  new AccountLoginDto("Admin", "admin", "", true)))
                          .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void wrongLogin() throws Exception {
        restAccountMockMvc.perform(TestUtils.post(
                                  "/api/auth",
                                  new AccountLoginDto("Admin", "", "", true)))
                          .andExpect(status().is4xxClientError());
    }
}
