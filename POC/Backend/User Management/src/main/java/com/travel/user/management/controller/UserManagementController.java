package com.travel.user.management.controller;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserManagementEntity;
import com.travel.user.management.service.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-management")
public class UserManagementController{

    @Autowired
    private IUserManagementService userManagementService;

    @GetMapping("/find-by-id/{userManagementId}")
    public ServiceResponseBean findById(@PathVariable("userManagementId") Long userManagementId) {
        return this.userManagementService.findById(userManagementId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.userManagementService.findAll();
    }

    @PostMapping(value="/add")
    public ServiceResponseBean addUserManagement(@RequestBody UserManagementEntity userManagementEntity) {
        return this.userManagementService.addUserManagement(userManagementEntity);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateUserManagement(@RequestBody UserManagementEntity userManagementEntity) {
        return this.userManagementService.updateUserManagement(userManagementEntity);
    }

    @DeleteMapping("/delete/{userManagementId}")
    public ServiceResponseBean deleteUserManagement(@PathVariable("userManagementId") Long userManagementId) {
        return this.userManagementService.deleteUserManagement(userManagementId);
    }

    @GetMapping("/check-if-exist/{userManagementUniqueId}")
    public Boolean existByUserManagementUniqueId(@PathVariable("userManagementUniqueId") String userManagementUniqueId){
        return this.userManagementService.existByUserManagementUniqueId(userManagementUniqueId);
    }

}
