package com.laohac.swp391spring2023.service;

import java.util.List;

import com.laohac.swp391spring2023.model.entities.Route;

public interface RouteService {

    public Route getRouteById(int id); 

    public void addRoute(Route route);

    public List<Route> getAllRoute();

    public void deleteRouteById(int id);

}
