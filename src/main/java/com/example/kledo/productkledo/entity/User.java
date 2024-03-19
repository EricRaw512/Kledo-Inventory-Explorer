package com.example.kledo.productkledo.entity;

import com.example.kledo.productkledo.model.UserForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(UserForm userForm, String encryptedPassword, Role role) {
        this.userName = userForm.getUserName();
        this.password = encryptedPassword;
        this.role = role;
    }
}
