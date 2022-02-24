package com.revature.models;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private int id;
    private boolean banned;
    private boolean admin;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String dateOfBirth;
    private String registrationDate;
    private String email;
    private String phone;

    private List<RecipeDTO> recipes;
}
