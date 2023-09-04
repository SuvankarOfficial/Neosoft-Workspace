package com.itinerary.itinerary.management.service.implementation;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.entity.ItineraryEntity;
import com.itinerary.itinerary.management.feign.IUserManagementService;
import com.itinerary.itinerary.management.global.exceptional.CannotBeNullException;
import com.itinerary.itinerary.management.global.exceptional.DoesNotExistException;
import com.itinerary.itinerary.management.repository.IItineraryRepository;
import com.itinerary.itinerary.management.service.IItineraryExampleService;
import com.itinerary.itinerary.management.service.IItineraryService;
import com.itinerary.itinerary.management.service.IitineraryExistById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.naming.Name;
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

    public ServiceResponseBean findAll(){
        List<ItineraryEntity> itineraryEntityList = this.itineraryRepository.findAll();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntityList).build();
    }

    public ServiceResponseBean findById(String itineraryUniqueId) {
        if (itineraryUniqueId == null)
            throw new CannotBeNullException("Itinerary Id");
        Optional<ItineraryEntity> itineraryEntityOptional = this.itineraryRepository.findByitineraryUniqueId(itineraryUniqueId);
        if (itineraryEntityOptional.isEmpty())
            throw new CannotBeNullException("Itinerary Id");
        ItineraryEntity itineraryEntity = itineraryEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(itineraryEntity).build();
    }

    public ServiceResponseBean add(ItineraryEntity itineraryEntity) {
        if (itineraryEntity == null)
            throw new CannotBeNullException("Itinerary Entity");
        if(itineraryEntity.getUserUniqueId() == null || itineraryEntity.getUserUniqueId().isBlank())
            throw new CannotBeNullException("User ID");
        if(!(this.userManagementService.existByUserManagementUniqueId(itineraryEntity.getUserUniqueId())))
            throw new DoesNotExistException("User ID");
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
        if (itineraryEntity.getItineraryUniqueId() == null)
            throw new CannotBeNullException("Itinerary Id");
        Optional<ItineraryEntity> itineraryEntityOptional = this.itineraryRepository.findByitineraryUniqueId(itineraryEntity.getItineraryUniqueId());
        if (itineraryEntityOptional.isEmpty())
            throw new DoesNotExistException("Itinerary Id");
        itineraryEntity = updateItineraryDatabaseToNew(itineraryEntity, itineraryEntityOptional.get());
        ItineraryEntity savedItinerary = this.itineraryRepository.save(itineraryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    private ItineraryEntity updateItineraryDatabaseToNew(ItineraryEntity itineraryEntity, ItineraryEntity databaseItineraryEntity) {
        return databaseItineraryEntity.builder()
                .itineraryUniqueId(itineraryEntity.getItineraryUniqueId())
                .userUniqueId(itineraryEntity.getUserUniqueId() != null ? itineraryEntity.getUserUniqueId() : databaseItineraryEntity.getUserUniqueId())
                .name(itineraryEntity.getName() != null ? itineraryEntity.getName() : databaseItineraryEntity.getName())
                .description(itineraryEntity.getDescription() != null ? itineraryEntity.getDescription() : databaseItineraryEntity.getDescription())
                .status(itineraryEntity.getStatus() != null ? itineraryEntity.getStatus() : databaseItineraryEntity.getStatus())
                .build();
    }

    public ServiceResponseBean delete(String itineraryUniqueId) {
        if (itineraryUniqueId == null)
            throw new CannotBeNullException("Itinerary Id");
        Optional<ItineraryEntity> itineraryEntityOptional = this.itineraryRepository.findByitineraryUniqueId(itineraryUniqueId);
        if(itineraryEntityOptional.isEmpty())
            throw new DoesNotExistException("Itinerary Id");
        ItineraryEntity itineraryEntity = itineraryEntityOptional.get();
        itineraryEntity.setStatus(Boolean.FALSE);
        ItineraryEntity savedItinerary = this.itineraryRepository.save(itineraryEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedItinerary).build();
    }

    @Override
    public Boolean existById(String itineraryUniqueId) {
        return this.itineraryRepository.existsByItineraryUniqueId(itineraryUniqueId);
    }

}
