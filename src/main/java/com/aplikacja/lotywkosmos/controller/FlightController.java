package com.aplikacja.lotywkosmos.controller;


import com.aplikacja.lotywkosmos.model.Flight;
import com.aplikacja.lotywkosmos.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {

    private FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }


    @GetMapping("/flights")
    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }


    @PostMapping("/flights")
    public ResponseEntity<?> addFlight(@RequestBody Flight flight){
        flightRepository.save(flight);
        return ResponseEntity.ok("DODANO LOT");
    }


    @DeleteMapping("/flights/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable("id") Long id){

        Flight myFlight = flightRepository.getFlightById(id);
        flightRepository.delete(myFlight);

        return ResponseEntity.ok("LOT USUNIĘTO");
    }


    @PatchMapping("/flights/{id}")
    public ResponseEntity<?> changeFlight(@PathVariable("id") Long id,
                                          @RequestParam("takeoffDateTime") Optional<String> takeoffDateTime,
                                          @RequestParam("landingDateTime") Optional<String> landingDateTime,
                                          @RequestParam("seatsAvailable") Optional<Integer> seatsAvailable,
                                          @RequestParam("ticketPrice") Optional<Integer> ticketPrice
                                          ){

        boolean isAnythingToChange = takeoffDateTime.isPresent() || landingDateTime.isPresent() || seatsAvailable.isPresent() || ticketPrice.isPresent();

        if (isAnythingToChange){
            Flight myFlight = flightRepository.findFlightById(id);

            takeoffDateTime.ifPresent(s -> myFlight.setTakeoffDateTime(LocalDateTime.parse(s)));
            landingDateTime.ifPresent(s -> myFlight.setLandingDateTime(LocalDateTime.parse(s)));
            seatsAvailable.ifPresent(myFlight::setSeatsAvailable);
            ticketPrice.ifPresent(myFlight::setTicketPrice);            //functional style expression

            flightRepository.save(myFlight);
            return ResponseEntity.ok("Flight is changed");

        }else{
            return ResponseEntity.ok("There is nothing to change");
        }


    }

}
