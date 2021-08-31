package com.midorlo.wolkenbruch.configuration;

import com.midorlo.wolkenbruch.components.AuditorAwareImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.midorlo.wolkenbruch.components.AuditorAwareImpl.AUDITOR_AWARE_IMPL_INSTANCE_NAME;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.REPOSITORY_SCAN_PARENT;

/**
 * Extended Database Configuration.
 * <p>
 * Registers {@link AuditorAwareImpl} at application level.
 */
@Configuration
@EnableJpaRepositories(REPOSITORY_SCAN_PARENT)
@EnableJpaAuditing(auditorAwareRef = AUDITOR_AWARE_IMPL_INSTANCE_NAME)
@EnableTransactionManagement
@Slf4j
public class PersistenceConfiguration {

   /**
    * Simple logging remark, since the configuration itself is annotation-based.
    */
   @Bean
   public void onLoad() {
      log.info("Database configuration loaded successfully.");
   }
}
