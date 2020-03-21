package com.aplikacja.lotywkosmos.controller;


import com.aplikacja.lotywkosmos.model.Flight;
import com.aplikacja.lotywkosmos.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController {

    private FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }

}
