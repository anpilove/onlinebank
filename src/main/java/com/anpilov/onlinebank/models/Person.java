package com.anpilov.onlinebank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//CREATE TABLE Person (
//        id int (15) NOT NULL AUTO_INCREMENT,
//        first_name varchar(255),
//        last_name varchar(255),
//        data_birth date,
//        email varchar(255) NOT NULL,
//        password varchar(255) NOT NULL,
//        role varchar(255) NOT NULL,
//        PRIMARY KEY (ID)
//        ) ENGINE=InnODB DEFAULT CHARSET=UTF8;


@Entity
@Table(name="Person", schema="OnlineBank")
public class Person implements UserDetails {

    // Можно использовать регулярные выражения @Pattern
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_birth")
    private String dataBirth;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private PersonRole role;



    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public @NotNull String getDataBirth() {
        return dataBirth;
    }

    public void setDataBirth(@NotNull String dataBirth) {
        this.dataBirth = dataBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonRole getRole() {
        return role;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }
}
