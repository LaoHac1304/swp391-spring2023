package com.laohac.swp391spring2023.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.Status;
import com.laohac.swp391spring2023.model.dto.CheckOutInfoDTOReponse;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.OrderDetail;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.OrderDetailRepository;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.BookingService;
import com.laohac.swp391spring2023.service.MemberService;

@Service
public class BookingServiceImpl implements BookingService {

        @Autowired
        private SeatRepository seatRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private OrderDetailRepository orderDetailRepository;

        @Autowired
        private JavaMailSender mailSender;

        @Autowired
        private MemberService memberService;

        @Override
        public void chooseSeats(int id) {

                Seat seat = seatRepository.findById(id);
                if (seat.getAvailableSeat() == 0) {
                        seat.setAvailableSeat(1);
                        seatRepository.save(seat);
                }
                return;
        }

        @Override
        public void cancelSeat(int id) {
                Seat seat = seatRepository.findById(id);
                if (seat != null) {
                        seat.setAvailableSeat(0);
                        seatRepository.save(seat);
                }
                return;
        }

        @Override
        public CheckOutInfoDTOReponse showCheckOutInfo(List<Integer> listSeats, HttpSession session) {

                if (listSeats.isEmpty())
                        return null;
                UserDTOResponse userDTOResponse = (UserDTOResponse) session.getAttribute("userSession");
                int id = listSeats.get(0);
                Seat seat = seatRepository.findById(id);
                Trip trip = seat.getTrip();
                BigDecimal price = trip.getPrice();
                BigDecimal priceTotal = price.multiply(BigDecimal.valueOf(listSeats.size()));
                // int priceTotal = price*(listSeats.size());
                Boolean isSpecialDay = trip.getIsSpecialDay();
                return CheckOutInfoDTOReponse.builder().trip(trip).lSeats(listSeats).priceTotal(priceTotal)
                                .user(userDTOResponse).isSpecialDay(isSpecialDay).build();

        }

        @Override
        public void saveOrder(HttpSession session, boolean isSaved) {

                UserDTOResponse userCurrent = memberService.getCurrentUser();

                CheckOutInfoDTOReponse checkOutInfoDTOReponse = (CheckOutInfoDTOReponse) session
                                .getAttribute("checkoutinfo");
                User user = new User();

                if (userCurrent != null) {

                        Optional<User> uOptional = userRepository.findByEmail(userCurrent.getEmail());
                        if (uOptional.isPresent()) 
                                user = uOptional.get();
                }
                else user = null;

                CheckOutInfoDTOReponse checkTmpUser = (CheckOutInfoDTOReponse) session.getAttribute("checkoutTmpInfo");
                

                Trip trip = checkOutInfoDTOReponse.getTrip();
                Car car = checkOutInfoDTOReponse.getTrip().getCar();
                int quantity = checkOutInfoDTOReponse.getLSeats().size();
                // Date date = new Date();
                String email = checkTmpUser.getEmail();
                String fullName = checkTmpUser.getFullName();
                String phoneNumber = checkTmpUser.getPhoneNumber();
                BigDecimal total = checkOutInfoDTOReponse.getPriceTotal();
                String departure = trip.getDepartureDetail();
                String arrival = trip.getArrivalDetail();
                String listSeats = checkOutInfoDTOReponse.getLSeats().toString();
                String str = listSeats;
                boolean isSpecialDay = trip.getIsSpecialDay();
                checkOutInfoDTOReponse.setSpecialDay(isSpecialDay);
                session.setAttribute("checkoutinfo", checkOutInfoDTOReponse);

                str = str.replaceAll("[\\[\\]\\s+]", "");

                List<Integer> listSeatsInt = Arrays.stream(str.split(","))
                                .map(String::trim)
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .collect(Collectors.toList());

                List<String> listSeatsNumber = new ArrayList<>();
                for (Integer integer : listSeatsInt) {
                        Seat seat = seatRepository.findById(integer.intValue());
                        if (seat != null) {
                                listSeatsNumber.add(Integer.toString(seat.getSeatNumber()));
                        }
                }

                checkOutInfoDTOReponse.setListSeatNumber(listSeatsNumber);
                session.setAttribute("checkoutinfo", checkOutInfoDTOReponse);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail = OrderDetail
                                .builder()
                                .customer(user)
                                .trip(trip)
                                .car(car)
                                .quantity(quantity)
                                .email(email)
                                .fullName(fullName)
                                .phoneNumber(phoneNumber)
                                .total(total)
                                .departure(departure)
                                .arrival(arrival)
                                .listSeats(listSeats)
                                .listSeatsNumber(String.join(",", listSeatsNumber))
                                .status(checkOutInfoDTOReponse.getStatus())
                                .paymentType(checkOutInfoDTOReponse.getPaymentType())
                                .paymentStatus(checkOutInfoDTOReponse.getPaymentStatus())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build();
                if (!isSaved)
                        orderDetailRepository.save(orderDetail);
                session.setAttribute("orderDetailEmail", orderDetail);

        }

