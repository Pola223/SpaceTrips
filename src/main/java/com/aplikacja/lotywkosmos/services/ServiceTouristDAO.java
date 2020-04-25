package com.aplikacja.lotywkosmos.services;

import com.aplikacja.lotywkosmos.model.Tourist;
import com.aplikacja.lotywkosmos.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTouristDAO {

    private TouristRepository touristRepository;

    @Autowired
    public ServiceTouristDAO (TouristRepository touristRepository){
        this.touristRepository = touristRepository;
    }

    public List<Tourist> getAllTourists(Integer id){
        List<Tourist> tourists = new ArrayList<>();
        this.touristRepository.findAll().forEach(tourists::add);
        return tourists;
    }


}
