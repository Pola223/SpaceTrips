package com.aplikacja.lotywkosmos.repository;


import com.aplikacja.lotywkosmos.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findAll();

    
}
