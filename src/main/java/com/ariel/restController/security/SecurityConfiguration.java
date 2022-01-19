package com.ariel.restController.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(getPasswordEncoder())
                .withUser("adm").password("$2y$10$d4yX/GeNyGN6eu9qwG2wVebKPFCi8FhfRnc5lYxojW/GKpslN8gCG")
                .roles("USER", "ADMIN")
                .and()
                .withUser("usr").password("$2y$10$9cOtB.TZLuBJZPpMtgcSYeCR0NlnT.NrPChRy7QcuEB5WP4BiQMuC")
                .roles("USER");
    }

    private BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    // Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/surveys/**", "/users/**").hasRole("USER")
                .antMatchers("/h2-console", "/actuator/**", "/**").hasRole("ADMIN")
                .and().csrf().disable().headers().frameOptions().disable();
    }
}
