package com.login.loginExample.user.domain.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OTP")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "CODE")
    private String code;

    @CreationTimestamp
    @Column(name = "CREATEDATE")
    private Date createDate;
}
