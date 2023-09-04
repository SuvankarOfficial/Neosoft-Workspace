package com.itinerary.itinerary.management.controller;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;
import com.itinerary.itinerary.management.service.IItineraryExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itinerary-entry")
public class ItineraryEntryController {

    @Autowired
    @Qualifier("itinerary-entry-service")
    public IItineraryExampleService itineraryEntryService;

    @GetMapping("/find-by-id/{ItineraryEntryUniqueId}")
    public ServiceResponseBean findById(@PathVariable("ItineraryEntryUniqueId") String ItineraryEntryUniqueId) {
        return this.itineraryEntryService.findById(ItineraryEntryUniqueId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.itineraryEntryService.findAll();
    }

    @PostMapping("/add")
    public ServiceResponseBean addUser(@RequestBody ItineraryEntryEntity ItineraryEntryEntity) {
        return this.itineraryEntryService.add(ItineraryEntryEntity);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateUser(@RequestBody ItineraryEntryEntity ItineraryEntryEntity) {
        return this.itineraryEntryService.update(ItineraryEntryEntity);
    }

    @DeleteMapping("/delete/{ItineraryEntryUniqueId}")
    public ServiceResponseBean deleteUser(@PathVariable("ItineraryEntryUniqueId") String ItineraryEntryUniqueId) {
        return this.itineraryEntryService.delete(ItineraryEntryUniqueId);
    }
}
