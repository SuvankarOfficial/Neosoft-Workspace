package com.travel.booking.management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EXPERIENCE-SERVICE")
public interface IExperienceListingService {

    @GetMapping("/experience/check-if-exist/{experienceListingUniqueId}")
    public Boolean existByExperienceListingUniqueId(@PathVariable("experienceListingUniqueId") String experienceListingUniqueId);

}
