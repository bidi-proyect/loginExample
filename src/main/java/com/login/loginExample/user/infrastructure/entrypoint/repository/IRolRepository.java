package com.login.loginExample.user.infrastructure.entrypoint.repository;


import com.login.loginExample.user.domain.entity.ERole;
import com.login.loginExample.user.domain.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolName(ERole rolName);
}
