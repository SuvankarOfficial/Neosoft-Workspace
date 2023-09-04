package com.travel.experience.listing.controller;

import com.travel.experience.listing.bean.response.ServiceResponseBean;
import com.travel.experience.listing.entity.ExperienceEntity;
import com.travel.experience.listing.service.IExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private IExperienceService experienceService;

    @GetMapping("/find-by-id/{experienceUniqueId}")
    public ServiceResponseBean findById(@PathVariable("experienceUniqueId") String experienceUniqueId) {
        return this.experienceService.findById(experienceUniqueId);
    }

    @GetMapping("/download-image/{experienceUniqueId}")
    public ResponseEntity downloadImage(@PathVariable("experienceUniqueId") String experienceUniqueId) {
        return this.experienceService.downloadImage(experienceUniqueId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.experienceService.findAll();
    }

    @PostMapping(value="/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ServiceResponseBean addExperience(@RequestPart("experience") ExperienceEntity experience, @RequestPart("image") MultipartFile multipartFile) throws IOException {
        return this.experienceService.addExperience(experience,multipartFile);
    }

    @PutMapping("/update-with-image")
    public ServiceResponseBean updateExperienceWithImage(@RequestPart("experience") ExperienceEntity experience, @RequestPart("image") MultipartFile multipartFile) throws IOException {
        return this.experienceService.updateExperience(experience,multipartFile);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateExperience(@RequestBody ExperienceEntity experience) throws IOException {
        return this.experienceService.updateExperience(experience,null);
    }

    @DeleteMapping("/delete/{experienceUniqueId}")
    public ServiceResponseBean deleteExperience(@PathVariable("experienceUniqueId") String experienceUniqueId) {
        return this.experienceService.deleteExperience(experienceUniqueId);
    }

    @GetMapping("/check-if-exist/{experienceUniqueId}")
    public Boolean existByExperienceUniqueId(@PathVariable("experienceUniqueId") String experienceUniqueId){
        return this.experienceService.existByExperienceUniqueId(experienceUniqueId);
    }


}
