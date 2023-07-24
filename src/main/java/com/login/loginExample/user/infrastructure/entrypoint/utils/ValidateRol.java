package com.login.loginExample.user.infrastructure.entrypoint.utils;

import com.login.loginExample.user.domain.entity.ERole;
import com.login.loginExample.user.domain.entity.Rol;
import com.login.loginExample.user.infrastructure.entrypoint.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ValidateRol {

    @Autowired
    private IRolRepository iRolRepository;

    public Set<Rol> validateRol(Set<String> setRol) {
        Set<Rol> roles = new HashSet<>();

        if (setRol != null) {
            setRol.forEach(rol -> {
                if (rol.equals("admin")) {
                    Rol adminRole = iRolRepository.findByRolName(ERole.ROL_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                }if (rol.equals("mod")) {
                    Rol modRole = iRolRepository.findByRolName(ERole.ROL_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);
                }
            });
        }else {
            Rol userRol = iRolRepository.findByRolName(ERole.ROL_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRol);
        }
        return roles;
    }
}
