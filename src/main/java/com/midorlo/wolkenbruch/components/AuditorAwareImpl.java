package com.midorlo.wolkenbruch.components;

import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.model.security.DefaultRoles;
import com.midorlo.wolkenbruch.repository.security.AccountRepository;
import com.midorlo.wolkenbruch.service.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
@Component
@Slf4j
public class AuditorAwareImpl implements AuditorAware<Account> {

    public static final String AUDITOR_AWARE_IMPL_INSTANCE_NAME = "auditorAwareImpl";

    private final AuthenticationService authenticationService;
    private final AccountRepository     accountRepository;

    public AuditorAwareImpl(AuthenticationService authenticationService, AccountRepository accountRepository) {
        this.authenticationService = authenticationService;
        this.accountRepository     = accountRepository;
    }

    @Override
    public @NonNull
    Optional<Account> getCurrentAuditor() {
        String currentAuditorAccountname = authenticationService.getPrincipal().orElse(DefaultRoles.System.toString());
        return accountRepository.findByNameEqualsIgnoreCase(currentAuditorAccountname);
    }
}
