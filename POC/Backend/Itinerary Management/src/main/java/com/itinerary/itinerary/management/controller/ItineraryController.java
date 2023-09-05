package com.itinerary.itinerary.management.controller;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntity;
import com.itinerary.itinerary.management.service.IItineraryExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController{

    @Autowired
    public IItineraryExampleService itineraryService;

    @GetMapping("/find-by-id/{itineraryUniqueId}")
    public ServiceResponseBean findById(@PathVariable("itineraryUniqueId") String itineraryUniqueId) {
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(this.itineraryService.findById(itineraryUniqueId)).build();
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.itineraryService.findAll();
    }

    @PostMapping("/add")
    public ServiceResponseBean addUser(@RequestBody ItineraryEntity itineraryEntity) {
        return this.itineraryService.add(itineraryEntity);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateUser(@RequestBody ItineraryEntity itineraryEntity) {
        return this.itineraryService.update(itineraryEntity);
    }

    @DeleteMapping("/delete/{itineraryUniqueId}")
    public ServiceResponseBean deleteUser(@PathVariable("itineraryUniqueId") String itineraryUniqueId) {
        return this.itineraryService.delete(itineraryUniqueId);
    }
}
