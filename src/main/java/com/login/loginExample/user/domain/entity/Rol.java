package com.login.loginExample.user.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLES")
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL_NAME")
    private ERole rolName;

    public Rol() {
    }

    public Rol(ERole rolName) {
        this.rolName = rolName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ERole getRolName() {
        return rolName;
    }

    public void setRolName(ERole rolName) {
        this.rolName = rolName;
    }

}
