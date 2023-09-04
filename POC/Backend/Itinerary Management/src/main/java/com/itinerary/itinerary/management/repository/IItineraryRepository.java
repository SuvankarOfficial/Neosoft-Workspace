package com.itinerary.itinerary.management.repository;

import com.itinerary.itinerary.management.entity.ItineraryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IItineraryRepository extends MongoRepository<ItineraryEntity, Long> {
    public Boolean existsByItineraryUniqueId(String itineraryUniqueId);

    Optional<ItineraryEntity> findByitineraryUniqueId(String itineraryId);
}
