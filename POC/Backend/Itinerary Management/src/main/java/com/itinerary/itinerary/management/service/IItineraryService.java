package com.itinerary.itinerary.management.service;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntity;
import com.itinerary.itinerary.management.repository.IItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IItineraryService {

    public ServiceResponseBean findAll();

    public ServiceResponseBean findById(String itineraryUniqueId);

    public ServiceResponseBean addItinerary(ItineraryEntity itineraryEntity);

    public ServiceResponseBean updateItinerary(ItineraryEntity itineraryEntity);

    public ServiceResponseBean deleteItinerary(String itineraryUniqueId);

    public Boolean existByItineraryUniqueId(String itineraryUniqueId);
}
