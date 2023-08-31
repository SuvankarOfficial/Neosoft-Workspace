package com.travel.experience.listing.controller;

import com.travel.experience.listing.bean.response.ServiceResponseBean;
import com.travel.experience.listing.entity.ExperienceListingEntity;
import com.travel.experience.listing.service.IExperienceListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/experience-listing")
public class ExperienceListingController {

    @Autowired
    private IExperienceListingService experienceListingService;

    @GetMapping("/find-by-id/{experienceListingId}")
    public ServiceResponseBean findById(@PathVariable("experienceListingId") Long experienceListingId) {
        return this.experienceListingService.findById(experienceListingId);
    }

    @GetMapping("/download-image/{experienceListingId}")
    public ResponseEntity downloadImage(@PathVariable("experienceListingId") Long experienceListingId) {
        return this.experienceListingService.downloadImage(experienceListingId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.experienceListingService.findAll();
    }

    @PostMapping(value="/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ServiceResponseBean addExperienceListing(@RequestPart("experienceListing") ExperienceListingEntity experienceListing, @RequestPart("image") MultipartFile multipartFile) throws IOException {
        return this.experienceListingService.addExperienceListing(experienceListing,multipartFile);
    }

    @PutMapping("/update-with-image")
    public ServiceResponseBean updateExperienceListingWithImage(@RequestPart("experienceListing") ExperienceListingEntity experienceListing,@RequestPart("image") MultipartFile multipartFile) throws IOException {
        return this.experienceListingService.updateExperienceListing(experienceListing,multipartFile);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateExperienceListing(@RequestBody ExperienceListingEntity experienceListing) throws IOException {
        return this.experienceListingService.updateExperienceListing(experienceListing,null);
    }

    @DeleteMapping("/delete/{experienceListingId}")
    public ServiceResponseBean deleteExperienceListing(@PathVariable("experienceListingId") Long experienceListingId) {
        return this.experienceListingService.deleteExperienceListing(experienceListingId);
    }

    @GetMapping("/check-if-exist/{experienceListingUniqueId}")
    public Boolean existByExperienceListingUniqueId(@PathVariable("experienceListingUniqueId") String experienceListingUniqueId){
        return this.experienceListingService.existByExperienceListingUniqueId(experienceListingUniqueId);
    }


}
