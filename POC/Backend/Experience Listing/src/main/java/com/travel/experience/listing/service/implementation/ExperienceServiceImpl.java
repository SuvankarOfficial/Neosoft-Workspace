package com.travel.experience.listing.service.implementation;

import com.travel.experience.listing.bean.response.ServiceResponseBean;
import com.travel.experience.listing.entity.ExperienceEntity;
import com.travel.experience.listing.global.exceptional.CannotBeNullException;
import com.travel.experience.listing.global.exceptional.DoesNotExistException;
import com.travel.experience.listing.repository.ExperienceRepository;
import com.travel.experience.listing.service.IExperienceService;
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
public class ExperienceServiceImpl implements IExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public ServiceResponseBean findById(String experienceUniqueId) {
        if (experienceUniqueId == null)
            throw new CannotBeNullException("Experience Id");
        Optional<ExperienceEntity> experienceEntityOptional = this.experienceRepository.findByExperienceUniqueId(experienceUniqueId);
        if (experienceEntityOptional.isEmpty())
            throw new DoesNotExistException("Experience Id");
        ExperienceEntity experience = experienceEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(experience).build();
    }

    public ServiceResponseBean findAll() {
        List<ExperienceEntity> experienceEntityList = this.experienceRepository.findAll();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(experienceEntityList).build();
    }

    public ServiceResponseBean addExperience(ExperienceEntity experience, MultipartFile image) throws IOException {
        if (experience == null)
            throw new CannotBeNullException("Experience");
        experience.setExperienceUniqueId(UUID.randomUUID().toString());
        experience.setImage(ImageUtil.compressImage(image.getBytes()));
        experience.setImageType(image.getContentType());
        experience.setImageName(image.getOriginalFilename());
        ExperienceEntity savedExperience = this.experienceRepository.save(experience);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperience).build();
    }

    public ServiceResponseBean updateExperience(ExperienceEntity experience, MultipartFile image) throws IOException {
        if (experience == null)
            throw new CannotBeNullException("Experience");
        if (experience.getExperienceUniqueId() == null)
            throw new CannotBeNullException("Experience Id");
        Optional<ExperienceEntity> databaseExperience = this.experienceRepository.findByExperienceUniqueId(experience.getExperienceUniqueId());
        if (databaseExperience.isEmpty())
            throw new DoesNotExistException("Experience Id");
        experience = updateExperienceDatabaseToNewObject(experience, databaseExperience.get());
        if (image != null) {
            experience.setImage(ImageUtil.compressImage(image.getBytes()));
            experience.setImageType(image.getContentType());
            experience.setImageName(image.getOriginalFilename());
        }
        ExperienceEntity savedExperience = this.experienceRepository.save(experience);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperience).build();
    }

    private ExperienceEntity updateExperienceDatabaseToNewObject(ExperienceEntity experience, ExperienceEntity databaseExperience) {
        return ExperienceEntity.builder()
                .experienceId(experience.getExperienceId())
                .experienceUniqueId(experience.getExperienceUniqueId() != null ? experience.getExperienceUniqueId() : databaseExperience.getExperienceUniqueId())
                .name(experience.getName() != null ? experience.getName() : databaseExperience.getName())
                .description(experience.getDescription() != null ? experience.getDescription() : databaseExperience.getDescription())
                .location(experience.getLocation() != null ? experience.getLocation() : databaseExperience.getLocation())
                .category(experience.getCategory() != null ? experience.getCategory() : databaseExperience.getCategory())
                .price(experience.getPrice() != null ? experience.getPrice() : databaseExperience.getPrice())
                .availability(experience.getAvailability() != null ? experience.getAvailability() : databaseExperience.getAvailability())
                .duration(experience.getDuration() != null ? experience.getDuration() : databaseExperience.getDuration())
                .image(experience.getImage() != null ? experience.getImage() : databaseExperience.getImage())
                .imageType(experience.getImageType() != null ? experience.getImageType() : databaseExperience.getImageType())
                .imageName(experience.getImageName() != null ? experience.getImageName() : databaseExperience.getImageName())
                .status(experience.getStatus() != null ? experience.getStatus() : databaseExperience.getStatus())
                .build();
    }

    public ServiceResponseBean deleteExperience(String experienceUniqueId) {
        if (experienceUniqueId == null)
            throw new CannotBeNullException("Experience Id");
        Optional<ExperienceEntity> experienceEntityOptional = this.experienceRepository.findByExperienceUniqueId(experienceUniqueId);
        if (experienceEntityOptional.isEmpty())
            throw new DoesNotExistException("Experience Id");
        ExperienceEntity experienceEntity = experienceEntityOptional.get();
        experienceEntity.setStatus(Boolean.FALSE);
        ExperienceEntity savedExperience = this.experienceRepository.save(experienceEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedExperience).build();
    }

    public ResponseEntity downloadImage(String experienceUniqueId) {
        if (experienceUniqueId == null)
            throw new CannotBeNullException("Experience Id");
        Optional<ExperienceEntity> experienceEntityOptional = this.experienceRepository.findByExperienceUniqueId(experienceUniqueId);
        if (experienceEntityOptional.isEmpty())
            throw new DoesNotExistException("Experience Id");
        ExperienceEntity experience = experienceEntityOptional.get();
        return ResponseEntity.status(HttpStatus.OK)
//                uncomment only if you want to download, but for now you can use it for display
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + experience.getImageName())
                .header(experience.getImageName())
                .contentType(MediaType.valueOf(experience.getImageType()))
                .body(ImageUtil.decompressImage(experience.getImage()));
    }

    @Override
    public Boolean existByExperienceUniqueId(String experienceUniqueId) {
        return experienceRepository.existsByExperienceUniqueId(experienceUniqueId);
    }


}
