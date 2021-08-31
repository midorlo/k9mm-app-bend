package com.midorlo.wolkenbruch.service.security;

import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.service.account.AccountService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.SPRING_SECURITY_OVERRIDE;

/**
 * UserDetailsService is described as a core interface that loads user-specific data in the Spring documentation.
 * <p>
 * In most use cases, authentication providers extract user identity information based on credentials from a database
 * and then perform validation. Because this use case is so common, Spring developers decided to extract it as a
 * separate interface, which exposes the single function:
 * <p>
 * loadUserByUsername accepts username as a parameter and returns the user identity object.
 */
@Slf4j
@Component(SPRING_SECURITY_OVERRIDE)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Loads a Spring Security User.
     *
     * @param name unique user name.
     * @return Spring Security UserDetails Pojo
     */
    @Override
    public @NonNull UserDetails loadUserByUsername(final String name) {
        return accountService
                .findByName(name)
                .map(this::toSpringSecurityUser)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Transforms a {@link Account}
     * to a {@link org.springframework.security.core.userdetails.User}
     *
     * @param account {@link Account}
     * @return {@link org.springframework.security.core.userdetails.User}
     */
    private org.springframework.security.core.userdetails.User toSpringSecurityUser(Account account) {
        return new org.springframework.security.core.userdetails.User(
                account.getName(),
                account.getPasswordHash(),
                account.getAllPrivileges()
                       .stream()
                       .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                       .collect(Collectors.toList())
        );
    }
}
