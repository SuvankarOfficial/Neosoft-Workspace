package com.itinerary.itinerary.management.service.implementation;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;
import com.itinerary.itinerary.management.repository.IItineraryEntryRepository;
import com.itinerary.itinerary.management.service.IItineraryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItineraryEntryServiceImpl implements IItineraryEntryService {

    @Autowired
    public IItineraryEntryRepository iItineraryEntryRepository;

    public ServiceResponseBean findAll(){
        List<ItineraryEntryEntity> itineraryEntryEntityList = this.iItineraryEntryRepository.findAll();
        if (itineraryEntryEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntryEntityList).build();
    }

    public ServiceResponseBean findById(Long itineraryEntryId) {
        if (itineraryEntryId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("ItineraryId Cannot be null").build();
        Optional<ItineraryEntryEntity> itineraryEntryEntityOptional = this.iItineraryEntryRepository.findById(itineraryEntryId);
        if (itineraryEntryEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        ItineraryEntryEntity itineraryEntryEntity = itineraryEntryEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntryEntity).build();
    }

    public ServiceResponseBean addItinerary(ItineraryEntryEntity itineraryEntryEntity) {
        if (itineraryEntryEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No itineraryEntry is null").build();
        itineraryEntryEntity.setItineraryUniqueId(UUID.randomUUID().toString());
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    public ServiceResponseBean updateItinerary(ItineraryEntryEntity itineraryEntryEntity) {
        if (itineraryEntryEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("itineraryEntry is null").build();
        if (itineraryEntryEntity.getItineraryEntryId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("itineraryEntry Id is null").build();
        Optional<ItineraryEntryEntity> itineraryEntryEntityOptional = this.iItineraryEntryRepository.findById(itineraryEntryEntity.getItineraryEntryId());
        if (itineraryEntryEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No Data to update").build();
        itineraryEntryEntity = updateItineraryDatabaseToNew(itineraryEntryEntity, itineraryEntryEntityOptional.get());
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    private ItineraryEntryEntity updateItineraryDatabaseToNew(ItineraryEntryEntity itineraryEntryEntity, ItineraryEntryEntity databaseItineraryEntryEntity) {
        return databaseItineraryEntryEntity.builder()
                .itineraryEntryId(itineraryEntryEntity.getItineraryEntryId())
                .itineraryEntryUniqueId(itineraryEntryEntity.getItineraryEntryUniqueId() != null ? itineraryEntryEntity.getItineraryEntryUniqueId() : databaseItineraryEntryEntity.getItineraryEntryUniqueId())
                .itineraryUniqueId(itineraryEntryEntity.getItineraryUniqueId() != null ? itineraryEntryEntity.getItineraryUniqueId() : databaseItineraryEntryEntity.getItineraryUniqueId())
                .experienceId(itineraryEntryEntity.getExperienceId() != null ? itineraryEntryEntity.getExperienceId() : databaseItineraryEntryEntity.getExperienceId())
                .notes(itineraryEntryEntity.getNotes() != null ? itineraryEntryEntity.getNotes() : databaseItineraryEntryEntity.getNotes())
                .time(itineraryEntryEntity.getTime() != null ? itineraryEntryEntity.getTime() : databaseItineraryEntryEntity.getTime())
                .date(itineraryEntryEntity.getDate() != null ? itineraryEntryEntity.getDate() : databaseItineraryEntryEntity.getDate())
                .status(itineraryEntryEntity.getStatus() != null ? itineraryEntryEntity.getStatus() : databaseItineraryEntryEntity.getStatus())
                .build();
    }

    public ServiceResponseBean deleteItinerary(Long itineraryId) {
        if (itineraryId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("Itinerary Id is null").build();
        Optional<ItineraryEntryEntity> itineraryEntryEntityOptional = this.iItineraryEntryRepository.findById(itineraryId);
        if(itineraryEntryEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        ItineraryEntryEntity itineraryEntryEntity = itineraryEntryEntityOptional.get();
        itineraryEntryEntity.setStatus(Boolean.FALSE);
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

}
