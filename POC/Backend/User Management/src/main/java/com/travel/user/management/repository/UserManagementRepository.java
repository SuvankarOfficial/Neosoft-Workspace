package com.travel.user.management.repository;

import com.travel.user.management.entity.UserManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagementRepository extends JpaRepository<UserManagementEntity,Long> {

    public Boolean existsByUserManagementUniqueId(String userManagementUniqueId);

}
