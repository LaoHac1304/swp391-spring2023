package com.laohac.swp391spring2023.service;

import java.util.List;

import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.dto.RouteDTO;

public interface RouteService {

    public Route getRouteById(int id); 

    public void addRoute(Route route);

    public List<RouteDTO> getAllRoute();
}
