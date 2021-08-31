package com.midorlo.wolkenbruch.model.security;

import com.midorlo.wolkenbruch.domain.security.Account;
import lombok.ToString;

@ToString
public class AccountListViewModel {

    private final String name;

    public AccountListViewModel(Account account) {
        this.name = account.getName();
    }
}
