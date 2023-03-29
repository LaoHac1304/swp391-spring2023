package com.laohac.swp391spring2023.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.laohac.swp391spring2023.model.dto.ProfitDTOReponse;
import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.CarCompany;
import com.laohac.swp391spring2023.model.entities.OrderDetail;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.repository.OrderDetailRepository;
import com.laohac.swp391spring2023.repository.TripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.Provider;
import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.CarCompanyRepository;
import com.laohac.swp391spring2023.repository.MemberRepository;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CarCompanyRepository carCompanyRepository;

    
    @Override
    public MemberDTOReponse authenticate(User memberDTORequest) {
        
        Optional<User> memberOptional = memberRepository.findByUsername(memberDTORequest.getUsername());
        if (memberOptional.isPresent()){
            User member = memberOptional.get();
            if (!member.getPassword().equals(memberDTORequest.getPassword())) return null;
            return MemberDTOReponse.builder()
                                    .username(member.getUsername())
                                    .fullName(member.getFullName())
                                    .name(member.getFullName())
                                    .build();
        }
        return null;
    }

    private boolean checkEnableCarCompany(User user, Set<Integer> lTrips){

        if (user.getCarCompany() == null) return false;
        CarCompany carCompany = user.getCarCompany();
        List<Car> cars = carCompany.getCar();
        if (cars == null) return false;
        
        
        for (Car car : cars) {
            if (lTrips.contains(car.getId()))
                return true;       
        }

        return false;
    }

    @Override
    public List<MemberDTOReponse> getAllMember() {  

        List<User> lUsers = new ArrayList<>();
        List<MemberDTOReponse> lDtoReponses = new ArrayList<>();
        List<Trip> lTrips = tripRepository.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (Trip trip : lTrips) {
            set.add(trip.getCar().getId());    
        }
        lUsers = memberRepository.findAllByRole("employee");
        for (User user : lUsers) {
            boolean isWork = checkEnableCarCompany(user, set);
            MemberDTOReponse memberDTOReponse = MemberDTOReponse
                                                    .builder()
                                                    .id(user.getId())
                                                    .email(user.getEmail())
                                                    .username(user.getUsername())
                                                    .password(user.getPassword())
                                                    .fullName(user.getFullName())
                                                    .sex(user.getSex())
                                                    .phoneNumber(user.getPhoneNumber())
                                                    .role(user.getRole())
                                                    .carCompany(user.getCarCompany())
                                                    .enableToWork(isWork)
                                                    .build();
            lDtoReponses.add(memberDTOReponse);
        }

        return lDtoReponses;
    }
    @Override
    public User getMemberById(int id) {
        Optional<User> optional = memberRepository.findById(id);
        User member = null;
        if(optional.isPresent()){
            member = optional.get();
        }else{
            throw new RuntimeException("Not found");
        }
        return member;
    }
    @Override
    public void deleteMemberById(int id) {
        Optional<User> optional = memberRepository.findById(id);
        if (!optional.isPresent()) return;
        User user = optional.get();
        // CarCompany carCompany = user.getCarCompany();
        // carCompanyRepository.deleteById(carCompany.getId());
        user.setCarCompany(null);
        user.setRole(null);
        user.setPassword(null);
        user = memberRepository.save(user);
        
        
    }

    

    @Override
    public void addMember(User member) {
        member.setRole("employee");
        member.setSex("Male");

        
        String rawPassword = member.getPassword();
        if (rawPassword != null && !rawPassword.isEmpty()) {
        member.setPassword(passwordEncoder.encode(rawPassword));}
        member.setProvider(Provider.LOCAL);
        
        this.memberRepository.save(member);       
    }

    @Override
    public UserDTOResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Optional<User> uOptional = memberRepository.findByUsername(username);
            Optional<User> uOptional2 = userRepository.findByEmail(username);
            User member = null;
            if (uOptional.isPresent())
                member = uOptional.get();
            else
                if (uOptional2.isPresent())
                {
                    member = uOptional2.get();
                }
            int carCompanyId = 0;
            if (member.getCarCompany()!= null){
                carCompanyId = member.getCarCompany().getId();
            }
            // if ((username == null || username.isEmpty()) && (member.getEmail() == null || member.getEmail().isEmpty())){
            //     return null;
            // }
            return UserDTOResponse
            .builder()
            .username(username)
            .fullName(member.getFullName())
            .email(member.getEmail())
            .sex(member.getSex())
            .phoneNumber(member.getPhoneNumber())
            .role(member.getRole())
            .carCompanyId(carCompanyId)
            .provider(member.getProvider().toString())
            .build();
            
        }
        return null;
    }

    @Override
    public void updateMember(User member) {
        String name = member.getFullName();
        String phone = member.getPhoneNumber();
        Optional<User> memberOptional = memberRepository.findById(member.getId());
        if (memberOptional.isPresent()) {
            member = memberOptional.get();
        }
        member.setFullName(name);
        member.setPhoneNumber(phone);
        this.memberRepository.save(member);    
    }

   


}
