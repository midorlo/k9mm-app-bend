package com.midorlo.wolkenbruch.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object handling account registration.
 */
@Data
@Slf4j
@NoArgsConstructor
public class AccountLoginViewModel {

    private String       accountname;
    private String       email;
    private JsonWebToken token;
    private List<String> roles = new ArrayList<>();

    public AccountLoginViewModel(String accountname, String email) {
        this.accountname = accountname;
        this.email    = email;
    }

    public AccountLoginViewModel(String accountname, JsonWebToken token, List<String> roles) {
        this.accountname = accountname;
        this.token = token;
        this.roles    = roles;
    }
}
