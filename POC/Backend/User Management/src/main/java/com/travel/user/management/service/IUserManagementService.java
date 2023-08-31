package com.travel.user.management.service;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserManagementEntity;

public interface IUserManagementService {

    public ServiceResponseBean findById(Long userManagementId);

    public ServiceResponseBean findAll();

    public ServiceResponseBean addUserManagement(UserManagementEntity userManagementEntity);

    public ServiceResponseBean updateUserManagement(UserManagementEntity userManagementEntity);

    public ServiceResponseBean deleteUserManagement(Long userManagementId);

    public Boolean existByUserManagementUniqueId(String userManagementUniqueId);
}
