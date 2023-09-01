package com.travel.user.management.repository;

import com.travel.user.management.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public Boolean existsByUserUniqueId(String userManagementUniqueId);

}
