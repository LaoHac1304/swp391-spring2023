package com.laohac.swp391spring2023.controller;

import java.util.List;
import java.util.Optional;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.laohac.swp391spring2023.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.dto.RouteDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.dto.UserDTOUpdate;
import com.laohac.swp391spring2023.model.entities.OrderDetail;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.OrderDetailRepository;
import com.laohac.swp391spring2023.repository.RouteRepository;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.MemberService;
import com.laohac.swp391spring2023.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeatRepository seatRepository;

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
    public String home(Model model, User user) {
        if (memberService.getCurrentUser() != null) {
            return "home/index";
        }
        model.addAttribute("customer", user);
        return "home/Register";
    }

    @GetMapping("/home")
    public String showUserHome(HttpSession session) {
        // return "home/index";
        session.setAttribute("userSession", memberService.getCurrentUser());
        return "home/index";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        if (memberService.getCurrentUser() != null) {
            return "home/index";
        }
        User user = new User();
        model.addAttribute("user", user);
        return "home/login1";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PostMapping("/save")
    public String register(@ModelAttribute("customer") User user, HttpServletRequest request, HttpSession session)
            throws UnsupportedEncodingException, MessagingException {

        session.setAttribute("currentRegister", null);
        session.setAttribute("errorEmail", null);
        session.setAttribute("errorUsername", null);
        UserDTOResponse userDTOResponse = userService.registerUser(user, session);
        if (userDTOResponse == null)
            return "home/Register";
        String siteURL = getSiteURL(request);
        userService.sendVerificationEmail(userDTOResponse, siteURL);

        System.out.println(userDTOResponse.getFullName());

        return "redirect:/users#popupRegister"; // users#popupRegister
    }

    @GetMapping("/verify")

    public String verifyAccount(@Param("code") String code, Model model, HttpSession session) {
        boolean verified = userService.verify(code, session);

        String pageTitle = verified ? "Verification Succeeded!" : "Verification Failed";
        model.addAttribute("pageTitle", pageTitle);

        return "home/" + (verified ? "verify_success" : "verify_fail");
    }

    @PostMapping("/sign-in")
    public String login(Model model, @ModelAttribute("userInfo") UserDTORequest userDTORequest) {

        UserDTOResponse userDTOResponse = userService.login(userDTORequest);
        System.out.println(userDTOResponse.getFullName());
        return "home/index";
    }

    
    
    @GetMapping("/info")
    public String showInfo(Model model, HttpSession session) {
        Object userCurrent = session.getAttribute("userSession");
        UserDTOResponse userDTOResponse = (UserDTOResponse) userCurrent;

        Optional<User> userOptional = userRepository.findByEmail(userDTOResponse.getEmail());
        User user = new User();
        if (userOptional.isPresent())
            user = userOptional.get();
        Optional<List<OrderDetail>> orderDetailOptional = orderDetailRepository.findByCustomer(user);
        List<OrderDetail> orderDetail = new ArrayList<>();
        if (orderDetailOptional.isPresent())
            orderDetail = orderDetailOptional.get();
        model.addAttribute("orderDetails", orderDetail);
        model.addAttribute("userInfo", userDTOResponse);
        return "home/Profile";
    }

    @GetMapping("/update-profile")
    public String showUpdateForm(Model model, HttpSession session) {

        Object userCurrent = session.getAttribute("userSession");
        UserDTOResponse userDTOResponse = (UserDTOResponse) userCurrent;
        model.addAttribute("userInfo", userDTOResponse);
        return "home/UpdateProfile";
    }

    @PostMapping("/update-userInfo")
    public String update(@ModelAttribute("userInfo") UserDTOUpdate userUpdate, HttpSession session) {
        Object userCurrent = session.getAttribute("userSession");
        UserDTOResponse userDTOResponse = (UserDTOResponse) userCurrent;
        String username = userDTOResponse.getUsername();
        String email = userDTOResponse.getEmail();
        userDTOResponse = userService.update(userUpdate, username, email);
        session.setAttribute("userSession", userDTOResponse);
        return "redirect:/users/info";
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

    @GetMapping("/search-trip")
    public String search(@ModelAttribute("routeDTORequest") RouteDTORequest routeDTORequest, Model model)
            throws ParseException {

        String dateString = routeDTORequest.getDate();
        LocalDate date = LocalDate.parse(dateString);
        model.addAttribute("isSpecialDay", Utils.isSpecialDay(date));
        Route route = routeRepository.findByState1AndState2(routeDTORequest.getState1().toUpperCase(),
                routeDTORequest.getState2().toUpperCase());

        List<Trip> tripsInfo = userService.searchByRouteAndDate(route, date, true);
        
        for (Trip trip : tripsInfo) {
            int total = 0;
            List<Seat> lSeats = new ArrayList<>();
            lSeats = seatRepository.findByTrip(trip);
            if (!lSeats.isEmpty())
                total = lSeats.size();
            for (Seat seat : lSeats) {
                if (seat.getAvailableSeat() == 1) total--;    
            }
            trip.setTotalAvailableSeat(total);   
        }
        model.addAttribute("listTrips", tripsInfo);


        List<Route> listRoute = routeRepository.findAll();
        model.addAttribute("listStates", listRoute);

        model.addAttribute("route", route);
        
        return "home/searchPage";
    }

    /*
     * @GetMapping("/login-google")
     * public String login(Model model, @AuthenticationPrincipal OAuth2User user){
     * 
     * UserDTOResponse userLogin = userService.login(user);
     * model.addAttribute("user", userLogin);
     * 
     * return "index";
     * }
     */

     @GetMapping("/changePassword")
     public String showChangePasswordProfile(Model model, HttpSession session) {
 
         Object userCurrent = session.getAttribute("userSession");
         UserDTOResponse userDTOResponse = (UserDTOResponse) userCurrent;
         model.addAttribute("userInfo", userDTOResponse);
         return "home/ChangePasswordProfile";
     }
}
