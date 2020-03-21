package com.aplikacja.lotywkosmos.repository;

import com.aplikacja.lotywkosmos.model.Flight;
import com.aplikacja.lotywkosmos.model.Tourist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristRepository extends CrudRepository<Tourist, Long> {

    List<Tourist> findAll();

    List<Tourist> findTouristsByName(String name);

    List<Tourist> findTouristsByLastName(String lastName);

    List<Tourist> findTouristsByNameAndLastName(String name, String lastName);

    List<Tourist> findTouristsByCountry(String country);

    List<Tourist> findTouristsByFlight(String flight);

    Tourist save(Tourist tourist);
}
