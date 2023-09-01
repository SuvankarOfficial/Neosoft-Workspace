package com.itinerary.itinerary.management.repository;

import com.itinerary.itinerary.management.entity.ItineraryEntity;
import com.itinerary.itinerary.management.entity.ItineraryEntryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItineraryEntryRepository extends MongoRepository<ItineraryEntryEntity, Long> {
}
