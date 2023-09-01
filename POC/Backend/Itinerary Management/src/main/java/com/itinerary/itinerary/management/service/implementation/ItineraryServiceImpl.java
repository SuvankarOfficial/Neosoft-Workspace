package com.itinerary.itinerary.management.service.implementation;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntity;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;
import com.itinerary.itinerary.management.repository.IItineraryRepository;
import com.itinerary.itinerary.management.service.IItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItineraryServiceImpl implements IItineraryService{

    @Autowired
    public IItineraryRepository itineraryRepository;

    public ServiceResponseBean findAll(){
        List<ItineraryEntity> itineraryEntityList = this.itineraryRepository.findAll();
        if (itineraryEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntityList).build();
    }

    public ServiceResponseBean findById(Long itineraryId) {
        if (itineraryId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("ItineraryId Cannot be null").build();
        Optional<ItineraryEntity> itineraryEntityOptional = this.itineraryRepository.findById(itineraryId);
        if (itineraryEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        ItineraryEntity itineraryEntity = itineraryEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntity).build();
    }

    public ServiceResponseBean addItinerary(ItineraryEntity itineraryEntity) {
        if (itineraryEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No UserManagement is null").build();
        itineraryEntity.setItineraryUniqueId(UUID.randomUUID().toString());
        ItineraryEntity savedItinerary = this.itineraryRepository.save(itineraryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    public ServiceResponseBean updateItinerary(ItineraryEntity itineraryEntity) {
        if (itineraryEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("itinerary is null").build();
        if (itineraryEntity.getItineraryId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("itinerary Id is null").build();
        Optional<ItineraryEntity> itineraryEntityOptional = this.itineraryRepository.findById(itineraryEntity.getItineraryId());
        if (itineraryEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No Data to update").build();
        itineraryEntity = updateItineraryDatabaseToNew(itineraryEntity, itineraryEntityOptional.get());
        ItineraryEntity savedItinerary = this.itineraryRepository.save(itineraryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    private ItineraryEntity updateItineraryDatabaseToNew(ItineraryEntity itineraryEntity, ItineraryEntity databaseItineraryEntity) {
        return databaseItineraryEntity.builder()
                .itineraryId(itineraryEntity.getItineraryId())
                .itineraryUniqueId(itineraryEntity.getItineraryUniqueId() != null ? itineraryEntity.getItineraryUniqueId() : databaseItineraryEntity.getItineraryUniqueId())
                .userUniqueId(itineraryEntity.getUserUniqueId() != null ? itineraryEntity.getUserUniqueId() : databaseItineraryEntity.getUserUniqueId())
                .name(itineraryEntity.getName() != null ? itineraryEntity.getName() : databaseItineraryEntity.getName())
                .description(itineraryEntity.getDescription() != null ? itineraryEntity.getDescription() : databaseItineraryEntity.getDescription())
                .status(itineraryEntity.getStatus() != null ? itineraryEntity.getStatus() : databaseItineraryEntity.getStatus())
                .build();
    }

    public ServiceResponseBean deleteItinerary(Long itineraryId) {
        if (itineraryId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("Itinerary Id is null").build();
        Optional<ItineraryEntity> itineraryEntityOptional = this.itineraryRepository.findById(itineraryId);
        if(itineraryEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        ItineraryEntity itineraryEntity = itineraryEntityOptional.get();
        itineraryEntity.setStatus(Boolean.FALSE);
        ItineraryEntity savedItinerary = this.itineraryRepository.save(itineraryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

}
