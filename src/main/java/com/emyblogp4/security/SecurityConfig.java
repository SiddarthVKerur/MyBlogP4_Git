package com.emyblogp4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()     // get() operation can be performed without login...if we want to set accessibility then remove permitAll() and use hasAnyRole("ADMIN") or hasAnyRole("USER") or hasAnyRole("ADMIN",USER)
                .antMatchers("/api/**").permitAll()     // get() operation can be performed without login...if we want to set accessibility then remove permitAll() and use hasAnyRole("ADMIN") or hasAnyRole("USER") or hasAnyRole("ADMIN",USER)
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder().username("Test").password(getEncodedPassword().encode("Testing")).roles("USER").build();
        UserDetails manager = User.builder().username("admin").password(getEncodedPassword().encode("Admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, manager);
    }
    @Bean
    PasswordEncoder getEncodedPassword() {
        return new BCryptPasswordEncoder();     //BCryptPasswordEncoder this class has encode() method that will encode the password so getEncodedPassword() method is created
    }
}
