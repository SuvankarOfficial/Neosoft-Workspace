package com.travel.experience.listing.service.implementation;

import com.travel.experience.listing.bean.response.ServiceResponseBean;
import com.travel.experience.listing.entity.ExperienceEntity;
import com.travel.experience.listing.repository.ExperienceListingRepository;
import com.travel.experience.listing.service.IExperienceListingService;
import com.travel.experience.listing.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExperienceListingServiceImpl implements IExperienceListingService {

    @Autowired
    private ExperienceListingRepository experienceListingRepository;

    public ServiceResponseBean findById(Long experienceListingId) {
        if (experienceListingId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("experienceListingId Cannot be null").build();
        Optional<ExperienceEntity> experienceListingEntityOptional = this.experienceListingRepository.findById(experienceListingId);
        if (experienceListingEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        ExperienceEntity experienceListing = experienceListingEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(experienceListing).build();
    }

    public ServiceResponseBean findAll() {
        List<ExperienceEntity> experienceListingEntityList = this.experienceListingRepository.findAll();
        if (experienceListingEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(experienceListingEntityList).build();
    }

    public ServiceResponseBean addExperienceListing(ExperienceEntity experienceListing, MultipartFile image) throws IOException {
        if (experienceListing == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No ExperienceListing is null").build();
        experienceListing.setExperienceUniqueId(UUID.randomUUID().toString());
        experienceListing.setImage(ImageUtil.compressImage(image.getBytes()));
        experienceListing.setImageType(image.getContentType());
        experienceListing.setImageName(image.getOriginalFilename());
        ExperienceEntity savedExperienceListing = this.experienceListingRepository.save(experienceListing);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperienceListing).build();
    }

    public ServiceResponseBean updateExperienceListing(ExperienceEntity experienceListing, MultipartFile image) throws IOException {
        if (experienceListing == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("ExperienceListing is null").build();
        if (experienceListing.getExperienceId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("ExperienceListing Id is null").build();
        Optional<ExperienceEntity> databaseExperienceListing = this.experienceListingRepository.findById(experienceListing.getExperienceId());
        if(databaseExperienceListing.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No Data to update").build();
        experienceListing = updateExperienceListingDatabaseToNewObject(experienceListing,databaseExperienceListing.get());
        if(image != null){
            experienceListing.setImage(ImageUtil.compressImage(image.getBytes()));
            experienceListing.setImageType(image.getContentType());
            experienceListing.setImageName(image.getOriginalFilename());
        }
        ExperienceEntity savedExperienceListing = this.experienceListingRepository.save(experienceListing);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperienceListing).build();
    }

    private ExperienceEntity updateExperienceListingDatabaseToNewObject(ExperienceEntity experienceListing, ExperienceEntity databaseExperienceListing) {
        return ExperienceEntity.builder()
                .experienceId(experienceListing.getExperienceId())
                .experienceUniqueId(experienceListing.getExperienceUniqueId() != null ? experienceListing.getExperienceUniqueId() : databaseExperienceListing.getExperienceUniqueId())
                .name(experienceListing.getName() != null ? experienceListing.getName() : databaseExperienceListing.getName())
                .description(experienceListing.getDescription() != null ? experienceListing.getDescription() : databaseExperienceListing.getDescription())
                .location(experienceListing.getLocation() != null ? experienceListing.getLocation() : databaseExperienceListing.getLocation())
                .category(experienceListing.getCategory() != null ? experienceListing.getCategory() : databaseExperienceListing.getCategory())
                .price(experienceListing.getPrice() != null ? experienceListing.getPrice() : databaseExperienceListing.getPrice())
                .availability(experienceListing.getAvailability() != null ? experienceListing.getAvailability() : databaseExperienceListing.getAvailability())
                .image(experienceListing.getImage() != null ? experienceListing.getImage() : databaseExperienceListing.getImage())
                .imageType(experienceListing.getImageType() != null ? experienceListing.getImageType() : databaseExperienceListing.getImageType())
                .imageName(experienceListing.getImageName() != null ? experienceListing.getImageName() : databaseExperienceListing.getImageName())
                .status(experienceListing.getStatus() != null ? experienceListing.getStatus() : databaseExperienceListing.getStatus())
                .build();
    }

    public ServiceResponseBean deleteExperienceListing(Long experienceListingId) {
        if (experienceListingId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("ExperienceListing Id is null").build();
        Optional<ExperienceEntity> experienceListingEntityOptional = this.experienceListingRepository.findById(experienceListingId);
        if(experienceListingEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        ExperienceEntity experienceListingEntity = experienceListingEntityOptional.get();
        experienceListingEntity.setStatus(Boolean.FALSE);
        ExperienceEntity savedExperienceListing = this.experienceListingRepository.save(experienceListingEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperienceListing).build();
    }

    public ResponseEntity downloadImage(Long experienceListingId) {
        if (experienceListingId == null)
            return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.FALSE).error("experienceListingId Cannot be null").build());
        Optional<ExperienceEntity> experienceListingEntityOptional = this.experienceListingRepository.findById(experienceListingId);
        if (experienceListingEntityOptional.isEmpty())
            return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build());
        ExperienceEntity experienceListing = experienceListingEntityOptional.get();
        return ResponseEntity.status(HttpStatus.OK)
//                uncomment only if you want to download, but for now you can use it for display
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + experienceListing.getImageName())
                .header(experienceListing.getImageName())
                .contentType(MediaType.valueOf(experienceListing.getImageType()))
                .body(ImageUtil.decompressImage(experienceListing.getImage()));
    }

    @Override
    public Boolean existByExperienceListingUniqueId(String experienceListingUniqueId) {
        return experienceListingRepository.existsByExperienceListingUniqueId(experienceListingUniqueId);
    }


}
