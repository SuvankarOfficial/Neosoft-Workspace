package com.itinerary.itinerary.management.controller;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;
import com.itinerary.itinerary.management.service.IItineraryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itinarary-entry")
public class ItineraryEntryController {

    @Autowired
    public IItineraryEntryService iItineraryEntryService;

    @GetMapping("/find-by-id/{itineraryId}")
    public ServiceResponseBean findById(@PathVariable("itineraryId") Long itineraryId) {
        return this.iItineraryEntryService.findById(itineraryId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.iItineraryEntryService.findAll();
    }

    @PostMapping(value="/add")
    public ServiceResponseBean addUser(@RequestBody ItineraryEntryEntity ItineraryEntryEntity) {
        return this.iItineraryEntryService.addItinerary(ItineraryEntryEntity);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateUser(@RequestBody ItineraryEntryEntity ItineraryEntryEntity) {
        return this.iItineraryEntryService.updateItinerary(ItineraryEntryEntity);
    }

    @DeleteMapping("/delete/{userId}")
    public ServiceResponseBean deleteUser(@PathVariable("itineraryId") Long itineraryId) {
        return this.iItineraryEntryService.deleteItinerary(itineraryId);
    }
}
