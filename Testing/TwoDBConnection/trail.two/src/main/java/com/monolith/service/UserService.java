package com.monolith.service;

import com.monolith.ResponseBean.ResponseBean;
import com.monolith.beans.RequestBean.UserRequestBean;
import com.monolith.entity.user.User;
import com.monolith.enums.Status;
import com.monolith.mapper.UserMapper;
import com.monolith.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public ResponseBean addUser(UserRequestBean userRequestBean){
        Optional<User> existingUser = userRepository.findByUserName(userRequestBean.getUserName());
        if(existingUser.isEmpty()) {
            User user = userMapper.requestEntityMapperCreate(userRequestBean);
            user.setStatus(Status.Active);
            user = userRepository.saveAndFlush(user);
            return ResponseBean.builder().message("User is saved").status(Boolean.TRUE).data(userMapper.entityResponseMapper(user)).build();
        }
        else
            return ResponseBean.builder().message("User Name Already exist").status(Boolean.TRUE).build();
    }

    public ResponseBean findAllUser(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty())
            return ResponseBean.builder().status(Boolean.TRUE).message("No data Found").build();

        return ResponseBean.builder().status(Boolean.TRUE).data(users.stream().map(data-> userMapper.entityResponseMapper(data))).build();
    }

    public ResponseBean findUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
            return ResponseBean.builder().status(Boolean.TRUE).data(userMapper.entityResponseMapper(user.get())).build();
        return ResponseBean.builder().status(Boolean.TRUE).message("No data Found").build();
    }

    public ResponseBean updateUser(UserRequestBean userRequestBean){
        Optional<User> optionalUser = userRepository.findById(userRequestBean.getUserId());
        if(optionalUser.isEmpty())
            return ResponseBean.builder().message("No User found to update").status(Boolean.TRUE).build();
        User user = userMapper.requestEntityMapperUpdate(userRequestBean, optionalUser.get());
        user = userRepository.saveAndFlush(user);
        return ResponseBean.builder().message("User has been updated Successfully").status(Boolean.TRUE).data(userMapper.entityResponseMapper(user)).build();
    }

    public ResponseBean deleteUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())
            return ResponseBean.builder().message("No User found to delete").status(Boolean.TRUE).build();
        User user = optionalUser.get();
        user.setStatus(Status.Deleted);
        user = userRepository.saveAndFlush(user);
        return ResponseBean.builder().message("User has been Deleted Successfull").status(Boolean.TRUE).data(userMapper.entityResponseMapper(user)).build();
    }

}
