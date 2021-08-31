package com.midorlo.wolkenbruch.rest.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public Resource to handle authentification.
 * <p>
 * For the sake of simplicity, we implemented a reduced
 * feature-set of JWT, disregarding any token refreshment mechanics.
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthResource {}

