package com.laohac.swp391spring2023.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.laohac.swp391spring2023.security.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    AppConfig appConfig = new AppConfig();

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //gọi userDetailsService trong bước 5 tiếp theo
        auth.userDetailsService(userDetailsService).passwordEncoder(appConfig.passwordEncoder());

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        
        httpSecurity.cors();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/js/**").permitAll()
                .antMatchers("/homepage/**", "/member/**").permitAll()
                .anyRequest().authenticated();
                /* .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/member/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/homepage")
                .failureForwardUrl("/member/login-error")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");*/

        return httpSecurity.build();
    }
}
