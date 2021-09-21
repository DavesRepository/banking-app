package com.nextgen.bankingapp.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Log4j2
public class DefaultWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable().headers().frameOptions().disable().and()
        .authorizeRequests()
        .anyRequest()
        .permitAll();
  }

}
