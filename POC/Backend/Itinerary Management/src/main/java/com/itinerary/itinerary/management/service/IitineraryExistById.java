package com.itinerary.itinerary.management.service;

public interface IitineraryExistById<T> extends IItineraryExampleService<T>{
    public Boolean existById(String uniqueId);
}
