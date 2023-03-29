package com.laohac.swp391spring2023.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laohac.swp391spring2023.model.dto.RouteDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.RouteRepository;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.MemberService;

@Controller
@RequestMapping("/homepage")
public class HomeController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        RouteDTORequest routeDTORequest = new RouteDTORequest();
        model.addAttribute("routeDTORequest", routeDTORequest);

        List<Route> listRoute = routeRepository.findAll();
        model.addAttribute("listStates", listRoute);

        Set<String> listState1 = new HashSet<>();
        Set<String> listState2 = new HashSet<>();

        for (Route route : listRoute) {
            listState1.add(route.getDeparture());
            listState2.add(route.getArrival());
        }
        model.addAttribute("departure", listState1);
        model.addAttribute("arrival", listState2);

    }

    @GetMapping("")
    public String showHomePage(Model model) {

        RouteDTORequest routeDTORequest = new RouteDTORequest();
        model.addAttribute("routeDTORequest", routeDTORequest);

        return "home/index";
    }

    @GetMapping("/adminDashBord")
    public String showAdminDashBoard(Model model) {
        model.addAttribute("listMembers", memberService.getAllMember());
        return "adminDashboard/Adashboard";
    }

    @GetMapping("/customerDashBord")
    public String showCustomerDashBoard() {
        return "home/index";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();

        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/homepage";
        // return "homepage/login";
    }

    @GetMapping("/orderForm")
    public String showOrderForm() {

        return "home/orderForm";
    }

    // @PostMapping("/sign-in")
    // public String login(HttpSession session ,Model model, @ModelAttribute("user")
    // UserDTORequest userDTORequest){

    // UserDTOResponse userDTOResponse = userService.authenticated(userDTORequest);
    // if (userDTOResponse == null) return "home/login";
    // session.setAttribute("userSession", userDTOResponse);
    // if (userDTOResponse.getRole().equals("admin")){
    // //return "adminDashboard/Adashboard";
    // return "redirect:/member/viewall";
    // }
    // return "home/index";
    // }

    @GetMapping("/verify_success")
    public String showSuccess() {

        return "home/verify_success";
    }

    @GetMapping("/verify_fail")
    public String showFailure() {

        return "home/verify_fail";
    }

    @GetMapping("/defaultSuccessUrl")
    public String showHomePage(HttpServletRequest request, Model model) {
        UserDTOResponse userDTOResponse = memberService.getCurrentUser();
        if (userDTOResponse.getRole().equals("admin"))
            return "redirect:/member/adminDB";
        else if (userDTOResponse.getRole().equals("customer")){

            return "redirect:/users/home";
        }
            
        else
            return "redirect:/route/viewall";

    }

    

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model) {
        return "home/forgotPassword";
    }

    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(@RequestParam("email") String email, Model model) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            model.addAttribute("error", "Email not found");
            return "redirect:/homepage/forgot-password";
        }
        User user = userOptional.get();

        int code = (int) (Math.random() * 900000) + 100000;

        user.setResetCode(code);
        userRepository.save(user);

        // Send email to user
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset Password");
        message.setText("Your verification code is " + code);
        javaMailSender.send(message);

        model.addAttribute("emailSent", true);
        return "/home/forgotPassword";
    }

    @PostMapping("/reset-password")
    public String processResetPasswordForm(@RequestParam("email") String email, @RequestParam("code") String code,

            @RequestParam("newPassword") String password, @RequestParam("confirmPassword") String confirmPassword,
            Model model) {


        if (password == null || password.isEmpty()) {
            model.addAttribute("error", "Password cannot be empty");
            return "/home/forgotPassword";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "/home/forgotPassword";
        }

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            model.addAttribute("error", "Email not found");
            return "/home/forgotPassword";
        }

        int resetCode;
        try {
            resetCode = Integer.parseInt(code);
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid code");
            return "/home/forgotPassword";
        }

        if (user.getResetCode() != resetCode) {
            model.addAttribute("error", "Invalid code");
            return "/home/forgotPassword";
        }

        // Update the user's password and reset code
        user.setPassword(password);
        user.setResetCode(0);
        userRepository.save(user);

        model.addAttribute("passwordChanged", true);
        return "redirect:/login";
    }

}
