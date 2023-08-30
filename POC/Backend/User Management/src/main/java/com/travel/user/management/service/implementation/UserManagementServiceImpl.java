package com.travel.user.management.service.implementation;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserManagementEntity;
import com.travel.user.management.repository.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserManagementServiceImpl {

    @Autowired
    private UserManagementRepository userManagementRepository;

    public ServiceResponseBean findById(Long userManagementId){
        if(userManagementId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("experienceListingId Cannot be null").build();
        Optional<UserManagementEntity> userManagementEntityOptional = this.userManagementRepository.findById(userManagementId);
        if(userManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        UserManagementEntity userManagementEntity = userManagementEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userManagementEntity).build();
    }

    public ServiceResponseBean findAll(){
        List<UserManagementEntity> userManagementEntityList = this.userManagementRepository.findAll();
        if (userManagementEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userManagementEntityList).build();
    }

    public ServiceResponseBean addUserManagement(UserManagementEntity userManagementEntity){
        if (userManagementEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No ExperienceListing is null").build();
        UserManagementEntity savedUserManagementEntity = this.userManagementRepository.save(userManagementEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUserManagementEntity).build();
    }

    public ServiceResponseBean updateUserManagement(UserManagementEntity userManagementEntity){
        if(userManagementEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("ExperienceListing is null").build();
        if(userManagementEntity.getUserManagementId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("ExperienceListing Id is null").build();
        Optional<UserManagementEntity> userManagementEntityOptional = this.userManagementRepository.findById(userManagementEntity.getUserManagementId());
        if(userManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No Data to update").build();
        updateUserManagementDatabaseToNew(userManagementEntity, userManagementEntityOptional.get());
    }

    private void updateUserManagementDatabaseToNew(UserManagementEntity userManagementEntity, UserManagementEntity databaseUserManagementEntity) {
        
    }
}
