package com.laohac.swp391spring2023.service.impl;



import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.config.CustomOAuth2User;
import com.laohac.swp391spring2023.model.Provider;
import com.laohac.swp391spring2023.model.dto.UserDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.dto.UserDTOUpdate;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.TripRepository;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder2;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private JavaMailSender mailSender;

    private boolean validatied(User user, HttpSession session){

        boolean ok_username = true;
        boolean ok_email = true;
        if (userRepository.findByEmail(user.getEmail()).isPresent()) 
        {
            session.setAttribute("errorEmail", " * Email already exists");
            ok_email = false;
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {

            session.setAttribute("errorUsername", " * Username already exists");
            ok_username = false;
        }

        if (!userRepository.findByEmail(user.getEmail()).isPresent()) 
        {
            session.setAttribute("errorEmail", null);
            ok_email = true;
        }
        if (!userRepository.findByUsername(user.getUsername()).isPresent()) {

            session.setAttribute("errorUsername", null);
            ok_username = true;
        }
        
        if (!ok_username || !ok_email) return false;
        return true;
    }

    @Override
    public UserDTOResponse registerUser(User user, HttpSession session) {
        
        // validation user
        
        // successful validation
        if (!validatied(user, session)) return null;
        user.setRole("customer");
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder2.encode(rawPassword));
        user.setProvider(Provider.LOCAL);
        user.setEnabled(0);

        String randomCode = RandomString.make(64); 
        user.setVerificationCode(randomCode); 
        session.setAttribute("currentRegister", user);     
        //userRepository.save(user);

        


        UserDTOResponse userDTOResponse = UserDTOResponse
                                .builder()
                                .fullName(user.getFullName())
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .role("customer")
                                .enabled(user.getEnabled())
                                .verificationCode(user.getVerificationCode())
                                .build();
        return userDTOResponse;
    }

    @Override
    @Transactional
    public boolean verify(String verificationCode, HttpSession session){
        //User user = userRepository.findByVerificationCode(verificationCode);
        User user = (User)session.getAttribute("currentRegister");
        if (user == null || user.getEnabled() > 0) return false;
        user.setVerificationCode(null);
        user.setEnabled(1);
        userRepository.save(user);
        //userRepository.enable(user.getId());
        return true;
        
    }

    @Override
    public void sendVerificationEmail(UserDTOResponse user, String siteURL) 
            throws UnsupportedEncodingException, MessagingException {
        String subject ="Please verify your registration";
        String senderName = "4Boys Team";
        String mailContent = "<p>Dear " + user.getFullName() + ",</p>";
        mailContent += "<p>Please click the link below to verify to your registration:</p>";
        
        String verifyURL = siteURL + "/users/verify?code=" + user.getVerificationCode();
        mailContent += "<h3><a href=\"" + verifyURL + "\">VERIFY</a></h3>";
        
        mailContent += "<p>Thank you<br>4Boys Team</p>";

        MimeMessage message = mailSender.createMimeMessage();

        JavaMailSenderImpl emailSender = (JavaMailSenderImpl) mailSender;
        emailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("simnhankid13042002@gmail.com", senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        

        mailSender.send(message);
    }

    @Override
    public UserDTOResponse login(UserDTORequest userDTORequest) {
        
        // authenticated
        String username = userDTORequest.getUsername();
        String password = userDTORequest.getPassword();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (!user.getPassword().equals(password)) return null;
            UserDTOResponse userDTOResponse = UserDTOResponse
                                                .builder()
                                                .email(user.getEmail())
                                                .username(user.getUsername())
                                                .fullName(user.getFullName())
                                                .phoneNumber(user.getPhoneNumber())
                                                .sex(user.getSex())
                                                .build();
            return userDTOResponse;
        }

        return null;
    }

    @Override
    public UserDTOResponse getUserInfo(String username) {
        
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()){
            return null;
        }
        User user = userOptional.get();
        return UserDTOResponse
                        .builder()
                        .username(username)
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .phoneNumber(user.getPhoneNumber())
                        .sex(user.getSex())               
                        .build();
    }

    @Override
    public UserDTOResponse authenticated(UserDTORequest userDTORequest) {
        // authenticated
        String username = userDTORequest.getUsername();
        String password = userDTORequest.getPassword();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (!user.getPassword().equals(password)) return null;
            UserDTOResponse userDTOResponse = UserDTOResponse
                                                .builder()
                                                .email(user.getEmail())
                                                .username(user.getUsername())
                                                .fullName(user.getFullName())
                                                .phoneNumber(user.getPhoneNumber())
                                                .sex(user.getSex())
                                                .role(user.getRole())
                                                .build();
            return userDTOResponse;
        }

        return null;
    }


    @Override
    public UserDTOResponse update(UserDTOUpdate userUpdate, String username, String email) {
        
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent())
            userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setFullName(userUpdate.getFullName());
            user.setPhoneNumber(userUpdate.getPhoneNumber());
            user.setSex(userUpdate.getSex());
            userRepository.save(user);


            UserDTOResponse userDTOResponse = UserDTOResponse
                                                .builder()
                                                .email(user.getEmail())
                                                .username(user.getUsername())
                                                .fullName(user.getFullName())
                                                .phoneNumber(user.getPhoneNumber())
                                                .sex(user.getSex())
                                                .role(user.getRole())
                                                .build();
            return userDTOResponse;
        }

        return null;
    }

    @Override
    public UserDTOResponse getCurrentUser() {
        
        return null;
    }

    @Override
    public boolean checkEmailExisted(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) return true;
        return false;
    }

    @Override
    public void createUserByEmail(CustomOAuth2User oAuth2User) {
        
        String email = oAuth2User.getEmail();
        String fullName = oAuth2User.getFullName();

        User user = User.builder().email(email).fullName(fullName).role("customer").provider(Provider.GOOGLE).build();

        userRepository.save(user);
        
    }

    @Override
    public List<Trip> search(Route route) {
        
        List<Trip> trips = tripRepository.findByRoute(route);
        return trips;
    
    }

    @Override
    public List<Trip> searchByRouteAndDate(Route route, LocalDate date, Boolean isEnable) {
        List<Trip> trips = tripRepository.findByRouteAndDateAndIsEnable(route, date, isEnable);
        return trips;
       
    }

    @Override
    public List<Trip> searchByRouteAndDateByPriceDesc(Route route, LocalDate date) {
        
        List<Trip> trips = tripRepository.findByRouteAndDateOrderByPriceDesc(route, date);
        return trips;
       
    }

    @Override
    public List<Trip> searchByRouteAndDateByPriceAsc(Route route, LocalDate date) {
        
        List<Trip> trips = tripRepository.findByRouteAndDateOrderByPriceAsc(route, date);
        return trips;
       
    }

    @Override
    public List<Trip> searchByRouteAndDateByStartTimeDesc(Route route, LocalDate date) {
        
        List<Trip> trips = tripRepository.findByRouteAndDateOrderByStartTimeDesc(route, date);
        return trips;
       
    }

    @Override
    public List<Trip> searchByRouteAndDateByStartTimeAsc(Route route, LocalDate date) {
        
        List<Trip> trips = tripRepository.findByRouteAndDateOrderByStartTimeAsc(route, date);
        return trips;
       
    }
    }




