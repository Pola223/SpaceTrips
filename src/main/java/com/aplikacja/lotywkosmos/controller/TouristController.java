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


    @GetMapping("/tourists")
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


    @DeleteMapping("/tourists/{id}")
    public ResponseEntity<?> deleteTourist(@PathVariable("id") Long id){
        touristRepository.deleteTouristById(id);
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("tourists/{id}")
    public ResponseEntity<?> changeNameTourist(@PathVariable("id") Long id,
                                               @RequestParam("name") Optional<String> name,
                                               @RequestParam("lastName") Optional<String> lastName,
                                               @RequestParam("notes") Optional<String> notes
                                            ){

        boolean isAnythingToChangeProvided = name.isPresent() || lastName.isPresent() || notes.isPresent();

        if (isAnythingToChangeProvided){
            Tourist myTourist = touristRepository.findTouristById(id);
            if (name.isPresent()){
                myTourist.setName(name.toString().substring(9).replace("]",""));
            }
            if (lastName.isPresent()) {
                myTourist.setName(lastName.toString().substring(9).replace("]",""));
            }
            if (notes.isPresent()){
                myTourist.setName(notes.toString().substring(9).replace("]",""));
            }
            touristRepository.save(myTourist);
            return ResponseEntity.ok("Tourist changed");
        } else {
            return ResponseEntity.ok("NOTHING TO CHANGE");
        }


    }



}
