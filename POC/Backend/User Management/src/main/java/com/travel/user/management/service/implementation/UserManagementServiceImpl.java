package com.travel.user.management.service.implementation;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserManagementEntity;
import com.travel.user.management.repository.UserManagementRepository;
import com.travel.user.management.service.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserManagementServiceImpl implements IUserManagementService {

    @Autowired
    private UserManagementRepository userManagementRepository;

    public ServiceResponseBean findById(Long userManagementId) {
        if (userManagementId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("UserManagementId Cannot be null").build();
        Optional<UserManagementEntity> userManagementEntityOptional = this.userManagementRepository.findById(userManagementId);
        if (userManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        UserManagementEntity userManagementEntity = userManagementEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userManagementEntity).build();
    }

    public ServiceResponseBean findAll() {
        List<UserManagementEntity> userManagementEntityList = this.userManagementRepository.findAll();
        if (userManagementEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userManagementEntityList).build();
    }

    public ServiceResponseBean addUserManagement(UserManagementEntity userManagementEntity) {
        if (userManagementEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No UserManagement is null").build();
        userManagementEntity.setUserManagementUniqueId(UUID.randomUUID().toString());
        UserManagementEntity savedUserManagementEntity = this.userManagementRepository.save(userManagementEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUserManagementEntity).build();
    }

    public ServiceResponseBean updateUserManagement(UserManagementEntity userManagementEntity) {
        if (userManagementEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("UserManagement is null").build();
        if (userManagementEntity.getUserManagementId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("UserManagement Id is null").build();
        Optional<UserManagementEntity> userManagementEntityOptional = this.userManagementRepository.findById(userManagementEntity.getUserManagementId());
        if (userManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No Data to update").build();
        userManagementEntity = updateUserManagementDatabaseToNew(userManagementEntity, userManagementEntityOptional.get());
        UserManagementEntity savedUserManagementEntity = this.userManagementRepository.save(userManagementEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUserManagementEntity).build();
    }

    private UserManagementEntity updateUserManagementDatabaseToNew(UserManagementEntity userManagementEntity, UserManagementEntity databaseUserManagementEntity) {
        return databaseUserManagementEntity.builder()
                .userManagementId(userManagementEntity.getUserManagementId())
                .userManagementUniqueId(userManagementEntity.getUserManagementUniqueId() != null ? userManagementEntity.getUserManagementUniqueId() : databaseUserManagementEntity.getUserManagementUniqueId())
                .username(userManagementEntity.getUsername() != null ? userManagementEntity.getUsername() : databaseUserManagementEntity.getUsername())
                .password(userManagementEntity.getPassword() != null ? userManagementEntity.getPassword() : databaseUserManagementEntity.getPassword())
                .email(userManagementEntity.getEmail() != null ? userManagementEntity.getEmail() : databaseUserManagementEntity.getEmail())
                .firstName(userManagementEntity.getFirstName() != null ? userManagementEntity.getFirstName() : databaseUserManagementEntity.getFirstName())
                .lastName(userManagementEntity.getLastName() != null ? userManagementEntity.getLastName() : databaseUserManagementEntity.getLastName())
                .role(userManagementEntity.getRole() != null ? userManagementEntity.getRole() : databaseUserManagementEntity.getRole())
                .status(userManagementEntity.getStatus() != null ? userManagementEntity.getStatus() : databaseUserManagementEntity.getStatus())
                .build();
    }

    public ServiceResponseBean deleteUserManagement(Long userManagementId) {
        if (userManagementId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("UserManagement Id is null").build();
        Optional<UserManagementEntity> userManagementEntityOptional = this.userManagementRepository.findById(userManagementId);
        if(userManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        UserManagementEntity userManagementEntity = userManagementEntityOptional.get();
        userManagementEntity.setStatus(Boolean.FALSE);
        UserManagementEntity savedUserManagement = this.userManagementRepository.save(userManagementEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUserManagement).build();
    }

    @Override
    public Boolean existByUserManagementUniqueId(String userManagementUniqueId) {
        return this.userManagementRepository.existsByUserManagementUniqueId(userManagementUniqueId);
    }
}
