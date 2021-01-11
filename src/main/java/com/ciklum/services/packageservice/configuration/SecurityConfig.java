package com.ciklum.services.packageservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@EnableWebSecurity
//@Configuration
/**
 * Class to configure the in-memory Spring Security.
 * Reads the user configuration from users.json file present in the src/main/resources.
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private IPAuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(IPAuthenticationProvider authenticationProvider){
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * Configures the end point security with user roles and the allowe URIs
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Configures the in-memory user and password security.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}

