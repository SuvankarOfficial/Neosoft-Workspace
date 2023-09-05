package com.itinerary.itinerary.management.service.implementation;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;
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

    public ServiceResponseBean findAll() {
        List<ItineraryEntryEntity> itineraryEntryEntityList = this.iItineraryEntryRepository.findAll();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntryEntityList).build();
    }

    public ItineraryEntryEntity findById(String itineraryEntryUniqueId) {
        if (itineraryEntryUniqueId == null)
            throw new CannotBeNullException("Itinerary Entry Unique Id");
        Optional<ItineraryEntryEntity> itineraryEntryEntityOptional = this.iItineraryEntryRepository.findByItineraryEntryUniqueId(itineraryEntryUniqueId);
        if(itineraryEntryEntityOptional.isEmpty())
            throw new DoesNotExistException("Itinerary Entry Unique Id");
        ItineraryEntryEntity itineraryEntryEntity = itineraryEntryEntityOptional.get();
        return itineraryEntryEntity;
    }

    public ServiceResponseBean add(ItineraryEntryEntity itineraryEntryEntity) {
        if (itineraryEntryEntity == null)
            throw new CannotBeNullException("Itinerary Entry");
        if (itineraryEntryEntity.getItineraryUniqueId() == null || itineraryEntryEntity.getItineraryEntryUniqueId().isBlank())
            throw new CannotBeNullException("Itinerary ID");
        if (!(this.itineraryService.existById(itineraryEntryEntity.getItineraryUniqueId())))
            throw new DoesNotExistException("Itinerary ID");
        itineraryEntryEntity.setItineraryEntryUniqueId(UUID.randomUUID().toString());
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    public ServiceResponseBean update(ItineraryEntryEntity itineraryEntryEntity) {
        if (itineraryEntryEntity == null)
            throw new CannotBeNullException("Itinerary Entry");
        if (itineraryEntryEntity.getItineraryUniqueId() != null)
            if (!(this.itineraryService.existById(itineraryEntryEntity.getItineraryUniqueId())))
                throw new DoesNotExistException("Itinerary ID");
        ItineraryEntryEntity databaseItineraryEntryEntity = this.findById(itineraryEntryEntity.getItineraryUniqueId());
        itineraryEntryEntity = updateItineraryEntryDatabaseToNew(itineraryEntryEntity, databaseItineraryEntryEntity);
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    private ItineraryEntryEntity updateItineraryEntryDatabaseToNew(ItineraryEntryEntity itineraryEntryEntity, ItineraryEntryEntity databaseItineraryEntryEntity) {
        return databaseItineraryEntryEntity.builder()
                .itineraryEntryUniqueId(itineraryEntryEntity.getItineraryEntryUniqueId())
                .itineraryUniqueId(itineraryEntryEntity.getItineraryUniqueId() != null ? itineraryEntryEntity.getItineraryUniqueId() : databaseItineraryEntryEntity.getItineraryUniqueId())
                .notes(itineraryEntryEntity.getNotes() != null ? itineraryEntryEntity.getNotes() : databaseItineraryEntryEntity.getNotes())
                .time(itineraryEntryEntity.getTime() != null ? itineraryEntryEntity.getTime() : databaseItineraryEntryEntity.getTime())
                .date(itineraryEntryEntity.getDate() != null ? itineraryEntryEntity.getDate() : databaseItineraryEntryEntity.getDate())
                .status(itineraryEntryEntity.getStatus() != null ? itineraryEntryEntity.getStatus() : databaseItineraryEntryEntity.getStatus())
                .build();
    }

    public ServiceResponseBean delete(String itineraryUniqueId) {
        ItineraryEntryEntity itineraryEntryEntity = this.findById(itineraryUniqueId);
        itineraryEntryEntity.setStatus(Boolean.FALSE);
        ItineraryEntryEntity savedItinerary = this.iItineraryEntryRepository.save(itineraryEntryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

}
