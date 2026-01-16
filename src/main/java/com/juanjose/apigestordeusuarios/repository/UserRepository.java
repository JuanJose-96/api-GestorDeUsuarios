package com.juanjose.apigestordeusuarios.repository;

import com.juanjose.apigestordeusuarios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}
