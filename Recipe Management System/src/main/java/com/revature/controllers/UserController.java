package com.revature.controllers;


import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    ResponseEntity<UserDTO> login(@RequestBody Map<String, String> authMap) {
        Optional<UserDTO> user = userService.authenticate(authMap.get("username"), authMap.get("password"));

        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        } else if (user.get().isBanned()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity.of(user);
        }
    }

    /**
     * Method retrieves a Users data from the database/backend by an id.
     *
     * @param id    The id of the User to be retrieved
     * @return      Returns the retrieved User data from the database.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        Optional<UserDTO> user = userService.getUserById(Integer.parseInt(id));

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * This method is used to register a new user account.
     *
     * @param user             user data to persist
     * @return                 User object representing their data
     */
    @PostMapping(value = "/register")
    public UserDTO addUser(@RequestBody UserDTO user) {
        return userService.registerUser(user);
    }

    /**
     * This method is used to get all users in the database.
     *
     * @return                 a list of all the users
     */
    @GetMapping(value = "/users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    /**
     * This method is used to update the user.
     * @param user            user data to persist
     * @return                the updated user data
     */
    @PutMapping(value="/users")
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return userService.updateUser(user);
    }

}
