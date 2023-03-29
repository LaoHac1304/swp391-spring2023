package com.laohac.swp391spring2023.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class AdminSecurityConfig {
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder1(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider1(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder1());
        return provider;

    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authenticationProvider(authenticationProvider1());

        httpSecurity.authorizeRequests()
        .antMatchers("/css/**", "/js/**","/images/**").permitAll()
        .antMatchers("/", "/pay/**", "/homepage","/homepage/login", "/homepage/logout", "/users/login","/homepage/defaultSuccessUrl","/homepage/**").permitAll();

        httpSecurity .antMatcher("/member/**").authorizeRequests().anyRequest().hasAuthority("admin")
        .and()
        .formLogin()
            .loginPage("/users/login")
            .usernameParameter("username")
            .loginProcessingUrl("/users/login")
            .defaultSuccessUrl("/homepage/defaultSuccessUrl")
            .permitAll();
            // .and()
            // .logout()
            // .invalidateHttpSession(true)
            // .deleteCookies("JSESSIONID")
            // .logoutUrl("/member/logout");
        
        return httpSecurity.build();
    }
}
