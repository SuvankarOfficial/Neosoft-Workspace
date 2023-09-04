package com.travel.experience.listing.service;

import com.travel.experience.listing.bean.response.ServiceResponseBean;
import com.travel.experience.listing.entity.ExperienceEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IExperienceService {

    public ServiceResponseBean findById(String experienceUniqueId);

    public ServiceResponseBean findAll();

    public ServiceResponseBean addExperience(ExperienceEntity experience, MultipartFile image) throws IOException;

    public ServiceResponseBean updateExperience(ExperienceEntity experience, MultipartFile image) throws IOException ;

    public ServiceResponseBean deleteExperience(String experienceUniqueId);


    public ResponseEntity downloadImage(String experienceUniqueId);

    public Boolean existByExperienceUniqueId(String experienceUniqueId);
}
