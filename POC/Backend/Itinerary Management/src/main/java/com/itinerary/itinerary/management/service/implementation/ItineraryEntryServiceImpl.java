package com.itinerary.itinerary.management.service.implementation;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;
import com.itinerary.itinerary.management.feign.IExperienceListingService;
import com.itinerary.itinerary.management.global.exceptional.CannotBeNullException;
import com.itinerary.itinerary.management.global.exceptional.DoesNotExistException;
import com.itinerary.itinerary.management.repository.IItineraryEntryRepository;
import com.itinerary.itinerary.management.service.IItineraryExampleService;
import com.itinerary.itinerary.management.service.IitineraryExistById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("itinerary-entry-service")
public class ItineraryEntryServiceImpl implements IItineraryExampleService<ItineraryEntryEntity> {

    @Autowired
    public IItineraryEntryRepository iItineraryEntryRepository;

    @Autowired
    public IitineraryExistById itineraryService;

    @Autowired
    public IExperienceListingService experienceListingService;

    public ServiceResponseBean findAll() {
        List<ItineraryEntryEntity> itineraryEntryEntityList = this.iItineraryEntryRepository.findAll();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntryEntityList).build();
    }

    public ServiceResponseBean findById(String itineraryEntryUniqueId) {
        if (itineraryEntryUniqueId == null)
            throw new CannotBeNullException("Itinerary Entry Unique Id");
        Optional<ItineraryEntryEntity> itineraryEntryEntityOptional = this.iItineraryEntryRepository.findByItineraryEntryUniqueId(itineraryEntryUniqueId);
        if(itineraryEntryEntityOptional.isEmpty())
            throw new DoesNotExistException("Itinerary Entry Unique Id");
        ItineraryEntryEntity itineraryEntryEntity = itineraryEntryEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntryEntity).build();
    }

    public ServiceResponseBean add(ItineraryEntryEntity itineraryEntryEntity) {
        if (itineraryEntryEntity == null)
            throw new CannotBeNullException("Itinerary Entry");
        if (itineraryEntryEntity.getItineraryUniqueId() == null || itineraryEntryEntity.getItineraryEntryUniqueId().isBlank())
            throw new CannotBeNullException("Itinerary ID");
        if (!(this.itineraryService.existById(itineraryEntryEntity.getItineraryUniqueId())))
            throw new DoesNotExistException("Itinerary ID");
        if (itineraryEntryEntity.getExperienceUniqueId() == null || itineraryEntryEntity.getExperienceUniqueId().isBlank())
            throw new CannotBeNullException("Experience ID");
        if (!(this.experienceListingService.existByExperienceListingUniqueId(itineraryEntryEntity.getExperienceUniqueId())))
            throw new DoesNotExistException("Experience ID");
        itineraryEntryEntity.setItineraryEntryUniqueId(UUID.randomUUID().toString());
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    public ServiceResponseBean update(ItineraryEntryEntity itineraryEntryEntity) {
        if (itineraryEntryEntity == null)
            throw new CannotBeNullException("Itinerary Entry");
        if (itineraryEntryEntity.getItineraryUniqueId() != null) {
            if (!(this.itineraryService.existById(itineraryEntryEntity.getItineraryUniqueId()))) {
                throw new DoesNotExistException("Itinerary ID");
            }
        }
        if (itineraryEntryEntity.getExperienceUniqueId() != null)
            if (!(this.experienceListingService.existByExperienceListingUniqueId(itineraryEntryEntity.getExperienceUniqueId())))
                throw new DoesNotExistException("Experience ID");
        if (itineraryEntryEntity.getItineraryEntryUniqueId() == null)
            throw new CannotBeNullException("Itinerary Entry Id");
        Optional<ItineraryEntryEntity> itineraryEntryEntityOptional = this.iItineraryEntryRepository.findByItineraryEntryUniqueId(itineraryEntryEntity.getItineraryEntryUniqueId());
        if (itineraryEntryEntityOptional.isEmpty())
            throw new DoesNotExistException("Itinerary Entry Id");
        itineraryEntryEntity = updateItineraryEntryDatabaseToNew(itineraryEntryEntity, itineraryEntryEntityOptional.get());
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    private ItineraryEntryEntity updateItineraryEntryDatabaseToNew(ItineraryEntryEntity itineraryEntryEntity, ItineraryEntryEntity databaseItineraryEntryEntity) {
        return databaseItineraryEntryEntity.builder()
                .itineraryEntryUniqueId(itineraryEntryEntity.getItineraryEntryUniqueId())
                .itineraryUniqueId(itineraryEntryEntity.getItineraryUniqueId() != null ? itineraryEntryEntity.getItineraryUniqueId() : databaseItineraryEntryEntity.getItineraryUniqueId())
                .experienceUniqueId(itineraryEntryEntity.getExperienceUniqueId() != null ? itineraryEntryEntity.getExperienceUniqueId() : databaseItineraryEntryEntity.getExperienceUniqueId())
                .notes(itineraryEntryEntity.getNotes() != null ? itineraryEntryEntity.getNotes() : databaseItineraryEntryEntity.getNotes())
                .time(itineraryEntryEntity.getTime() != null ? itineraryEntryEntity.getTime() : databaseItineraryEntryEntity.getTime())
                .date(itineraryEntryEntity.getDate() != null ? itineraryEntryEntity.getDate() : databaseItineraryEntryEntity.getDate())
                .status(itineraryEntryEntity.getStatus() != null ? itineraryEntryEntity.getStatus() : databaseItineraryEntryEntity.getStatus())
                .build();
    }

    public ServiceResponseBean delete(String itineraryUniqueId) {
        if (itineraryUniqueId == null)
            throw new CannotBeNullException("Itinerary Id");
        Optional<ItineraryEntryEntity> itineraryEntryEntityOptional = this.iItineraryEntryRepository.findByItineraryEntryUniqueId(itineraryUniqueId);
        if (itineraryEntryEntityOptional.isEmpty())
            throw new DoesNotExistException("Itinerary Id");
        ItineraryEntryEntity itineraryEntryEntity = itineraryEntryEntityOptional.get();
        itineraryEntryEntity.setStatus(Boolean.FALSE);
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

}
