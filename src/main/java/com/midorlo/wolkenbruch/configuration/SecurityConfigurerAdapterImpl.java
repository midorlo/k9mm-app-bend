package com.midorlo.wolkenbruch.configuration;

import com.midorlo.wolkenbruch.service.security.AuthenticationService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Configure Spring Http Security (using Json-Web-Tokens).
 */
public class SecurityConfigurerAdapterImpl extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthenticationService authenticationService;
    private final ApplicationProperties applicationProperties;

    public SecurityConfigurerAdapterImpl(AuthenticationService authenticationService, ApplicationProperties applicationProperties) {
        this.authenticationService = authenticationService;
        this.applicationProperties = applicationProperties;
    }

    /**
     * Applies Spring security implementation to the filter chain.
     * @param http http security configuration.
     */
    @Override
    public void configure(HttpSecurity http) {

        GenericFilterBean applicationFilterBean = new GenericFilterBean() {
            @Override
            public void doFilter(
                    ServletRequest request,
                    ServletResponse response,
                    FilterChain chain) throws IOException, ServletException {

                String token = resolveToken((HttpServletRequest) request);
                if (StringUtils.hasText(token) && authenticationService.isAuthorized(token)) {
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authenticationService.findAuthentication(token));
                }
                chain.doFilter(request, response);
            }

            /**
             * Resolves our token from a http request.
             * @param request http request
             * @return unverified token or null, if no token was found.
             */
            private String resolveToken(HttpServletRequest request) {
                String bearerToken = request.getHeader(applicationProperties.getSecurity().getAuthorization().getHeaderName());
                return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")
                        ? bearerToken.substring(7)
                        : null;
            }

        };
        http.addFilterBefore(applicationFilterBean, UsernamePasswordAuthenticationFilter.class);
    }
}
