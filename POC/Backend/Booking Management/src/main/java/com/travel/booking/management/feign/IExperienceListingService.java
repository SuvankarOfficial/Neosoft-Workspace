package com.travel.booking.management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EXPERIENCE-LISTING",url = "localhost:9001/experience-listing")
public interface IExperienceListingService {

    @GetMapping("/check-if-exist/{experienceListingUniqueId}")
    public Boolean existByExperienceListingUniqueId(@PathVariable("experienceListingUniqueId") String experienceListingUniqueId);

}
