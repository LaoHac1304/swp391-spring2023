package com.laohac.swp391spring2023;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.laohac.swp391spring2023.service.MemberService;

@SpringBootApplication
public class Swp391Spring2023Application {

	public static void main(String[] args) {
		SpringApplication.run(Swp391Spring2023Application.class, args);
	}

	/*@Bean
	CommandLineRunner run(MemberService memberService){
		return args -> {
			memberService.addMember(new Member(0, "Admin", "AdminNhan", "05233", "itachi", "123"));	
		}; 
	};*/

}

