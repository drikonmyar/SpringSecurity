package com.easter.SpringSecurity.Repository;

import com.easter.SpringSecurity.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select * from roles where role = :role", nativeQuery = true)
    Optional<Role> getRoleByName(@Param("role") String role);

    @Query("SELECT r.role FROM UserRole ur JOIN Role r ON ur.roleId = r.id WHERE ur.userId = :userId")
    List<String> findRolesByUserId(int userId);

}
