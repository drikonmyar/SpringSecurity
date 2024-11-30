package com.easter.SpringSecurity.Repository;

import com.easter.SpringSecurity.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query(value = "select * from user_role where user_id = :userId and role_id = :roleId", nativeQuery = true)
    Optional<UserRole> getUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

    @Query(value = "select role_id from user_role where user_id = :userId", nativeQuery = true)
    List<Integer> getUserRoleByUserId(@Param("userId") int userId);

}
