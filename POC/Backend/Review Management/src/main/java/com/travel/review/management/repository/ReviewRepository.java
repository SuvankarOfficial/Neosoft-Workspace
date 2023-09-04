package com.travel.review.management.repository;

import com.travel.review.management.entity.ReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<ReviewEntity,Long> {
    Optional<ReviewEntity> findByReviewUniqueId(String reviewUniqueId);
}
