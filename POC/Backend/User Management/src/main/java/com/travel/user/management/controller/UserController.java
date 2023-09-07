package com.travel.user.management.controller;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserEntity;
import com.travel.user.management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/find-by-id/{userUniqueId}")
    public ServiceResponseBean findById(@PathVariable("userUniqueId") String userUniqueId) {
        return this.userService.findById(userUniqueId);
    }

    @PostMapping("/check-credential")
    public ServiceResponseBean checkCredential(@RequestParam("username") String username, @RequestParam("password") String password){
        return this.userService.checkCredential(username,password);
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

    @DeleteMapping("/delete/{userUniqueId}")
    public ServiceResponseBean deleteUser(@PathVariable("userUniqueId") String userUniqueId) {
        return this.userService.deleteUser(userUniqueId);
    }

    @GetMapping("/check-if-exist/{userUniqueId}")
    public Boolean existByUserUniqueId(@PathVariable("userUniqueId") String userUniqueId){
        return this.userService.existByUserUniqueId(userUniqueId);
    }

}
