package com.tawme.userservice.repository;

import com.tawme.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByPhoneNumber(String phoneNumber);
}
