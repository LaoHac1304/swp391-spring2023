package com.laohac.swp391spring2023.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.dto.RouteDTORequest;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.RouteRepository;
import com.laohac.swp391spring2023.service.MemberService;

@Controller
@RequestMapping("/homepage")
public class HomeController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RouteRepository routeRepository;

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

        return "/homepage";
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
}
