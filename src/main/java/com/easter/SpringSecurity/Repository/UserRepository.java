package com.easter.SpringSecurity.Repository;

import com.easter.SpringSecurity.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
