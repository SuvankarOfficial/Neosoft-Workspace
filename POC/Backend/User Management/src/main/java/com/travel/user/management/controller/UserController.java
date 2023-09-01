package com.travel.user.management.controller;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserEntity;
import com.travel.user.management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-management")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/find-by-id/{userId}")
    public ServiceResponseBean findById(@PathVariable("userId") Long userId) {
        return this.userService.findById(userId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.userService.findAll();
    }

    @PostMapping(value="/add")
    public ServiceResponseBean addUser(@RequestBody UserEntity userEntity) {
        return this.userService.addUser(userEntity);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateUser(@RequestBody UserEntity userEntity) {
        return this.userService.updateUser(userEntity);
    }

    @DeleteMapping("/delete/{userId}")
    public ServiceResponseBean deleteUser(@PathVariable("userId") Long userId) {
        return this.userService.deleteUser(userId);
    }

    @GetMapping("/check-if-exist/{userUniqueId}")
    public Boolean existByUserUniqueId(@PathVariable("userUniqueId") String userUniqueId){
        return this.userService.existByUserUniqueId(userUniqueId);
    }

}
