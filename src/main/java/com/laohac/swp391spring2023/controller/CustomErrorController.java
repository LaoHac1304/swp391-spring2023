package com.laohac.swp391spring2023.controller;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorPageRegistrar {

    private static final String ERROR_PATH = "/error";

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, ERROR_PATH + "/404"),
                               new ErrorPage(HttpStatus.FORBIDDEN, ERROR_PATH + "/403"));
    }

    @GetMapping("/error/403")
    public String handle403Error() {
        return "error/page403";
    }
    
    @GetMapping("/error/404")
    public String handle404Error() {
        return "error/page404";
    }
}

