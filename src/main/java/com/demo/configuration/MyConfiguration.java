package com.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration(proxyBeanMethods = false)
@EnableJpaAuditing
public class MyConfiguration {

    @Bean
    public AuditorAware<String> auditorAwareRef() {
        return () -> Optional.of("HO");
    }
}
