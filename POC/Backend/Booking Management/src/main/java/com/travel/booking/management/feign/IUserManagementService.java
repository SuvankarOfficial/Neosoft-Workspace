package com.travel.booking.management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-MANAGEMENT",url = "localhost:9002/user-management")
public interface IUserManagementService {

    @GetMapping("/check-if-exist/{userManagementUniqueId}")
    public Boolean existByUserManagementUniqueId(@PathVariable("userManagementUniqueId") String userManagementUniqueId);

}
