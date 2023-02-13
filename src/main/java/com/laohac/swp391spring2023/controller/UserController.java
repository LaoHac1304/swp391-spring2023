package com.laohac.swp391spring2023.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.dto.UserDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.dto.UserDTOUpdate;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String home(Model model, User user){
        model.addAttribute("customer", user);
        return "home/Register";
    }

    @PostMapping("/save")
    public String register (@ModelAttribute("customer") @Valid User user, BindingResult bindingResult){
        // if(userService.userExists(user.getEmail())){
        //     bindingResult.addError(new FieldError("user", "email", "Email is already exist"));
        // }
        if(bindingResult.hasErrors()){
            return "";
        }
        else{
        UserDTOResponse userDTOResponse = userService.registerUser(user);
        System.out.println(userDTOResponse.getFullName());
        return "home/index";
        }
    }


    @PostMapping("/sign-in")
    public String login(Model model, @ModelAttribute("userInfo") UserDTORequest userDTORequest){

        UserDTOResponse userDTOResponse = userService.login(userDTORequest);
        System.out.println(userDTOResponse.getFullName());
        return "home/index";
    }

    @GetMapping("/info")
    public String showInfo(Model model, HttpSession session){
        Object userCurrent = session.getAttribute("userSession");
        UserDTOResponse userDTOResponse = (UserDTOResponse) userCurrent;
        model.addAttribute("userInfo", userDTOResponse);
        return "home/Profile";
    }

    @GetMapping("/update-profile")
    public String showUpdateForm(Model model, HttpSession session){

        Object userCurrent = session.getAttribute("userSession");
        UserDTOResponse userDTOResponse = (UserDTOResponse) userCurrent;
        model.addAttribute("userInfo", userDTOResponse);
        return "home/UpdateProfile";
    }

    @PostMapping("/update-userInfo")
    public String update(@ModelAttribute("userInfo") UserDTOUpdate userUpdate, HttpSession session){
        Object userCurrent = session.getAttribute("userSession");
        UserDTOResponse userDTOResponse = (UserDTOResponse) userCurrent;
        String username = userDTOResponse.getUsername();
        userDTOResponse = userService.update(userUpdate, username);
        session.setAttribute("userSession", userDTOResponse);
        return "redirect:/users/info";
    }

    
    /*@GetMapping("/login-google")
    public String login(Model model, @AuthenticationPrincipal OAuth2User user){

        UserDTOResponse userLogin = userService.login(user);
        model.addAttribute("user", userLogin);

        return "index";
    }*/

    
}
