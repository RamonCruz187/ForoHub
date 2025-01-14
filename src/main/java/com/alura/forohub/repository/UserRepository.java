package com.alura.forohub.repository;

import com.alura.forohub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
    User getUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
