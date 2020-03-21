package com.aplikacja.lotywkosmos.controller;


import com.aplikacja.lotywkosmos.model.Tourist;
import com.aplikacja.lotywkosmos.repository.FlightRepository;
import com.aplikacja.lotywkosmos.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TouristController {

    private TouristRepository touristRepository;
    private FlightRepository flightRepository;

    @Autowired
    public TouristController(TouristRepository touristRepository, FlightRepository flightRepository){
        this.touristRepository = touristRepository;
        this.flightRepository = flightRepository;
    }


    @GetMapping
    public List<Tourist> getTourists(
            @RequestParam("name") Optional<String> name,
            @RequestParam("lastName") Optional<String> lastName){

        if (name.isPresent() && lastName.isPresent()){
            return touristRepository.findTouristsByNameAndLastName(name.get(), lastName.get());
        } else if (name.isPresent() && !lastName.isPresent()){
            return touristRepository.findTouristsByName(name.get());
        } else if (!name.isPresent() && lastName.isPresent()){
            return touristRepository.findTouristsByLastName(lastName.get());
        } else {
            return touristRepository.findAll();
        }

    }


    @PostMapping("/tourists")
    public ResponseEntity<?> addTourist(@RequestBody Tourist tourist){
        touristRepository.save(tourist);
        return ResponseEntity.ok(tourist);
    }





}
