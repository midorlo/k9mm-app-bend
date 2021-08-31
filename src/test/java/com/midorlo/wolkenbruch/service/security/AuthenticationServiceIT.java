//package com.midorlo.wolkenbruch.service.security;
//
//import com.midorlo.wolkenbruch.common.annotation.IntegrationTest;
//import com.midorlo.wolkenbruch.common.annotation.MocksAnonymousAccount;
//import com.midorlo.wolkenbruch.model.security.AccountLoginDto;
//import com.midorlo.wolkenbruch.repository.security.AccountRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.test.context.support.WithMockUser;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@IntegrationTest
//class AuthenticationServiceIT {
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Test
//    public void authenticateTest() {
//        Authentication authenticate = authenticationService.login(new AccountLoginDto("Admin", "admin", "", true));
//        assertThat(authenticate).isNotNull();
//        assertThat(authenticate.isAuthenticated()).isTrue();
//        assertThat(authenticate.getPrincipal().toString().contains("Admin")).isTrue();
//    }
//
//    @Test
//    @WithMockUser("Admin")
//    public void getPrincipalAccountTest() {
//        assertThat(authenticationService.getPrincipalAccount()).isEqualTo(accountRepository.findByName("Admin"));
//    }
//
//    @Test
//    @MocksAnonymousAccount
//    public void getPrincipalAccountAsGuestTest() {
//        assertThat(authenticationService.getPrincipalAccount()).isEqualTo(Optional.empty());
//    }
//}
