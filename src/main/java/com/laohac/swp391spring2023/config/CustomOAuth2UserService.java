package com.laohac.swp391spring2023.config;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Get the user's profile information from the OAuth2 provider (Google)
        OAuth2User userGG =  super.loadUser(userRequest);
        //String email = userGG.getAttribute("email");
        // Return the UserDetails object
        return new CustomOAuth2User(userGG);
    }
}
