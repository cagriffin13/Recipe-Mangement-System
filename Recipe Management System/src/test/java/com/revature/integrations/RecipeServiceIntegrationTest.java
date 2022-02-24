package com.revature.integrations;

import com.revature.models.RecipeDTO;
import com.revature.models.UserDTO;
import com.revature.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.revature.driver.Application.class)
@Transactional
public class RecipeServiceIntegrationTest {

    @Autowired
    RecipeService recipeService;

    @Test
    public void testCreateRecipe() {
        UserDTO u = new UserDTO();
        u.setId(1);
        RecipeDTO r = new RecipeDTO();
        r.setName("new recipe");
        r.setCookTime("3 hrs");
        r.setDescription("my new recipe is tasty");
        r.setUser(u);
        r.setServings(4);
        r.setInstructions(new ArrayList<>());
        r.setMedia(new ArrayList<>());
        r.setIngredients(new ArrayList<>());

        r = recipeService.createRecipe(r);

        assertNotEquals(r.getId(), 0);
        assertEquals(r.getName(), r.getName());
    }

    @Test
    public void testDeleteRecipe() {
        recipeService.deleteRecipe(1);

        RecipeDTO op = recipeService.getRecipeById(1);

        assertEquals(op.getId(), 0);
    }

    @Test
    public void testGetRecipeById() {
        RecipeDTO recipe = recipeService.getRecipeById(1);

        assertEquals(recipe.getName(), "recipe");
    }

    @Test
    public void testGetAll() {
        List<RecipeDTO> recipes = recipeService.getAll();

        assertEquals(recipes.size(), 2);
        assertEquals(recipes.get(0).getName(), "recipe");
        assertEquals(recipes.get(1).getName(), "recipe2");
    }

    @Test
    public void testUpdateRecipe() {
        UserDTO u = new UserDTO();
        u.setId(1);
        RecipeDTO r = new RecipeDTO();
        r.setName("new recipe");
        r.setCookTime("3 hrs");
        r.setDescription("my new recipe is tasty");
        r.setUser(u);
        r.setServings(4);
        r.setInstructions(new ArrayList<>());
        r.setMedia(new ArrayList<>());
        r.setIngredients(new ArrayList<>());

        r = recipeService.updateRecipe(r);

        assertNotEquals(r.getId(), 0);
        assertEquals(r.getName(), r.getName());
    }

    @Test
    public void testSeachRecipe() {
        List<RecipeDTO> recipes = recipeService.search("2");

        assertEquals(recipes.size(), 1);
        assertEquals(recipes.get(0).getName(), "recipe2");
    }
}
