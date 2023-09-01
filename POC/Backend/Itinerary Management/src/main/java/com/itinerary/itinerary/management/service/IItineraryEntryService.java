package com.itinerary.itinerary.management.service;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;

public interface IItineraryEntryService {

    public ServiceResponseBean findAll();

    public ServiceResponseBean findById(Long itineraryEntryId);

    public ServiceResponseBean addItinerary(ItineraryEntryEntity itineraryEntryEntity);

    public ServiceResponseBean updateItinerary(ItineraryEntryEntity itineraryEntryEntity);

    public ServiceResponseBean deleteItinerary(Long itineraryId);

}
