package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserService {

    /**
     * This method is used to authenticate the user.
     *
     * @param username          username of the user
     * @param password          password of the user
     * @return                  Optional with a User object representing their data
     */
    public Optional<UserDTO> authenticate(String username, String password);

    /**
     * This method is used to create a new user account.
     *
     * @param user             user data to persist
     * @return                 User object representing their data
     */
    public UserDTO registerUser(UserDTO user);

    /**
     * This method is used to get the information of a single user by their id.
     *
     * @param id               id of the user to find
     * @return                 Optional with a User object representing their data
     */
    public Optional<UserDTO> getUserById(int id);

    /**
     * This method method is used to get all of the users in the database.
     *
     * @return                 a list of all the users
     */
    public List<UserDTO> getAllUsers();

    /**
     * This method is used to modify a user's account information.
     *
     * @param user             the modified user object
     * @return                 the modified user object gotten from the database
     */
    public UserDTO updateUser(UserDTO user);
}
