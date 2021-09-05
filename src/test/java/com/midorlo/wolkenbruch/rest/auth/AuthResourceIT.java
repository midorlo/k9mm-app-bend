//package com.midorlo.k9.rest.auth;
//
//import com.midorlo.k9.common.TestUtils;
//import com.midorlo.k9.common.annotation.IntegrationTest;
//import com.midorlo.k9.domain.security.Account;
//import com.midorlo.k9.model.security.LoginDto;
//import com.midorlo.k9.repository.security.AccountRepository;
//import com.midorlo.k9.util.security.RandomPasswordGenerator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SuppressWarnings("unused")
//@IntegrationTest
//class AuthResourceIT {
//
//    private Account      account;
//    private LoginDto dto;
//    private String       randomPassword;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private MockMvc restAccountMockMvc;
//
//    @BeforeEach
//    public void createTestAccount() {
//        this.randomPassword = new RandomPasswordGenerator().generateSecureRandomPassword();
//        this.account = accountRepository.saveAndFlush(new Account("test", "test@localhost", randomPassword, true,
//                null));
//        this.dto     = new LoginDto(account.getName(), randomPassword, "", true);
//    }
//
//    @Test
//    @Transactional
//    void correctLogin() throws Exception {
//        restAccountMockMvc.perform(TestUtils.post(
//                                  "/api/auth",
//                                  new LoginDto("Admin", "admin", "", true)))
//                          .andExpect(status().isOk());
//    }
//
//    @Test
//    @Transactional
//    void wrongLogin() throws Exception {
//        restAccountMockMvc.perform(TestUtils.post(
//                                  "/api/auth",
//                                  new LoginDto("Admin", "", "", true)))
//                          .andExpect(status().is4xxClientError());
//    }
//}
