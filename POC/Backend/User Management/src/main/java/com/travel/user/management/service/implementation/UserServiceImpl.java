package com.travel.user.management.service.implementation;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserEntity;
import com.travel.user.management.feignclient.OtpService;
import com.travel.user.management.global.exceptional.CannotBeNullException;
import com.travel.user.management.global.exceptional.DoesNotExistException;
import com.travel.user.management.global.exceptional.InvalidEmailIdException;
import com.travel.user.management.global.exceptional.InvalidOtpException;
import com.travel.user.management.repository.UserRepository;
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

    @Autowired
    private OtpService otpService;

    public ServiceResponseBean findById(String userUniqueId) {
        if (userUniqueId == null)
            throw new CannotBeNullException("User Id");
        Optional<UserEntity> userEntityOptional = this.userRepository.findByUserUniqueId(userUniqueId);
        if (userEntityOptional.isEmpty())
            throw new DoesNotExistException("User Id");
        UserEntity userEntity = userEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userEntity).build();
    }

    public ServiceResponseBean findAll() {
        List<UserEntity> userEntityList = this.userRepository.findAll();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userEntityList).build();
    }

    public ServiceResponseBean addUser(UserEntity userEntity) {
        if (userEntity == null)
            throw new CannotBeNullException("User");
        userEntity.setUserUniqueId(UUID.randomUUID().toString());
        UserEntity savedUserEntity = this.userRepository.save(userEntity);
        Boolean sendStatus = this.otpService.sendOtp(userEntity.getEmail());
        if(!sendStatus)
            throw new InvalidEmailIdException();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUserEntity).build();
    }

    public ServiceResponseBean updateUser(UserEntity userEntity) {
        if (userEntity == null)
            throw new CannotBeNullException("User");
        if (userEntity.getUserUniqueId() == null || userEntity.getUserUniqueId().isBlank())
            throw new CannotBeNullException("User Id");
        Optional<UserEntity> userEntityOptional = this.userRepository.findByUserUniqueId(userEntity.getUserUniqueId());
        if (userEntityOptional.isEmpty())
            throw new DoesNotExistException("User Id");
        userEntity = updateUserDatabaseToNew(userEntity, userEntityOptional.get());
        UserEntity savedUserEntity = this.userRepository.save(userEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUserEntity).build();
    }

    private UserEntity updateUserDatabaseToNew(UserEntity userEntity, UserEntity databaseUserEntity) {
        return databaseUserEntity.builder()
                .userId(databaseUserEntity.getUserId())
                .userUniqueId(userEntity.getUserUniqueId())
                .username(userEntity.getUsername() != null ? userEntity.getUsername().toLowerCase() : databaseUserEntity.getUsername())
                .password(userEntity.getPassword() != null ? userEntity.getPassword() : databaseUserEntity.getPassword())
                .email(userEntity.getEmail() != null ? userEntity.getEmail() : databaseUserEntity.getEmail())
                .firstName(userEntity.getFirstName() != null ? userEntity.getFirstName() : databaseUserEntity.getFirstName())
                .lastName(userEntity.getLastName() != null ? userEntity.getLastName() : databaseUserEntity.getLastName())
                .role(userEntity.getRole() != null ? userEntity.getRole() : databaseUserEntity.getRole())
                .status(userEntity.getStatus() != null ? userEntity.getStatus() : databaseUserEntity.getStatus())
                .build();
    }

    public ServiceResponseBean deleteUser(String userUniqueId) {
        if (userUniqueId == null)
            throw new CannotBeNullException("User Id");
        Optional<UserEntity> userEntityOptional = this.userRepository.findByUserUniqueId(userUniqueId);
        if(userEntityOptional.isEmpty())
            throw new DoesNotExistException("User Id");
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setStatus(Boolean.FALSE);
        UserEntity savedUser = this.userRepository.save(userEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedUser).build();
    }

    public Boolean existByUserUniqueId(String userUniqueId) {
        return this.userRepository.existsByUserUniqueId(userUniqueId);
    }

    @Override
    public ServiceResponseBean checkCredential(String username, String password) {
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(this.userRepository.existsByUsernameAndPassword(username,password)).build();
    }

    @Override
    public ServiceResponseBean completeProfile(UserEntity userEntity, String otp) {
        if (userEntity == null)
            throw new CannotBeNullException("User");
        Boolean verified = this.otpService.verifyOtp(userEntity.getEmail(), otp);
        if(!verified)
            throw new InvalidOtpException();
        if (userEntity.getUserUniqueId() == null)
            throw new CannotBeNullException("User Id");
        Optional<UserEntity> userEntityOptional = this.userRepository.findByUserUniqueId(userEntity.getUserUniqueId());
        if (userEntityOptional.isEmpty())
            throw new DoesNotExistException("User Id");
        UserEntity databaseUserEntity = userEntityOptional.get();
        databaseUserEntity.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build();
        userEntity = this.userRepository.save(databaseUserEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(userEntity).build();
    }

    @Override
    public ServiceResponseBean existUsername(String username) {
        System.out.println(username);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(this.userRepository.existsByUsername(username.toLowerCase())).build();
    }
}
