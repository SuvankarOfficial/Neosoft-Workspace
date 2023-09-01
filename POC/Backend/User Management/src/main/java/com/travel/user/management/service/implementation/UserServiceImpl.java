package com.travel.user.management.service.implementation;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserEntity;
import com.travel.user.management.repository.UserRepository;
import com.travel.user.management.service.IUserService;
import com.travel.user.management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public ServiceResponseBean findById(Long userId) {
        if (userId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("UserId Cannot be null").build();
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(userId);
        if (userEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        UserEntity userEntity = userEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userEntity).build();
    }

    public ServiceResponseBean findAll() {
        List<UserEntity> userEntityList = this.userRepository.findAll();
        if (userEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userEntityList).build();
    }

    public ServiceResponseBean addUser(UserEntity userEntity) {
        if (userEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No User is null").build();
        userEntity.setUserUniqueId(UUID.randomUUID().toString());
        UserEntity savedUserEntity = this.userRepository.save(userEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUserEntity).build();
    }

    public ServiceResponseBean updateUser(UserEntity userEntity) {
        if (userEntity == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("User is null").build();
        if (userEntity.getUserId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("User Id is null").build();
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(userEntity.getUserId());
        if (userEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No Data to update").build();
        userEntity = updateUserDatabaseToNew(userEntity, userEntityOptional.get());
        UserEntity savedUserEntity = this.userRepository.save(userEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUserEntity).build();
    }

    private UserEntity updateUserDatabaseToNew(UserEntity userEntity, UserEntity databaseUserEntity) {
        return databaseUserEntity.builder()
                .userId(userEntity.getUserId())
                .userUniqueId(userEntity.getUserUniqueId() != null ? userEntity.getUserUniqueId() : databaseUserEntity.getUserUniqueId())
                .username(userEntity.getUsername() != null ? userEntity.getUsername() : databaseUserEntity.getUsername())
                .password(userEntity.getPassword() != null ? userEntity.getPassword() : databaseUserEntity.getPassword())
                .email(userEntity.getEmail() != null ? userEntity.getEmail() : databaseUserEntity.getEmail())
                .firstName(userEntity.getFirstName() != null ? userEntity.getFirstName() : databaseUserEntity.getFirstName())
                .lastName(userEntity.getLastName() != null ? userEntity.getLastName() : databaseUserEntity.getLastName())
                .role(userEntity.getRole() != null ? userEntity.getRole() : databaseUserEntity.getRole())
                .status(userEntity.getStatus() != null ? userEntity.getStatus() : databaseUserEntity.getStatus())
                .build();
    }

    public ServiceResponseBean deleteUser(Long userId) {
        if (userId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("User Id is null").build();
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(userId);
        if(userEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setStatus(Boolean.FALSE);
        UserEntity savedUser = this.userRepository.save(userEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUser).build();
    }

    public Boolean existByUserUniqueId(String userUniqueId) {
        return this.userRepository.existsByUserUniqueId(userUniqueId);
    }
}
