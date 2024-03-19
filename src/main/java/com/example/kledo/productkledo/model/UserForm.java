package com.example.kledo.productkledo.model;

import com.example.kledo.productkledo.entity.User;
import com.example.kledo.productkledo.validator.FieldsValueMatch;
import com.example.kledo.productkledo.validator.UniqueUsername;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@FieldsValueMatch(field = "password", fieldMatch = "matchingPassword", message = "Password doesn't match")
public class UserForm {

    @NotNull
    private int id;

    @UniqueUsername
    @Size(min = 5, max = 15, message = "Username should have 5-15 letters")
    @NotBlank
    private String userName;

    @Size(min = 5, max = 15, message = "Password should have 5-15 letters")
    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

    public UserForm(User user) {
        this.setId(user.getId());
        this.setUserName(user.getUserName());
    }
}
