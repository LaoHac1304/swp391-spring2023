package com.laohac.swp391spring2023.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.laohac.swp391spring2023.service.UserService;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        System.out.println(email);
        if (!userService.checkEmailExisted(email)){
            userService.createUserByEmail(oAuth2User);
        }
        
         Set<GrantedAuthority> authorities = new HashSet<>();
         authorities.add(new SimpleGrantedAuthority("customer"));
         UserDetails userDetails = new org.springframework.security.core.userdetails.User(email, "123", authorities);
         //Authentication authentication1 = new UsernamePasswordAuthenticationToken(userDetails, "123", userDetails.getAuthorities());
         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        
        
         //SecurityContextHolder.getContext();
        //.setAuthentication(authentication1);
        response.sendRedirect("/users/home");
        super.onAuthenticationSuccess(request, response, authentication);
        
    }
    
    
}
