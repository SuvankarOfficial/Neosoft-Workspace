package com.travel.user.management.service;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserEntity;

public interface IUserService {

    public ServiceResponseBean findById(Long userId);

    public ServiceResponseBean findAll();

    public ServiceResponseBean addUser(UserEntity userEntity);

    public ServiceResponseBean updateUser(UserEntity userEntity);

    public ServiceResponseBean deleteUser(Long userId);

    public Boolean existByUserUniqueId(String userUniqueId);
}
