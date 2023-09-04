package com.itinerary.itinerary.management.service;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;

public interface IItineraryExampleService<T> {

    public ServiceResponseBean findAll();

    public ServiceResponseBean findById(String uniqueId);

    public ServiceResponseBean add(T itinerary);

    public ServiceResponseBean update(T itinerary);

    public ServiceResponseBean delete(String uniqueId);

}
