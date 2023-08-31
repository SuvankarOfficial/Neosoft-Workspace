package com.travel.experience.listing.repository;

import com.travel.experience.listing.entity.ExperienceListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceListingRepository extends JpaRepository<ExperienceListingEntity,Long> {
    Boolean existsByExperienceListingUniqueId(String experienceListingUniqueId);
}
