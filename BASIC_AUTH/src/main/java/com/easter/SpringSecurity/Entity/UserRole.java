package com.easter.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int userId;
    int roleId;
}
