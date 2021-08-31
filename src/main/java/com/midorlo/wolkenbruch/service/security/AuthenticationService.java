package com.midorlo.wolkenbruch.service.security;

import com.midorlo.wolkenbruch.configuration.ApplicationProperties;
import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.model.security.AccountLoginDto;
import com.midorlo.wolkenbruch.repository.security.AccountRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides services related to authentification, authorization and authentication.
 */
@Service
@Slf4j
public class AuthenticationService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountRepository            accountRepository;
    private final ApplicationProperties        applicationProperties;
    private final Key                          encryptionKey;
    private final JwtParser                    tokenParser;

    public AuthenticationService(
            AuthenticationManagerBuilder authenticationManagerBuilder,
            AccountRepository accountRepository,
            ApplicationProperties applicationProperties
    ) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.accountRepository            = accountRepository;
        this.applicationProperties        = applicationProperties;
        this.encryptionKey                =
                Keys.hmacShaKeyFor(Decoders.BASE64.decode(applicationProperties.getSecurity()
                                                                               .getAuthentication()
                                                                               .getJwt()
                                                                               .getSecret()));
        this.tokenParser                  = Jwts.parserBuilder().setSigningKey(encryptionKey).build();
    }

    /**
     * Authenticates a login request.
     *
     * @param dto dto containing the login request.
     * @return Authentication.
     */
    @Transactional
    public Authentication login(AccountLoginDto dto) {
        return authenticationManagerBuilder
                .getObject()
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getAccountname(), dto.getPassword()
                        )
                );
    }

    /**
     * Gets the registered authentication for the given json web token.
     *
     * @param token scoped json web token.
     * @return registered authentication.
     */
    public Authentication findAuthentication(@NonNull String token) {
        Claims claims = tokenParser.parseClaimsJws(token).getBody();
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("auth").toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        org.springframework.security.core.userdetails.User principal = new
                org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * Creates a new Json Web Token from given authentication.
     *
     * @param authentication   account scoped authentication.
     * @param extendedValidity modifier for the token validity time.
     * @return new json web token.
     */
    public String createNewAuthentificationToken(@NonNull Authentication authentication, boolean extendedValidity) {

        String authorities = authentication.getAuthorities()
                                           .stream()
                                           .map(GrantedAuthority::getAuthority)
                                           .collect(Collectors.joining(","));

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .signWith(encryptionKey, SignatureAlgorithm.HS512)
                .setExpiration(SecurityUtils.getTokenValidity(applicationProperties, extendedValidity))
                .compact();
    }

    /**
     * Determines if the given json web token is currently registered for any authorization.
     *
     * @param authToken scoped json web token.
     * @return true, if the given json web token is currently registered for any authorization.
     */
    public boolean isAuthorized(@NonNull String authToken) {
        boolean isValidToken;
        try {
            isValidToken = !tokenParser.parseClaimsJws(authToken).getSignature().isEmpty();
        } catch (JwtException | IllegalArgumentException e) {
            throw new ApplicationSecurityException("Invalid token!");
        }
        return isValidToken;
    }

    /**
     * Get the login of the current account.
     *
     * @return the login of the current account.
     */
    public Optional<String> getPrincipal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(getPrincipal(securityContext.getAuthentication()));
    }

    /**
     * <b>Query</b> and return the principal account.
     *
     * @return Optional of the {@link Account} in current execution scope.
     */
    public Optional<Account> getPrincipalAccount() {
        String account = getPrincipal().orElseThrow(SecurityException::new);
        return accountRepository.findByName(account);
    }

    /**
     * Get the login of the current account by its authentication.
     *
     * @return the login of the current account.
     */
    private String getPrincipal(Authentication authentication) {
        String principal = null;
        if (authentication != null && authentication.getPrincipal() != null) {
            principal = (authentication.getPrincipal() instanceof String)
                    ? (String) authentication.getPrincipal()
                    : ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return principal;
    }

    /**
     * Checks if the current account has a specific authority.
     *
     * @param authority the authority to check.
     * @return true if the current account has the authority, false otherwise.
     */
    public boolean isAuthenticated(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && getAccountAuthenticationAuthoritiesNames(authentication).anyMatch(authority::equals);
    }

    /**
     * Gets the JSON Web Token for the current principal.
     *
     * @return the JSON Web Token.
     */
    public Optional<String> getPrincipalToken() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                       .filter(authentication -> authentication.getCredentials() instanceof String)
                       .map(authentication -> (String) authentication.getCredentials());
    }

    /**
     * Gets the current account's authentications' authorities' names.
     *
     * @param authentication the current account's authentications.
     * @return authentications' authorities' names.
     */
    private Stream<String> getAccountAuthenticationAuthoritiesNames(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
    }

    /**
     * Utility Methods that are in scope for AuthenticationService only.
     */
    private static class SecurityUtils {

        /**
         * Gets the configured lifetime of a json web token.
         *
         * @param applicationProperties configuration object.
         * @param extended              true if the token should have an extended lifetime.
         * @return the lifetime in ms.
         */
        private static Date getTokenValidity(ApplicationProperties applicationProperties, boolean extended) {
            return new Date(
                    new Date().getTime() + 1000 * (extended
                            ? applicationProperties.getSecurity()
                                                   .getAuthentication()
                                                   .getJwt()
                                                   .getTokenValidityInSecondsForRememberMe()
                            : applicationProperties.getSecurity()
                                                   .getAuthentication()
                                                   .getJwt()
                                                   .getTokenValidityInSeconds())
            );
        }
    }
}
