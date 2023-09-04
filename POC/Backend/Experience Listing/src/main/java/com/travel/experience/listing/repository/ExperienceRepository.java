package com.travel.experience.listing.repository;

import com.travel.experience.listing.entity.ExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceEntity,Long> {
    Boolean existsByExperienceUniqueId(String experienceListingUniqueId);

    Optional<ExperienceEntity> findByExperienceUniqueId(String experienceUniqueId);
}
