package com.midorlo.wolkenbruch.configuration;

import com.midorlo.wolkenbruch.service.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * REST Security Configuration.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class WebSecurityConfigurerAdapterImpl extends WebSecurityConfigurerAdapter {

    public WebSecurityConfigurerAdapterImpl(AuthenticationService provider,
                                            ApplicationProperties properties) {
    }

    /**
     * Pin the password encoder to be used as {@link BCryptPasswordEncoder}.
     *
     * @return {@link BCryptPasswordEncoder} instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure {@link WebSecurity}.
     * <p>
     * Endpoints specified in this method will be ignored by Spring Security, meaning it
     * will not protect them from CSRF, XSS, Clickjacking, and so on.
     * <p>
     * By implementation, we will handle as such:
     * <ul>
     * <li>All OPTION requests, as they are used for discovery.</li>
     * <li>The swagger-ui endpoint, as it is served for documentation purposes.</li>
     * <li>The test/* endpoints, as they provide health status information.</li>
     * </ul>
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
           .antMatchers(HttpMethod.OPTIONS, "/**")
           .antMatchers(HttpMethod.GET, "/**")
           .antMatchers(HttpMethod.POST, "/**");
    }

    /**
     * Configure {@link HttpSecurity}.
     * <p>
     * Used by {@link #authenticationManager()} to attempt to obtain an {@link AuthenticationManager}, so that
     * {@link #authenticationManagerBean()} exposes the resulting {@link AuthenticationManager} as a Bean.
     * <p>
     * The {@link #userDetailsServiceBean()} then can be used to expose the last populated {@link UserDetailsService}
     * that is created with the {@link AuthenticationManagerBuilder} as a Bean.
     * <p>
     * The {@link UserDetailsService} will also automatically be populated on
     * {@link HttpSecurity#getSharedObject(Class)} for use with other
     * {@link SecurityContextConfigurer} (i.e. RememberMeConfigurer )
     *
     * @param httpSecurity the {@link AuthenticationManagerBuilder} to use
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity = httpSecurity
                .csrf()
                .disable();

        httpSecurity = httpSecurity
                .cors()
                .disable();

        httpSecurity = httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        httpSecurity.authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/api/v1/**").permitAll()
                    .antMatchers("/api/v1/account/**").permitAll()
                    .antMatchers("/api/v1/account/login").permitAll();
    }

}
