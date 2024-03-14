package com.example.kledo.productkledo.model;

import com.example.kledo.productkledo.entity.User;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {

    @NotNull
    @Min(value = 1)
    private int id;

    //@UniqueUsername(groups = CreateUser.class)
    @Size(min = 5, max = 15, message = "Username should have 5-15 letters")
    @NotBlank
    private String userName;

    @Size(min = 5, max = 15, message = "Password should have 5-15 letters")
    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

    @Email(message = "Email not valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    public UserForm(User user) {
        this.setId(user.getId());
        this.setUserName(user.getUserName());
        this.setEmail(user.getEmail());
    }
}
