package com.revature.integrations;

import com.revature.models.RecipeDTO;
import com.revature.models.UserDTO;
import com.revature.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = com.revature.driver.Application.class)
@Transactional
public class AdminServiceIntegrationTest {

    @Autowired
    AdminService adminService;

    @Test
    public void testBanUser() {
        UserDTO user = new UserDTO();

        user.setId(1);
        user.setBanned(false);
        user.setAdmin(false);
        user.setFirstName("first");
        user.setLastName("last");
        user.setDateOfBirth("12/31/1969");
        user.setRegistrationDate("12/31/1969");
        user.setEmail("email@gmail.com");
        user.setPhone("555-5555");

        assertTrue(adminService.banUser(user));
    }

    @Test
    public void testUnbanUser() {
        UserDTO user = new UserDTO();

        user.setId(1);
        user.setBanned(true);
        user.setAdmin(false);
        user.setFirstName("first");
        user.setLastName("last");
        user.setDateOfBirth("12/31/1969");
        user.setRegistrationDate("12/31/1969");
        user.setEmail("email@gmail.com");
        user.setPhone("555-5555");

        assertTrue(adminService.unbanUser(user));
    }

    @Test
    public void testApproveRecipe() {
        RecipeDTO recipeIn = new RecipeDTO();

        UserDTO u = new UserDTO();
        u.setId(1);

        recipeIn.setId(1);
        recipeIn.setApproved(false);
        recipeIn.setName("curry");
        recipeIn.setDescription("yummy");
        recipeIn.setCookTime("40 min");
        recipeIn.setServings(4);
        recipeIn.setInstructions(new ArrayList<>());
        recipeIn.setMedia(new ArrayList<>());
        recipeIn.setIngredients(new ArrayList<>());
        recipeIn.setUser(u);

        assertTrue(adminService.approveRecipe(recipeIn));
    }

    @Test
    public void testDisapproveRecipe() {
        RecipeDTO recipeIn = new RecipeDTO();

        UserDTO u = new UserDTO();
        u.setId(1);

        recipeIn.setId(1);
        recipeIn.setDisapproved(false);
        recipeIn.setName("curry");
        recipeIn.setDescription("yummy");
        recipeIn.setCookTime("40 min");
        recipeIn.setServings(4);
        recipeIn.setInstructions(new ArrayList<>());
        recipeIn.setMedia(new ArrayList<>());
        recipeIn.setIngredients(new ArrayList<>());
        recipeIn.setUser(u);

        assertTrue(adminService.disapproveRecipe(recipeIn));
    }
}