        @Override
        public void sendVerificationEmail(HttpSession session, String siteURL)
                        throws UnsupportedEncodingException, MessagingException {

                UserDTOResponse user = (UserDTOResponse) session.getAttribute("userSession");
                CheckOutInfoDTOReponse checkOutTmp = (CheckOutInfoDTOReponse) session
                                .getAttribute("checkoutTmpInfo");
                CheckOutInfoDTOReponse checkOutInfoDTOReponse = (CheckOutInfoDTOReponse) session
                                .getAttribute("checkoutinfo");
                String fullName = checkOutTmp.getFullName();
                String email = checkOutTmp.getEmail();
                String phoneNumber = checkOutTmp.getPhoneNumber();
                String subject = "About your trip";
                String senderName = "BusGo";

                String mailContent = "<body style=\"background-color: #f4f4f4; max-width: 350px\"><div style=\"background-color: #f4f4f4; padding: 20px; \">"
                  + "<div style=\"background-color: white; border: 1px solid #ddd; border-radius: 5px; overflow: hidden;\">"
                  + "<div style=\"background-color: #55a9ee; color: white; padding: 10px 20px; font-size: 24px; font-weight: bold;\">"
                  + "Bus Ticket"
                  + "</div>"
                  + "<div style=\"padding: 20px;\">"

                  + "<p style=\"font-size: 18px; font-weight: bold; margin-bottom: 10px;\">User Details</p>"
                  + "<p style=\"font-size: 14px; margin: 0;\">"
                  + "Full Name: " + fullName + "<br>"
                  + "Email: " + email + "<br>"
                  + "Phone Number: " + phoneNumber 
                  + "</p>"

                  + "<p style=\"font-size: 18px; font-weight: bold; margin-bottom: 10px;\">Route</p>"
                  + "<p style=\"font-size: 14px; margin: 0;\">" 
                  + checkOutInfoDTOReponse.getTrip().getRoute().getDeparture() 
                  + " - " 
                  + checkOutInfoDTOReponse.getTrip().getRoute().getArrival() 
                  + "</p>"

                  + "<p style=\"font-size: 18px; font-weight: bold; margin-bottom: 10px;\">Departure Place</p>"
                  + "<p style=\"font-size: 14px; margin: 0;\">" 
                  + checkOutInfoDTOReponse.getTrip().getDepartureDetail() 
                  + "</p>"

                  + "<p style=\"font-size: 18px; font-weight: bold; margin-bottom: 10px;\">Start Time</p>"
                  + "<p style=\"font-size: 14px; margin: 0;\">" 
                  + checkOutInfoDTOReponse.getTrip().getStartTime() 
                  + "</p>"

                  + "<p style=\"font-size: 18px; font-weight: bold; margin-bottom: 10px;\">Seat Numbers</p>"
                  + "<p style=\"font-size: 14px; margin: 0;\">" 
                  + checkOutInfoDTOReponse.getListSeatNumber().toString() 
                  + "</p>"
                  
                  + "<p style=\"font-size: 18px; font-weight: bold; margin-bottom: 10px;\">Price Total</p>"
                  + "<p style=\"font-size: 14px; margin: 0; color: #2b6ccd; font-weight: 600;\">" 
                  + checkOutInfoDTOReponse.getPriceTotal().toString() 
                  + " VND" 
                  + "</p>"

                  + "</div>"
                  + "</div>"
                  + "</div></body>";



                MimeMessage message = mailSender.createMimeMessage();

                JavaMailSenderImpl emailSender = (JavaMailSenderImpl) mailSender;
                emailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");

                MimeMessageHelper helper = new MimeMessageHelper(message);

                helper.setFrom("tekato2002@gmail.com", senderName);
                helper.setTo(email);
                helper.setSubject(subject);
                helper.setText(mailContent, true);

                mailSender.send(message);

        }

        @Override
        public boolean cancelBooking(int bookingId) {

                Optional<OrderDetail> canceledOrderDetailOptional = orderDetailRepository.findById(bookingId);
                if (!canceledOrderDetailOptional.isPresent()) return false;
                OrderDetail canceledOrderDetail = canceledOrderDetailOptional.get();
                // get LocaldDateTime of the Trip
                String time = canceledOrderDetail.getTrip().getStartTime();
                LocalDate localDate = canceledOrderDetail.getTrip().getDate();
                LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
                LocalDateTime localDateTime = localDate.atTime(localTime);
                //

                LocalDateTime now = LocalDateTime.now();
                if (localDateTime.isBefore(now.plusDays(1)))
                        return false;

                String str = canceledOrderDetail.getListSeats();
                str = str.replaceAll("[\\[\\]\\s+]", "");
                List<Integer> listSeats = Arrays.stream(str.split(","))
                                .map(String::trim)
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .collect(Collectors.toList());
                for (Integer integer : listSeats) {
                        cancelSeat(integer);
                }
                canceledOrderDetail.setStatus(Status.CANCELLED);
                orderDetailRepository.save(canceledOrderDetail);

                return true;
        }

        @Override
        public List<OrderDetail> getAllBookings() {
                List<OrderDetail> optional = orderDetailRepository.findAll();
                return optional;
                
        }

        @Override
        public List<OrderDetail> getBookingsByStatus(Status bookingStatus) {
                List<OrderDetail> lOrderDetails = new ArrayList<>();
                lOrderDetails = orderDetailRepository.findByStatus(bookingStatus);
                return lOrderDetails;
        }

}
