package com.travel.experience.listing.service;

import com.travel.experience.listing.bean.response.ServiceResponseBean;
import com.travel.experience.listing.entity.ExperienceEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IExperienceListingService {

    public ServiceResponseBean findById(Long experienceListingId);

    public ServiceResponseBean findAll();

    public ServiceResponseBean addExperienceListing(ExperienceEntity experienceListing, MultipartFile image) throws IOException;

    public ServiceResponseBean updateExperienceListing(ExperienceEntity experienceListing, MultipartFile image) throws IOException ;

    public ServiceResponseBean deleteExperienceListing(Long experienceListingId);


    public ResponseEntity downloadImage(Long experienceListingId);

    public Boolean existByExperienceListingUniqueId(String experienceListingUniqueId);
}
