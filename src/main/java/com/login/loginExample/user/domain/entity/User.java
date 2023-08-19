package com.login.loginExample.user.domain.entity;


import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private long id;
    @Column(name= "NAME")
    private String name;
    @Column(name= "LASTNAME")
    private String lastname;
    @Column(name= "PASSWORD")
    private String password;
    @Column (name= "EMAIL", unique = true)
    private String email;
    @Column(name = "CREATEDATE")
    private Date createDate;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
