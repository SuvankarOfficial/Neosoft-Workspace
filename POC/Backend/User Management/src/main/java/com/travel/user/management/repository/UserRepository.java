package com.travel.user.management.repository;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public Boolean existsByUserUniqueId(String userManagementUniqueId);

    Optional<UserEntity> findByUserUniqueId(String userUniqueId);

    Boolean existsByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);
}
