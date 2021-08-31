package com.midorlo.wolkenbruch.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.ServletContext;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
@Slf4j
public class ServletContextInitializerImpl implements ServletContextInitializer {

   private final Environment env;

   public ServletContextInitializerImpl(Environment env) {
      this.env = env;
   }

   @Override
   public void onStartup(ServletContext servletContext) {
      if (env.getActiveProfiles().length != 0) {
         log.info("Active profiles: {}", (Object[]) env.getActiveProfiles());
      }
   }
}
