package com.revature.services;

import com.revature.models.User;

import com.revature.models.UserDTO;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DTOConverterService dtoConverterService;

    /**
     * This method is used to authenticate the user.
     *
     * @param username          username of the user
     * @param password          password of the user
     * @return                  Optional with a User object representing their data
     */
    @Transactional
    @Override
    public Optional<UserDTO> authenticate(String username, String password) {

        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        return user.map(value -> dtoConverterService.buildUserDTO(value));
    }

    /**
     * This method is used to get all of the users in the database.
     *
     * @return                 a list of all the users
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user: users) {
            userDTOs.add(dtoConverterService.buildUserDTO(user));
        }

        return userDTOs;
    }

    /**
     * This method is used to register a new user account.
     *
     * @param newUser             user data to persist
     * @return                    User object representing their data
     */
    @Override
    public UserDTO registerUser(UserDTO newUser) {
        User user = dtoConverterService.convertDTO(newUser);
        user =  userRepository.save(user);
        return dtoConverterService.buildUserDTO(user);
    }

    /**
     * This method is used to get the information of a single user by their id.
     *
     * @param id               id of the user to find
     * @return                 Optional with a User object representing their data
     */
    @Override
    public Optional<UserDTO> getUserById(int id) {

        Optional<User> opUser = userRepository.findById(id);
        return opUser.map(dtoConverterService::buildUserDTO);
    }

    /**
     * This method is used to modify a user's account information. Since we don't
     * want them updating their passwords we need account for that in the update. This
     * could be expanded to handle all the fields.
     *
     * @param change            the actual changes/updates to the user object
     * @return                  the modified user object gotten from the database
     */
    @Override
    public UserDTO updateUser(UserDTO change) {
        Optional<User> user = userRepository.findById(change.getId());

        if(user.isPresent()) {
            User currentState = user.get();
            User updatedState = dtoConverterService.convertDTO(change);

            updatedState.setPassword(currentState.getPassword());
            updatedState = userRepository.save(updatedState);

            return dtoConverterService.buildUserDTO(updatedState);

        } else {
            return new UserDTO();
        }
    }
}
