package com.aplikacja.lotywkosmos.controller;


import com.aplikacja.lotywkosmos.model.Flight;
import com.aplikacja.lotywkosmos.model.Tourist;
import com.aplikacja.lotywkosmos.repository.FlightRepository;
import com.aplikacja.lotywkosmos.repository.TouristRepository;
import org.hibernate.SessionFactory;
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

    @GetMapping("/tourists/{id}/flights")
    public List<Flight> getFlightsForTourist(@PathVariable("id") Long id){
        return touristRepository.getTouristById(id).getFlights();
    }


    //nie dziala tak jak powinno
    //dodawanie nowego lotu turyście
    @PostMapping("/tourists/{id}/flights")
    public Tourist addTouristFlights(@PathVariable("id") Long id,
                                          @RequestBody Flight flight){

        Tourist t = touristRepository.findTouristById(id);
        try {
            t.addFlight(flight);
        } catch (Exception e) {
            ResponseEntity.badRequest();
        }

        return t;
    }


    @PostMapping("/tourists")
    public ResponseEntity<?> addTourist(@RequestBody Tourist tourist){
        touristRepository.save(tourist);
        return ResponseEntity.ok(tourist);
    }



    @DeleteMapping("/tourists/{id}")
    public ResponseEntity<?> deleteTourist(@PathVariable("id") Long id){

        Tourist myTourist = touristRepository.getTouristById(id);
        touristRepository.delete(myTourist);

        return ResponseEntity.ok("Tourist deleted!");
    }


    @PatchMapping("tourists/{id}")
    public ResponseEntity<?> changeTourist(@PathVariable("id") Long id,
                                           @RequestParam("name") Optional<String> name,
                                           @RequestParam("lastName") Optional<String> lastName,
                                           @RequestParam("notes") Optional<String> notes
                                            ){

        boolean isAnythingToChangeProvided = name.isPresent() || lastName.isPresent() || notes.isPresent();

        if (isAnythingToChangeProvided){
            Tourist myTourist = touristRepository.findTouristById(id);

            name.ifPresent(myTourist::setName);
            lastName.ifPresent(myTourist::setLastName);
            if (notes.isPresent()){
                myTourist.setNotes(notes.get());
            }

            touristRepository.save(myTourist);
            return ResponseEntity.ok("Tourist changed");
        } else {
            return ResponseEntity.ok("NOTHING TO CHANGE");
        }

    }





}
