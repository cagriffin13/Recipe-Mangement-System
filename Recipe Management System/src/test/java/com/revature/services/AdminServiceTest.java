package com.revature.services;

import com.revature.models.Recipe;
import com.revature.models.RecipeDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repositories.RecipeRepository;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = com.revature.driver.Application.class)
public class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    public void testBanUser() {
        UserDTO userIn = new UserDTO();
        User userOut = new User();

        userIn.setId(1);
        userIn.setAdmin(false);
        userIn.setFirstName("first");
        userIn.setLastName("last");
        userIn.setDateOfBirth("12/31/1969");
        userIn.setRegistrationDate("12/31/1969");
        userIn.setEmail("email@gmail.com");
        userIn.setPhone("555-5555");

        userIn.setBanned(false);
        userOut.setBanned(true);

        when(userRepository.save(Mockito.any(User.class))).thenReturn(userOut);

        assertTrue(adminService.banUser(userIn));
    }

    @Test
    public void testUnbanUser() {
        UserDTO userIn = new UserDTO();
        User userOut = new User();

        userIn.setId(1);
        userIn.setAdmin(false);
        userIn.setFirstName("first");
        userIn.setLastName("last");
        userIn.setDateOfBirth("12/31/1969");
        userIn.setRegistrationDate("12/31/1969");
        userIn.setEmail("email@gmail.com");
        userIn.setPhone("555-5555");

        userIn.setBanned(true);
        userOut.setBanned(false);

        when(userRepository.save(Mockito.any(User.class))).thenReturn(userOut);

        assertTrue(adminService.unbanUser(userIn));
    }

    @Test
    public void testApproveRecipe() {
        RecipeDTO recipeIn = new RecipeDTO();
        Recipe recipeOut = new Recipe();

        UserDTO u = new UserDTO();
        u.setId(1);

        recipeIn.setId(1);
        recipeIn.setName("curry");
        recipeIn.setDescription("yummy");
        recipeIn.setCookTime("40 min");
        recipeIn.setServings(4);
        recipeIn.setApproved(true);
        recipeIn.setInstructions(new ArrayList<>());
        recipeIn.setMedia(new ArrayList<>());
        recipeIn.setIngredients(new ArrayList<>());
        recipeIn.setUser(u);

        recipeIn.setApproved(false);
        recipeOut.setApproved(true);

        when(recipeRepository.save(Mockito.any(Recipe.class))).thenReturn(recipeOut);

        assertTrue(adminService.approveRecipe(recipeIn));
    }

    @Test
    public void testDisapproveRecipe() {
        RecipeDTO recipeIn = new RecipeDTO();
        Recipe recipeOut = new Recipe();

        UserDTO u = new UserDTO();
        u.setId(1);

        recipeIn.setId(1);
        recipeIn.setName("curry");
        recipeIn.setDescription("yummy");
        recipeIn.setCookTime("40 min");
        recipeIn.setServings(4);
        recipeIn.setApproved(true);
        recipeIn.setInstructions(new ArrayList<>());
        recipeIn.setMedia(new ArrayList<>());
        recipeIn.setIngredients(new ArrayList<>());
        recipeIn.setUser(u);

        recipeIn.setDisapproved(false);
        recipeOut.setDisapproved(true);

        when(recipeRepository.save(Mockito.any(Recipe.class))).thenReturn(recipeOut);

        assertTrue(adminService.disapproveRecipe(recipeIn));
    }
}
