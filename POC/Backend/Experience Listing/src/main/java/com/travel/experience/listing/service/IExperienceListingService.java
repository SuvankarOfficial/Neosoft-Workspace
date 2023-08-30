package com.travel.experience.listing.service.implementation;

import com.travel.experience.listing.bean.response.ServiceResponseBean;
import com.travel.experience.listing.entity.ExperienceListingEntity;
import com.travel.experience.listing.repository.ExperienceListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceListingServiceImpl {

    @Autowired
    private ExperienceListingRepository experienceListingRepository;

    public ServiceResponseBean findById(Long experienceListingId) {
        if (experienceListingId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("experienceListingId Cannot be null").build();
        Optional<ExperienceListingEntity> experienceListingEntityOptional = this.experienceListingRepository.findById(experienceListingId);
        if (experienceListingEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        ExperienceListingEntity experienceListing = experienceListingEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(experienceListing).build();
    }

    public ServiceResponseBean findAll() {
        List<ExperienceListingEntity> experienceListingEntityList = this.experienceListingRepository.findAll();
        if (experienceListingEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(experienceListingEntityList).build();
    }

    public ServiceResponseBean addExperienceListing(ExperienceListingEntity experienceListing) {
        if (experienceListing == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No ExperienceListing is null").build();
        ExperienceListingEntity savedExperienceListing = this.experienceListingRepository.save(experienceListing);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperienceListing).build();
    }

    public ServiceResponseBean updateExperienceListing(ExperienceListingEntity experienceListing) {
        if (experienceListing == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("ExperienceListing is null").build();
        if (experienceListing.getExperienceListingId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("ExperienceListing Id is null").build();
        ExperienceListingEntity savedExperienceListing = this.experienceListingRepository.save(experienceListing);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperienceListing).build();
    }

    public ServiceResponseBean deleteExperienceListing(Long experienceListingId) {
        if (experienceListingId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("ExperienceListing Id is null").build();
        Optional<ExperienceListingEntity> experienceListingEntityOptional = this.experienceListingRepository.findById(experienceListingId);
        if(experienceListingEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        ExperienceListingEntity experienceListingEntity = experienceListingEntityOptional.get();
        experienceListingEntity.setStatus(Boolean.FALSE);
        ExperienceListingEntity savedExperienceListing = this.experienceListingRepository.save(experienceListingEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperienceListing).build();
    }


}
