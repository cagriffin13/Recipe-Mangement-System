package com.revature.integrations;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.revature.driver.Application.class)
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    UserService userService;

    @Test
    public void testAuthenticate() {
        String username = "username";
        String password = "password";

        Optional<UserDTO> user = userService.authenticate(username, password);

        assertTrue(user.isPresent());
        assertNotEquals(user.get().getId(), 0);
        assertFalse(user.get().isBanned());
        assertFalse(user.get().isAdmin());
        assertEquals(user.get().getFirstName(), "first");
        assertEquals(user.get().getLastName(), "last");
        assertEquals(user.get().getUsername(), username);
        assertEquals(user.get().getPassword(), password);
        assertEquals(user.get().getDateOfBirth(), "12/31/1969");
        assertEquals(user.get().getRegistrationDate(), "12/31/1969");
        assertEquals(user.get().getEmail(), "email@gmail.com");
        assertEquals(user.get().getPhone(), "555-5555");
    }

    @Test
    public void testRegisterUser() {
        UserDTO user = new UserDTO();
        user.setFirstName("Crow");
        user.setLastName("Armblast");
        user.setUsername("C");
        user.setPassword("jurai");
        user.setDateOfBirth("01/01/1970");
        user.setRegistrationDate("01/01/1970");
        user.setEmail("carm@gmail.com");
        user.setPhone("555-5555");
        user.setRecipes(new ArrayList<>());

        UserDTO newUser = userService.registerUser(user);

        assertNotEquals(newUser.getId(), 0);
        assertEquals(newUser.getFirstName(), user.getFirstName());
        assertEquals(newUser.getLastName(), user.getLastName());
        assertEquals(newUser.getUsername(), user.getUsername());
        assertEquals(newUser.getPassword(), user.getPassword());
        assertEquals(newUser.getDateOfBirth(), user.getDateOfBirth());
        assertEquals(newUser.getRegistrationDate(), user.getRegistrationDate());
        assertEquals(newUser.getEmail(), user.getEmail());
        assertEquals(newUser.getPhone(), user.getPhone());
    }

    @Test
    public void testGetAllUsers() {
        List<UserDTO> users = userService.getAllUsers();

        UserDTO user = users.get(0);
        assertNotEquals(user.getId(), 0);
        assertFalse(user.isBanned());
        assertFalse(user.isAdmin());
        assertEquals(user.getFirstName(), "first");
        assertEquals(user.getLastName(), "last");
        assertEquals(user.getUsername(), "username");
        assertEquals(user.getPassword(), "password");
        assertEquals(user.getDateOfBirth(), "12/31/1969");
        assertEquals(user.getRegistrationDate(), "12/31/1969");
        assertEquals(user.getEmail(), "email@gmail.com");
        assertEquals(user.getPhone(), "555-5555");
    }

    @Test
    public void testGetById() {
        Optional<UserDTO> user = userService.getUserById(1);

        assertTrue(user.isPresent());
        assertFalse(user.get().isBanned());
        assertFalse(user.get().isAdmin());
        assertEquals(user.get().getFirstName(), "first");
        assertEquals(user.get().getLastName(), "last");
        assertEquals(user.get().getUsername(), "username");
        assertEquals(user.get().getPassword(), "password");
        assertEquals(user.get().getDateOfBirth(), "12/31/1969");
        assertEquals(user.get().getRegistrationDate(), "12/31/1969");
        assertEquals(user.get().getEmail(), "email@gmail.com");
        assertEquals(user.get().getPhone(), "555-5555");
    }

    @Test
    public void updateUser() {
        UserDTO newData = new UserDTO();
        newData.setId(1);
        newData.setFirstName("first");
        newData.setLastName("last");
        newData.setUsername("username");
        newData.setPassword("password");
        newData.setDateOfBirth("12/31/1969");
        newData.setRegistrationDate("12/31/1969");
        newData.setEmail("email@gmail.com");
        newData.setPhone("888-8888");

        UserDTO user = userService.updateUser(newData);

        assertNotEquals(user.getId(), 0);
        assertEquals(user.getFirstName(), "first");
        assertEquals(user.getLastName(), "last");
        assertEquals(user.getUsername(), "username");
        assertEquals(user.getPassword(), "password");
        assertEquals(user.getDateOfBirth(), "12/31/1969");
        assertEquals(user.getRegistrationDate(), "12/31/1969");
        assertEquals(user.getEmail(), "email@gmail.com");
        assertEquals(user.getPhone(), "888-8888");
    }
}
