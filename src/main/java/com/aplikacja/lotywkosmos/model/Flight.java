package com.aplikacja.lotywkosmos.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    private Long id;

    private String destination;
    private LocalDateTime takeoffDateTime;
    private LocalDateTime landingDateTime;
    private Integer seatsAvailable;
    private Integer ticketPrice;

    //@ManyToMany
    @ElementCollection
    List<Tourist> passengers = new ArrayList<>();


    public Flight() {
    }

    public Flight(String destination, LocalDateTime takeoffDateTime, LocalDateTime landingDateTime, Integer seatsAvailable, Integer ticketPrice){
        this.destination = destination;
        this.takeoffDateTime = takeoffDateTime;
        this.landingDateTime = landingDateTime;
        this.seatsAvailable = seatsAvailable;
        this.ticketPrice = ticketPrice;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public LocalDateTime getTakeoffDateTime() {
        return takeoffDateTime;
    }

    public void setTakeoffDateTime(LocalDateTime takeoffDateTime) {
        this.takeoffDateTime = takeoffDateTime;
    }


    public LocalDateTime getLandingDateTime() {
        return landingDateTime;
    }

    public void setLandingDateTime(LocalDateTime landingDateTime) {
        this.landingDateTime = landingDateTime;
    }


    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }


    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


    public List<Tourist> getPassengers() {
        return passengers;
    }

    public void addPassengers(Tourist tourist) {
        passengers.add(tourist);
    }

}
