package com.laohac.swp391spring2023.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.service.RouteService;
import com.laohac.swp391spring2023.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route getRouteById(int id) {
        Optional<Route> optional = routeRepository.findById(id);
        Route route;
        if(optional.isPresent()){
            route = optional.get();
        }else{
            throw new RuntimeException("Not found");
        }
        return route;
    }

    @Override
    public void addRoute(Route route){
        routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoute(){
        List<Route> routes = routeRepository.findAll();
        return routes;
    }

    @Override
    public void deleteRouteById(int id) {
        this.routeRepository.deleteById(id);
    }
}
