package com.aplikacja.lotywkosmos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime takeoffDateTime;
    private LocalDateTime landingDateTime;
    private Integer seatsAvailable;
    private Integer ticketPrice;

    public Flight() {
    }

    public Flight(String takeoffDateTime, LocalDateTime landingDateTime, int seatsAvailable, Integer ticketPrice){
        this.takeoffDateTime = LocalDateTime.parse(takeoffDateTime);
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
}
