package com.laohac.swp391spring2023.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.entities.CarCompany;
import com.laohac.swp391spring2023.repository.CarCompanyRepository;
import com.laohac.swp391spring2023.service.CarCompanyService;

@Service
public class CarCompanyServiceImpl implements CarCompanyService{

    @Autowired
    CarCompanyRepository carCompanyRepository;
    @Override
    public CarCompany getCarCompanyById(int id) {
        Optional<CarCompany> optional = carCompanyRepository.findById(id);
        CarCompany carCompany = null;
        if(optional.isPresent()){
            carCompany = optional.get();
        }else{
            throw new RuntimeException("Not found");
        }
        return carCompany;
    }
    
}
