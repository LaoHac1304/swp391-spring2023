package com.laohac.swp391spring2023.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.laohac.swp391spring2023.model.dto.ProfitDTOReponse;
import com.laohac.swp391spring2023.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.Month;
import com.laohac.swp391spring2023.model.dto.MemberViewDTOReponse;
import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.CarCompany;
import com.laohac.swp391spring2023.model.entities.OrderDetail;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.CarCompanyRepository;
import com.laohac.swp391spring2023.repository.CarRepository;
import com.laohac.swp391spring2023.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CarCompanyRepository carCompanyRepository;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/adminDB")
    public String showAdminDB(HttpSession session, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated())
            session.setAttribute("userSession", memberService.getCurrentUser());
        // return "adminDashboard/Adashboard";
        return "redirect:/member/viewall";
    }

    @GetMapping("/login")
    public String showAdminLogin(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/member/adminDB";
        }
        return "home/login";
    }

    @GetMapping("/viewall")
    public String viewAllMember(Model model) {

        int employees = 0;
        int carCompanies = 0;
        // int orders = 0;
        int cars = 0;

        List<User> listUsers = memberService.getAllMember();
        List<CarCompany> listCarCompanies = carCompanyRepository.findAll();
        List<Car> listCars = carRepository.findAll();

        if (!listUsers.isEmpty())
            employees = listUsers.size();
        if (!listCarCompanies.isEmpty())
            carCompanies = listCarCompanies.size();
        if (!listCars.isEmpty())
            cars = listCars.size();

        MemberViewDTOReponse memberViewDTOReponse = MemberViewDTOReponse.builder()
                .employees(employees)
                .carCompanies(carCompanies)
                .cars(cars)
                .build();

        MemberViewDTOReponse.builder().employees(employees).carCompanies(carCompanies).cars(cars).build();

        Map<String, ProfitDTOReponse> wrapper = new HashMap<>();

        for (Month month : Month.values()) {
            ProfitDTOReponse profitDTOReponse = getProfitWithMonth(month.getMonthName());
            wrapper.put(month.getMonthName(), profitDTOReponse);
        }

        model.addAttribute("total", memberViewDTOReponse);

        model.addAttribute("listMembers", listUsers);

        model.addAttribute("profit", wrapper);

        return "adminDashboard/Adashboard";
    }

    @GetMapping("/add")
    public String addMember(Model model) {
        User member = new User();
        model.addAttribute("member", member);

        return "adminDashboard/addEmployee";
    }

    @GetMapping("/save")
    public String saveMember(@ModelAttribute("member") User member) {
        memberService.addMember(member);
        return "redirect:/member/viewall";
    }

    @GetMapping("/update/{id}")
    public String updateMember(@PathVariable(value = "id") int id, Model model) {
        User member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "adminDashboard/updateEmployee";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable(value = "id") int id) {
        this.memberService.deleteMemberById(id);
        return "redirect:/member/viewall";
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
    }

    @GetMapping("/setting")
    public String setting() {
        return "adminDashboard/setting";
    }

    
    private ProfitDTOReponse getProfitWithMonth(String month){
        
        List<OrderDetail> orderDetails = orderDetailRepository.findByStatus("CONFIRMED");
       
        BigDecimal totalPrice = new BigDecimal(0);
        int totalTicket = 0;
        for (OrderDetail orderDetail : orderDetails) {
                LocalDate date = orderDetail.getDate();
                if (date.getMonth().name().equalsIgnoreCase(month)){
                    totalPrice.add(orderDetail.getTotal());
                    totalTicket ++ ;
                }
        }
        ProfitDTOReponse profitDTOReponse = ProfitDTOReponse
                                            .builder()
                                            .month(month)
                                            .totalPrice(totalPrice)
                                            .totalTicket(totalTicket)
                                            .build();
        return profitDTOReponse;
        
    }
}
