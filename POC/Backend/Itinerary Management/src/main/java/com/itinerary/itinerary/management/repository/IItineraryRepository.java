package com.itinerary.itinerary.management.repository;

import com.itinerary.itinerary.management.entity.ItineraryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItineraryRepository extends MongoRepository<ItineraryEntity, Long> {
}
