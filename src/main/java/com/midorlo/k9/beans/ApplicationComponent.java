package com.midorlo.k9.beans;

import com.midorlo.k9.configuration.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationComponent {

    @Bean
    public CommandLineRunner initApplication(ApplicationProperties applicationProperties) {
        return args -> log.info(String.valueOf(applicationProperties));
    }
}
