package com.midorlo.k9.configuration;

import com.midorlo.k9.configuration.core.CoreProperties;
import com.midorlo.k9.configuration.security.SecurityProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final CoreProperties     coreProperties;
    private final SecurityProperties securityProperties;

    public ApplicationProperties(CoreProperties coreProperties,
                                 SecurityProperties securityProperties) {

        this.coreProperties     = coreProperties;
        this.securityProperties = securityProperties;
    }
}
