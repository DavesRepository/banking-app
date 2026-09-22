package com.nextgen.bankingapp.security;

import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class DefaultWebSecurityConfiguration {

  private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(DefaultWebSecurityConfiguration.class);

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            // 1. Disable CSRF using the new Lambda DSL
            .csrf(AbstractHttpConfigurer::disable)

            // 2. Disable FrameOptions (often used for H2 console access)
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

            // 3. New authorization API
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
            );

    return http.build();
  }

}
