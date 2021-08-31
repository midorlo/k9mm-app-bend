package com.midorlo.wolkenbruch.rest.account;

import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.model.mapper.AccountMapper;
import com.midorlo.wolkenbruch.model.mapper.RestMapper;
import com.midorlo.wolkenbruch.model.security.AccountLoginDto;
import com.midorlo.wolkenbruch.model.security.AccountLoginViewModel;
import com.midorlo.wolkenbruch.model.security.JsonWebToken;
import com.midorlo.wolkenbruch.service.account.AccountService;
import com.midorlo.wolkenbruch.service.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/api/v1/account")
public class AccountController {

    public static final String ENTRY_LOGGING = "Processing request {}";

    private final AuthenticationService authenticationService;
    private final AccountService           accountService;

    public AccountController(
            AuthenticationService service,
            AccountService accountService) {
        this.authenticationService = service;
        this.accountService           = accountService;
    }

    /**
     * Gets the Account's Account Information
     *
     * @return Account record.
     */
    @GetMapping("")
    public ResponseEntity<Account> getAccount(HttpServletRequest request) {
        log.info(ENTRY_LOGGING, request);
        Account account = authenticationService.getPrincipalAccount().orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(account);
    }

    /**
     * Public resource for account logging in.
     *
     * @param dto dto.
     * @return http response entity containing an authenticated jwt.
     */
    @PostMapping("/login")
    public ResponseEntity<AccountLoginViewModel> login(HttpServletRequest request,
                                                    @RequestBody AccountLoginDto dto) {
        log.info(ENTRY_LOGGING, request);
        Authentication authentication = authenticationService.login(dto);
        String token = authenticationService
                .createNewAuthentificationToken(authentication, dto.getRememberMe());
        JsonWebToken       jsonWebToken       = new JsonWebToken(token);
        AccountLoginViewModel accountLoginViewModel = AccountMapper.toViewModel(authentication, jsonWebToken);

        return new ResponseEntity<>(
                accountLoginViewModel,
                RestMapper.toHttpHeader(authentication, token),
                HttpStatus.OK
        );
    }

    /**
     * Public resource for account registering.
     *
     * @param dto dto.
     * @return http 201.
     */
    @PostMapping("/register")
    public ResponseEntity<JsonWebToken> register(@RequestBody AccountLoginDto dto) {
        log.info("Created Account {}", accountService.create(dto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
