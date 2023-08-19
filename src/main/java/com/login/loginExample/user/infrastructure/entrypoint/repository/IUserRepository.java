package com.login.loginExample.user.infrastructure.entrypoint.repository;

import com.login.loginExample.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository <User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User findUserById(long id);
}
