package com.itinerary.itinerary.management.controller;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntity;
import com.itinerary.itinerary.management.service.IItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itinarary")
public class ItineraryController{

    @Autowired
    public IItineraryService itineraryService;

    @GetMapping("/find-by-id/{itineraryId}")
    public ServiceResponseBean findById(@PathVariable("itineraryId") Long itineraryId) {
        return this.itineraryService.findById(itineraryId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.itineraryService.findAll();
    }

    @PostMapping(value="/add")
    public ServiceResponseBean addUser(@RequestBody ItineraryEntity itineraryEntity) {
        return this.itineraryService.addItinerary(itineraryEntity);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateUser(@RequestBody ItineraryEntity itineraryEntity) {
        return this.itineraryService.updateItinerary(itineraryEntity);
    }

    @DeleteMapping("/delete/{userId}")
    public ServiceResponseBean deleteUser(@PathVariable("itineraryId") Long itineraryId) {
        return this.itineraryService.deleteItinerary(itineraryId);
    }
}
