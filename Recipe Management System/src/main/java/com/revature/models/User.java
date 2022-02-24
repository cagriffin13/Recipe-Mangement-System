package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private int id;

    private boolean banned = false;

    private boolean admin = false;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(length = 50)
    private String username;

    @Column(length = 50)
    private String password;

    @Column(name = "date_of_birth")
    private long dateOfBirth;

    @Column(name = "registration_date")
    private long registrationDate;

    @Column(length = 100)
    private String email;

    @Column(length = 15)
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes;
}
