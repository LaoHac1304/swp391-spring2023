package com.laohac.swp391spring2023.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomerSecurityConfig {

    @Bean
    public UserDetailsService customerUserDetailsService() {
        return new CustomerUserDetailsService();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder2(){
    // //return NoOpPasswordEncoder.getInstance();
    // return new BCryptPasswordEncoder();

    // }

    @Bean
    public DaoAuthenticationProvider authenticationProvider2() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerUserDetailsService());
        provider.setPasswordEncoder(passwordEncoder2);
        return provider;

    }

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authenticationProvider(authenticationProvider2());

        httpSecurity.authorizeRequests()
                .antMatchers("/", "/pay/**", "/homepage", "/homepage/**", "/users", "/users/**", "/trip", "/trip/**", "/homepage/logout",
                        "/route", "/route/**", "/car", "car/**", "/booking", "/booking/**", "/users/verify",
                        "/oauth2/**", "/css/**", "/js/**", "/images/**")
                .permitAll()
                // .antMatchers("/users/**").hasAuthority("customer")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("username")
                .loginProcessingUrl("/users/login")
                .defaultSuccessUrl("/homepage/defaultSuccessUrl")
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/users/login")
                .userInfoEndpoint().userService(oAuth2UserService)
                .and()
                .defaultSuccessUrl("/users/home")
                .successHandler(oAuth2LoginSuccessHandler);
                // .and()
                // .logout()
                // .invalidateHttpSession(true)
                // .deleteCookies("JSESSIONID")
                // .logoutUrl("/users/logout");

        return httpSecurity.build();
    }

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Autowired
    private PasswordEncoder passwordEncoder2;
}