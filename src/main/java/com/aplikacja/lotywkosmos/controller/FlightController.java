package com.aplikacja.lotywkosmos.controller;


import com.aplikacja.lotywkosmos.model.Flight;
import com.aplikacja.lotywkosmos.model.Tourist;
import com.aplikacja.lotywkosmos.repository.FlightRepository;
import com.aplikacja.lotywkosmos.repository.TouristRepository;
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
    private TouristRepository touristRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository, TouristRepository touristRepository){
        this.flightRepository = flightRepository;
        this.touristRepository = touristRepository;
    }


    @GetMapping("/flights")
    public List<Flight> getFlights(@RequestParam("destination") Optional<String> destination,
                                   @RequestParam("ticketPrice") Optional<Integer> ticketPrice){

        if (destination.isPresent() && ticketPrice.isPresent()){
            return flightRepository.findFlightsByDestinationAndTicketPrice(destination.get(), ticketPrice.get());
        } else if (destination.isPresent()){
            return flightRepository.findFlightsByDestination(destination.get());
        } else if (ticketPrice.isPresent()){
            return flightRepository.findFlightsByTicketPrice(ticketPrice.get());
        } else {
            return flightRepository.findAll();
        }
    }

    @GetMapping("/flights/{id}/tourists")
    public List<Tourist> getFlightPassengers(@PathVariable("id") Long id){
        return flightRepository.findFlightById(id).getPassengers();
    }


    @PostMapping("/flights")
    public ResponseEntity<?> addFlight(@RequestBody Flight flight){
        flightRepository.save(flight);
        return ResponseEntity.ok(flight);
    }

    @PostMapping("/flights/{id}/tourists")
    public Flight addTouristToFlight(@PathVariable("id") Long id,
                                     @RequestBody Tourist tourist){

        Flight f = flightRepository.findFlightById(id);
        try {
            f.addPassengers(tourist);
            flightRepository.save(f);
        } catch (Exception e){
            ResponseEntity.badRequest();
        }


        //touristRepository.findTouristById(tourist.getId());
        /*
        if (!tourist.getFlights().contains(f)){
            tourist.addFlight(f);
            touristRepository.save(tourist);
        }
*/


        return f;
    }


    @DeleteMapping("/flights/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable("id") Long id){

        Flight myFlight = flightRepository.getFlightById(id);
        flightRepository.delete(myFlight);

        return ResponseEntity.ok("LOT USUNIĘTO");
    }


    @PatchMapping("/flights/{id}")
    public ResponseEntity<?> changeFlight(@PathVariable("id") Long id,
                                          @RequestParam("destination") Optional<String> destination,
                                          @RequestParam("takeoffDateTime") Optional<String> takeoffDateTime,
                                          @RequestParam("landingDateTime") Optional<String> landingDateTime,
                                          @RequestParam("seatsAvailable") Optional<Integer> seatsAvailable,
                                          @RequestParam("ticketPrice") Optional<Integer> ticketPrice
                                          ){

        boolean isAnythingToChange = destination.isPresent() || takeoffDateTime.isPresent() || landingDateTime.isPresent() || seatsAvailable.isPresent() || ticketPrice.isPresent();

        if (isAnythingToChange){
            Flight myFlight = flightRepository.findFlightById(id);

            destination.ifPresent(myFlight::setDestination);
            takeoffDateTime.ifPresent(s -> myFlight.setTakeoffDateTime(LocalDateTime.parse(s)));
            landingDateTime.ifPresent(s -> myFlight.setLandingDateTime(LocalDateTime.parse(s)));
            seatsAvailable.ifPresent(myFlight::setSeatsAvailable);
            ticketPrice.ifPresent(myFlight::setTicketPrice);            //functional style expression

            flightRepository.save(myFlight);
            return ResponseEntity.ok("Flight is changed");

        }else{
            return ResponseEntity.ok("There is nothing provided to change");
        }


    }

}
