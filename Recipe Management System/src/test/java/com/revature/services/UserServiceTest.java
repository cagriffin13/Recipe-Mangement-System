package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = com.revature.driver.Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testAuthenticate() {
        User user = new User();
        user.setId(1);
        user.setAdmin(false);
        user.setFirstName("first");
        user.setLastName("last");
        user.setDateOfBirth(0);
        user.setRegistrationDate(0);
        user.setEmail("email@gmail.com");
        user.setPhone("555-5555");

        Recipe recipe = new Recipe();
        recipe.setRecipe_id(1);
        recipe.setName("curry");
        recipe.setDescription("yummy");
        recipe.setCook_time("40 min");
        recipe.setServings(4);
        recipe.setApproved(true);
        recipe.setInstruction(new ArrayList<>());
        recipe.setMedia(new ArrayList<>());
        recipe.setIngredient(new ArrayList<>());
        recipe.setUser(user);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        user.setRecipes(recipes);

        when(userRepository.findByUsernameAndPassword("username", "password")).thenReturn(Optional.of(user));

        Optional<UserDTO> userDTO = userService.authenticate("username", "password");

        UserDTO u = userDTO.orElse(new UserDTO());
        assertEquals(u.getId(), user.getId());
        assertEquals(u.isAdmin(), user.isAdmin());
        assertEquals(u.getFirstName(), user.getFirstName());
        assertEquals(u.getLastName(), user.getLastName());
        assertEquals(u.getEmail(), user.getEmail());
        assertEquals(u.getPhone(), user.getPhone());

        assertEquals(u.getRecipes().size(), 1);

        RecipeDTO r = u.getRecipes().get(0);
        assertEquals(r.getId(), recipe.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe.getMedia().size());
        assertEquals(r.getUser().getId(), recipe.getUser().getId());
    }

    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setId(1);
        user.setAdmin(false);
        user.setFirstName("first");
        user.setLastName("last");
        user.setDateOfBirth(0);
        user.setRegistrationDate(0);
        user.setEmail("email@gmail.com");
        user.setPhone("555-5555");

        Recipe recipe = new Recipe();
        recipe.setRecipe_id(1);
        recipe.setName("curry");
        recipe.setDescription("yummy");
        recipe.setCook_time("40 min");
        recipe.setServings(4);
        recipe.setApproved(true);
        recipe.setInstruction(new ArrayList<>());
        recipe.setMedia(new ArrayList<>());
        recipe.setIngredient(new ArrayList<>());
        recipe.setUser(user);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        user.setRecipes(recipes);

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTO = userService.getAllUsers();
        UserDTO u = userDTO.get(0);

        assertEquals(u.getId(), user.getId());
        assertEquals(u.isAdmin(), user.isAdmin());
        assertEquals(u.getFirstName(), user.getFirstName());
        assertEquals(u.getLastName(), user.getLastName());
        assertEquals(u.getEmail(), user.getEmail());
        assertEquals(u.getPhone(), user.getPhone());

        assertEquals(u.getRecipes().size(), 1);

        RecipeDTO r = u.getRecipes().get(0);
        assertEquals(r.getId(), recipe.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe.getMedia().size());
        assertEquals(r.getUser().getId(), recipe.getUser().getId());
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setId(1);
        user.setAdmin(false);
        user.setFirstName("first");
        user.setLastName("last");
        user.setDateOfBirth(0);
        user.setRegistrationDate(0);
        user.setEmail("email@gmail.com");
        user.setPhone("555-5555");

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDTO u = new UserDTO();
        u.setId(1);
        u.setAdmin(false);
        u.setFirstName("first");
        u.setLastName("last");
        u.setDateOfBirth("01/01/1970");
        u.setRegistrationDate("01/01/1970");
        u.setEmail("email@gmail.com");
        u.setPhone("555-5555");

        u = userService.registerUser(u);

        assertEquals(u.getId(), user.getId());
        assertEquals(u.isAdmin(), user.isAdmin());
        assertEquals(u.getFirstName(), user.getFirstName());
        assertEquals(u.getLastName(), user.getLastName());
        assertEquals(u.getEmail(), user.getEmail());
        assertEquals(u.getPhone(), user.getPhone());

        assertEquals(u.getRecipes().size(), 0);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1);
        user.setAdmin(false);
        user.setFirstName("first");
        user.setLastName("last");
        user.setDateOfBirth(0);
        user.setRegistrationDate(0);
        user.setEmail("email@gmail.com");
        user.setPhone("555-5555");

        Recipe recipe = new Recipe();
        recipe.setRecipe_id(1);
        recipe.setName("curry");
        recipe.setDescription("yummy");
        recipe.setCook_time("40 min");
        recipe.setServings(4);
        recipe.setApproved(true);
        recipe.setInstruction(new ArrayList<>());
        recipe.setMedia(new ArrayList<>());
        recipe.setIngredient(new ArrayList<>());
        recipe.setUser(user);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        user.setRecipes(recipes);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<UserDTO> userDTO = userService.getUserById(1);

        UserDTO u = userDTO.orElse(new UserDTO());
        assertEquals(u.getId(), user.getId());
        assertEquals(u.isAdmin(), user.isAdmin());
        assertEquals(u.getFirstName(), user.getFirstName());
        assertEquals(u.getLastName(), user.getLastName());
        assertEquals(u.getEmail(), user.getEmail());
        assertEquals(u.getPhone(), user.getPhone());

        assertEquals(u.getRecipes().size(), 1);

        RecipeDTO r = u.getRecipes().get(0);
        assertEquals(r.getId(), recipe.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe.getMedia().size());
        assertEquals(r.getUser().getId(), recipe.getUser().getId());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setAdmin(false);
        user.setFirstName("first");
        user.setLastName("last");
        user.setDateOfBirth(0);
        user.setRegistrationDate(0);
        user.setEmail("email@gmail.com");
        user.setPhone("555-5555");

        Recipe recipe = new Recipe();
        recipe.setRecipe_id(1);
        recipe.setName("curry");
        recipe.setDescription("yummy");
        recipe.setCook_time("40 min");
        recipe.setServings(4);
        recipe.setApproved(true);
        recipe.setInstruction(new ArrayList<>());
        recipe.setMedia(new ArrayList<>());
        recipe.setIngredient(new ArrayList<>());
        recipe.setUser(user);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        user.setRecipes(recipes);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDTO u = new UserDTO();
        u.setId(1);
        u.setAdmin(false);
        u.setFirstName("first");
        u.setLastName("last");
        u.setDateOfBirth("01/01/1970");
        u.setRegistrationDate("01/01/1970");
        u.setEmail("email@gmail.com");
        u.setPhone("555-5555");

        RecipeDTO r = new RecipeDTO();
        r.setId(1);
        r.setName("curry");
        r.setDescription("yummy");
        r.setCookTime("40 min");
        r.setServings(4);
        r.setApproved(true);
        r.setInstructions(new ArrayList<>());
        r.setMedia(new ArrayList<>());
        r.setIngredients(new ArrayList<>());
        r.setUser(u);

        List<RecipeDTO> reps = new ArrayList<>();
        reps.add(r);
        u.setRecipes(reps);

        u = userService.updateUser(u);

        assertEquals(u.getId(), user.getId());
        assertEquals(u.isAdmin(), user.isAdmin());
        assertEquals(u.getFirstName(), user.getFirstName());
        assertEquals(u.getLastName(), user.getLastName());
        assertEquals(u.getEmail(), user.getEmail());
        assertEquals(u.getPhone(), user.getPhone());

        assertEquals(u.getRecipes().size(), 1);

        r = u.getRecipes().get(0);
        assertEquals(r.getId(), recipe.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe.getMedia().size());
        assertEquals(r.getUser().getId(), recipe.getUser().getId());
    }
}
