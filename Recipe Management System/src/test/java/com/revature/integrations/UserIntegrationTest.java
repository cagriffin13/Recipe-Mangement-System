package com.revature.integrations;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.revature.driver.Application.class)
@Transactional
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsernameAndPassword() {
        Optional<User> opUser = userRepository.findByUsernameAndPassword("username", "password");

        assertTrue(opUser.isPresent());
    }

    @Test
    public void testRegisterNewUser() {
        User user = new User();
        user.setEmail("newemail@gmail.com");
        user.setUsername("new");
        user.setPassword("pass");
        user.setDateOfBirth(0);
        user.setRegistrationDate(0);
        user.setFirstName("firsty");
        user.setLastName("lasty");
        user.setPhone("555-8888");

        User u = userRepository.save(user);

        assertNotEquals(u.getId(), 0);
        assertEquals(u.getUsername(), user.getUsername());
        assertEquals(u.getPassword(), user.getPassword());
    }

    @Test
    public void findUserById() {
        Optional<User> opUser = userRepository.findById(1);
        assertTrue(opUser.isPresent());
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();

        assertEquals(users.size(), 1);
    }

    @Test
    public void testUpdateUser() {
        Optional<User> opUser = userRepository.findById(1);
        assertTrue(opUser.isPresent());

        User user = opUser.get();

        user.setBanned(true);

        User u = userRepository.save(user);

        assertTrue(u.isBanned());
    }
}
