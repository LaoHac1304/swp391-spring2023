package com.laohac.swp391spring2023.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laohac.swp391spring2023.model.PaypalPaymentIntent;
import com.laohac.swp391spring2023.model.PaypalPaymentMethod;
import com.laohac.swp391spring2023.model.dto.CheckOutInfoDTOReponse;
import com.laohac.swp391spring2023.model.dto.PaypalUserInfo;
import com.laohac.swp391spring2023.service.BookingService;
import com.laohac.swp391spring2023.service.impl.PaypalServiceImpl;
import com.laohac.swp391spring2023.utils.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;


@Controller
public class PaypalController {
	@Autowired
	private BookingService bookingService;
	
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaypalServiceImpl paypalService;
	@GetMapping("/")
	public String index(Model model, HttpSession session){
		CheckOutInfoDTOReponse checkOut = (CheckOutInfoDTOReponse)session.getAttribute("checkoutinfo");
		BigDecimal price = checkOut.getPriceTotal();
		BigDecimal divisor = new BigDecimal("25000");
		price = price.divide(divisor);
		model.addAttribute("price", price);
		PaypalUserInfo paypalUserInfo = (PaypalUserInfo) session.getAttribute("paypalUserInfo");
		model.addAttribute("paypalUserInfo", paypalUserInfo);
		return "home/indexPaypal";
	}
	@PostMapping("/pay")
	public String pay(HttpServletRequest request,@RequestParam("price") double price ){
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(
					price,
					"USD",
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"payment description",
					cancelUrl,
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(){
		return "home/cancel";
	}
	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpSession session){
		try {

			bookingService.saveOrder(session,false);
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				CheckOutInfoDTOReponse checkOutInfoDTOReponse = 
                (CheckOutInfoDTOReponse) session.getAttribute("checkoutinfo");
        		List<Integer> lSeats = new ArrayList<>();
        		lSeats = checkOutInfoDTOReponse.getLSeats();
        		for (Integer seat : lSeats) {
             		bookingService.chooseSeats(seat);    
         }
				return "redirect:/homepage";
			} 
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
}
