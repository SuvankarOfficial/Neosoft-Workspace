package com.itinerary.itinerary.management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface IUserManagementService {

    @GetMapping("/user/check-if-exist/{userManagementUniqueId}")
    public Boolean existByUserManagementUniqueId(@PathVariable("userManagementUniqueId") String userManagementUniqueId);

}
