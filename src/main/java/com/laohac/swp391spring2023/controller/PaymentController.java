package com.laohac.swp391spring2023.controller;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.laohac.swp391spring2023.model.PaymentStatus;
import com.laohac.swp391spring2023.model.PaymentType;
import com.laohac.swp391spring2023.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laohac.swp391spring2023.model.dto.CheckOutInfoDTOReponse;
import com.laohac.swp391spring2023.model.dto.PaypalUserInfo;
import com.laohac.swp391spring2023.model.entities.OrderDetail;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.repository.RouteRepository;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.repository.TripRepository;
import com.laohac.swp391spring2023.service.BookingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingService bookingService;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
       CheckOutInfoDTOReponse checkOutInfoDTOReponse = new CheckOutInfoDTOReponse();
       model.addAttribute("checkoutTmpInfo", checkOutInfoDTOReponse);

    }

    @GetMapping("/{id}")
    public String payment(@PathVariable(value = "id") int id, Model model, HttpSession session){

        List<Integer> listSeats = new ArrayList<>();
        session.setAttribute("SselectedSeats", listSeats);
        Trip trip = new Trip();
        
        trip = tripRepository.findById(id);
        model.addAttribute("tripInfoCurrent", trip);
        List<Seat> seatLists = seatRepository.findByTrip(trip);
        
        model.addAttribute("listSeats",seatLists);
        
        
        return "home/orderForm";
    }


    public static String formatVND(BigDecimal price) {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.forLanguageTag("vi-VN"));
        formatter.setGroupingSize(3);
        formatter.setGroupingUsed(true);
        return formatter.format(price) + " ₫";
    }

    @GetMapping("/list-place")
    public String listPlace(Model model) {

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

        for (Route route : listRoute) {
            System.out.println(route.getArrival());
        }
        return "redirect:/users/search-trip";
    }

    @GetMapping("/detail")
    public String showDetail(){
        return "home/ArrivalDepartureDetail";
    }

    private String getListSeatNumber(String str){
        str = str.replaceAll("[\\[\\]\\s+]", "");

        List<Integer> listSeatsInt = Arrays.stream(str.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        List<String> listSeatsNumber = new ArrayList<>();
        for (Integer integer : listSeatsInt) {
            Seat seat = seatRepository.findById(integer.intValue());
            if (seat!=null){
                listSeatsNumber.add(Integer.toString(seat.getSeatNumber()));
            }
        }
        return String.join(",", listSeatsNumber);
    }

    @GetMapping("/checkout/{id}")
    public String showCheckoutForm(Model model, @PathVariable(value = "id") int id, HttpSession session){

        Trip trip = new Trip();
        
        trip = tripRepository.findById(id);
        model.addAttribute("tripInfoCurrent", trip);

        CheckOutInfoDTOReponse checkOutInfoDTOReponse = (CheckOutInfoDTOReponse) session.getAttribute("checkoutinfo");
        String listSeatNumber = getListSeatNumber(checkOutInfoDTOReponse.getLSeats().toString());
        model.addAttribute("listSeatsNumber", listSeatNumber);
        model.addAttribute("checkoutinfo", checkOutInfoDTOReponse);
        return "home/checkoutForm";
    }

    @GetMapping("/choose-seats/{id}")
    public String chooseSeats(@RequestParam(name = "selectedSeats", required = false) List<Integer> selectedSeats, 
                                        Model model, @PathVariable(value = "id") int id, HttpSession session){

        // for (Integer idd : selectedSeats) {
        //     bookingService.chooseSeats(idd);    
        // }
        //if (!selectedSeats.isEmpty())
            session.setAttribute("SselectedSeats", selectedSeats);
        CheckOutInfoDTOReponse checkOutInfoDTOReponse = bookingService.showCheckOutInfo(selectedSeats, session);
        
        session.setAttribute("checkoutinfo", checkOutInfoDTOReponse);

       return "redirect:/booking/checkout/{id}";
    }

    private String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PostMapping("/save-order")
    public String saveOrder(@RequestParam(name = "paymentMethod") String paymentMethod, 
                                    HttpSession session, Model model, HttpServletRequest request, 
                                    @ModelAttribute("checkoutTmpInfo") CheckOutInfoDTOReponse checkoutTmpInfo) 
                                    throws UnsupportedEncodingException, MessagingException{


        CheckOutInfoDTOReponse checkOutInfoDTOReponse =
                (CheckOutInfoDTOReponse) session.getAttribute("checkoutinfo");
        session.setAttribute("checkoutTmpInfo", checkoutTmpInfo);

        
        
        if(paymentMethod.equals("paypal")) {
            checkOutInfoDTOReponse.setStatus(Status.PENDING);

            checkOutInfoDTOReponse.setPaymentType(PaymentType.PAYPAL);
            checkOutInfoDTOReponse.setPaymentStatus(PaymentStatus.PAID);

            session.setAttribute("checkoutinfo", checkOutInfoDTOReponse);
            bookingService.saveOrder(session, true);
        }
        else {
            checkOutInfoDTOReponse.setStatus(Status.PENDING);
            checkOutInfoDTOReponse.setPaymentType(PaymentType.CASH);
            checkOutInfoDTOReponse.setPaymentStatus(PaymentStatus.PENDING);

            session.setAttribute("checkoutinfo", checkOutInfoDTOReponse);
            bookingService.saveOrder(session, false);
        }
        
        String siteURL = getSiteURL(request);
        bookingService.sendVerificationEmail(session, siteURL);

        if(paymentMethod.equals("paypal")){
            OrderDetail orderDetail = (OrderDetail) session.getAttribute("orderDetailEmail");
            BigDecimal divisor = new BigDecimal("25000");
		    BigDecimal price = orderDetail.getTotal();
            price = price.divide(divisor);

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(date);

            PaypalUserInfo paypalUserInfo = PaypalUserInfo.builder()
                                            .fullName(orderDetail.getFullName())
                                            .date(strDate)
                                            .price(price)
                                            .build();
            session.setAttribute("paypalUserInfo", paypalUserInfo);
            return "redirect:/";
        }


        List<Integer> lSeats = new ArrayList<>();
        lSeats = checkOutInfoDTOReponse.getLSeats();
        for (Integer seat : lSeats) {
             bookingService.chooseSeats(seat);    
         }
         return"redirect:/homepage";
        
    }

    @PostMapping("/cancel-booking")
    public String cancelBooking(@RequestParam("bookingId") int bookingId, Model model, HttpSession session){
        
        boolean ok = true;
        if (!bookingService.cancelBooking(bookingId)) {
            ok = false;
            session.setAttribute("errorMessageCancelBK", ok);
            // model.addAttribute("errorMessageCancelBK",ok);
        }
            

        return "redirect:/users/info";
    }


}