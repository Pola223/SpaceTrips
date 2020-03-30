package com.aplikacja.lotywkosmos.repository;

import com.aplikacja.lotywkosmos.model.Flight;
import com.aplikacja.lotywkosmos.model.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {

    private TouristRepository touristRepository;
    private FlightRepository flightRepository;

    @Autowired
    public DataLoader(TouristRepository touristRepository, FlightRepository flightRepository){
        this.touristRepository = touristRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Tourist t1 = new Tourist("Elon", "Musk", "M", "RPA", LocalDate.of(1971, 6,28));
        Tourist t2 = new Tourist("Kinga", "Stanislawska", "F", "Polska", LocalDate.of(1980,9,9));
        Tourist t3 = new Tourist("Matt", "Harasymczuk", "M", "Polska", LocalDate.of(1980,9,9));
        touristRepository.save(t1);
        touristRepository.save(t2);
        touristRepository.save(t3);

        Flight f1 = new Flight("Mars", LocalDateTime.of(2030,9,9,12,12), LocalDateTime.of(2030,11,11,12,12),30,100_000);
        Flight f2 = new Flight("Venus", LocalDateTime.of(2031,9,9,12,12), LocalDateTime.of(2031,11,11,12,12),12,200_000);
        Flight f3 = new Flight("Pluto", LocalDateTime.of(2034,9,9,12,12), LocalDateTime.of(2034,11,11,12,12),50,500_000);
        flightRepository.save(f1);
        flightRepository.save(f2);
        flightRepository.save(f3);

    }

}
