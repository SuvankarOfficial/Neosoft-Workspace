package com.itinerary.itinerary.management.service;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IItineraryEntryService {

    public ServiceResponseBean findAll();

    public ServiceResponseBean findById(String itineraryEntryUniqueId);

    public ServiceResponseBean addItineraryEntry(ItineraryEntryEntity itineraryEntryEntity);

    public ServiceResponseBean updateItineraryEntry(ItineraryEntryEntity itineraryEntryEntity);

    public ServiceResponseBean deleteItineraryEntry(String itineraryUniqueId);

}
