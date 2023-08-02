package com.monolith.controller;

import com.monolith.ResponseBean.ResponseBean;
import com.monolith.beans.RequestBean.UserRequestBean;
import com.monolith.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseBean addUser(@RequestBody UserRequestBean user){
        return userService.addUser(user);
    }

    @GetMapping("/")
    public ResponseBean findAllUsers(){
        return userService.findAllUser();
    }

    @GetMapping("/{user_id}")
    public ResponseBean findUserById(@PathVariable("user_id") Long userId){
        return userService.findUserById(userId);
    }

    @PutMapping("/")
    public ResponseBean updateUser(@RequestBody UserRequestBean user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{user_id}")
    public ResponseBean deleteUser(@PathVariable("user_id") Long userId){
        return userService.deleteUser(userId);
    }

}
