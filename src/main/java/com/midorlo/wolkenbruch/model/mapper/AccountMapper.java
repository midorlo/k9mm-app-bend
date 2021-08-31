package com.midorlo.wolkenbruch.model.mapper;

import com.midorlo.wolkenbruch.configuration.ApplicationProperties;
import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.model.security.AccountLoginDto;
import com.midorlo.wolkenbruch.model.security.AccountLoginViewModel;
import com.midorlo.wolkenbruch.model.security.JsonWebToken;
import com.midorlo.wolkenbruch.util.RandomPasswordGenerator;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

/**
 * Maps any account related dto to a account pojo. Validates against field defaults.
 */
public class AccountMapper {

    private final PasswordEncoder       passwordEncoder;
    private final ApplicationProperties applicationProperties;

    public AccountMapper(PasswordEncoder passwordEncoder, ApplicationProperties applicationProperties) {
        this.passwordEncoder       = passwordEncoder;
        this.applicationProperties = applicationProperties;
    }

    /**
     * Maps a {@link AccountLoginViewModel} into a {@link Account} object.
     *
     * @param dto {@link AccountLoginViewModel}
     * @return {@link Account}.
     */
    public Account toAccount(@NonNull AccountLoginDto dto) {
        return new Account()
                .setName(dto.getAccountname())
                .setLogin(dto.getEmail())
                .setActive(applicationProperties.getSecurity().getRegistration().getRequireActivation())
                .setPasswordHash(passwordEncoder.encode(
                        (dto.getPassword() != null
                                ? dto.getPassword()
                                : new RandomPasswordGenerator().generateSecureRandomPassword())
                ));
    }

    public static AccountLoginViewModel toViewModel(
            Authentication authentication,
            JsonWebToken token
    ) {
        return new AccountLoginViewModel(
                authentication.getName(),
                token,
                authentication.getAuthorities()
                              .stream()
                              .map(GrantedAuthority::getAuthority)
                              .collect(Collectors.toList())
        );

    }
}
