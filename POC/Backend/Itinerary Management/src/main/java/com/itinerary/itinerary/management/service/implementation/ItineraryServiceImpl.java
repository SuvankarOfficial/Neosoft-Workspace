package com.itinerary.itinerary.management.service.implementation;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntity;
import com.itinerary.itinerary.management.feign.IExperienceListingService;
import com.itinerary.itinerary.management.feign.IUserManagementService;
import com.itinerary.itinerary.management.global.exceptional.CannotBeNullException;
import com.itinerary.itinerary.management.global.exceptional.DoesNotExistException;
import com.itinerary.itinerary.management.repository.IItineraryRepository;
import com.itinerary.itinerary.management.service.IitineraryExistById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("itinerary-service")
@Primary
public class ItineraryServiceImpl implements IitineraryExistById<ItineraryEntity> {

    @Autowired
    public IItineraryRepository itineraryRepository;

    @Autowired
    public IUserManagementService userManagementService;

    @Autowired
    public IExperienceListingService experienceListingService;

    public ServiceResponseBean findAll(){
        List<ItineraryEntity> itineraryEntityList = this.itineraryRepository.findAll();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntityList).build();
    }

    public ItineraryEntity findById(String itineraryUniqueId) {
        if (itineraryUniqueId == null)
            throw new CannotBeNullException("Itinerary Id");
        Optional<ItineraryEntity> itineraryEntityOptional = this.itineraryRepository.findByitineraryUniqueId(itineraryUniqueId);
        if (itineraryEntityOptional.isEmpty())
            throw new CannotBeNullException("Itinerary Id");
        ItineraryEntity itineraryEntity = itineraryEntityOptional.get();
        return itineraryEntity;
    }

    public ServiceResponseBean add(ItineraryEntity itineraryEntity) {
        if (itineraryEntity == null)
            throw new CannotBeNullException("Itinerary Entity");
        if(itineraryEntity.getUserUniqueId() == null || itineraryEntity.getUserUniqueId().isBlank())
            throw new CannotBeNullException("User ID");
        if (!(this.userManagementService.existByUserManagementUniqueId(itineraryEntity.getUserUniqueId())))
            throw new DoesNotExistException("User ID");
        if (itineraryEntity.getExperienceUniqueId() == null || itineraryEntity.getExperienceUniqueId().isBlank())
            throw new CannotBeNullException("Experience ID");
        if (!(this.experienceListingService.existByExperienceListingUniqueId(itineraryEntity.getExperienceUniqueId())))
            throw new DoesNotExistException("Experience ID");
        itineraryEntity.setItineraryUniqueId(UUID.randomUUID().toString());
        ItineraryEntity savedItinerary = this.itineraryRepository.save(itineraryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    public ServiceResponseBean update(ItineraryEntity itineraryEntity) {
        if (itineraryEntity == null)
            throw new CannotBeNullException("Itinerary");
        if(itineraryEntity.getUserUniqueId() != null)
            if(!(this.userManagementService.existByUserManagementUniqueId(itineraryEntity.getUserUniqueId())))
                throw new DoesNotExistException("User Id");
        if (itineraryEntity.getExperienceUniqueId() != null)
            if (!(this.experienceListingService.existByExperienceListingUniqueId(itineraryEntity.getExperienceUniqueId())))
                throw new DoesNotExistException("Experience ID");
        ItineraryEntity databaseItineraryId = this.findById(itineraryEntity.getItineraryUniqueId());
        itineraryEntity = updateItineraryDatabaseToNew(itineraryEntity,databaseItineraryId);
        ItineraryEntity savedItinerary = this.itineraryRepository.save(itineraryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    private ItineraryEntity updateItineraryDatabaseToNew(ItineraryEntity itineraryEntity, ItineraryEntity databaseItineraryEntity) {
        return databaseItineraryEntity.builder()
                .itineraryUniqueId(itineraryEntity.getItineraryUniqueId())
                .userUniqueId(itineraryEntity.getUserUniqueId() != null ? itineraryEntity.getUserUniqueId() : databaseItineraryEntity.getUserUniqueId())
                .experienceUniqueId(itineraryEntity.getExperienceUniqueId() != null ? itineraryEntity.getExperienceUniqueId() : databaseItineraryEntity.getExperienceUniqueId())
                .name(itineraryEntity.getName() != null ? itineraryEntity.getName() : databaseItineraryEntity.getName())
                .description(itineraryEntity.getDescription() != null ? itineraryEntity.getDescription() : databaseItineraryEntity.getDescription())
                .status(itineraryEntity.getStatus() != null ? itineraryEntity.getStatus() : databaseItineraryEntity.getStatus())
                .build();
    }

    public ServiceResponseBean delete(String itineraryUniqueId) {
        ItineraryEntity itineraryEntity = this.findById(itineraryUniqueId);
        itineraryEntity.setStatus(Boolean.FALSE);
        ItineraryEntity savedItinerary = this.itineraryRepository.save(itineraryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    @Override
    public Boolean existById(String itineraryUniqueId) {
        return this.itineraryRepository.existsByItineraryUniqueId(itineraryUniqueId);
    }

}
