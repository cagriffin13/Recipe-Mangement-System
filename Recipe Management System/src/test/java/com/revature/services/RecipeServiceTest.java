package com.revature.services;


import com.revature.models.Recipe;
import com.revature.models.RecipeDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repositories.RecipeRepository;
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
public class RecipeServiceTest {

    @Autowired
    RecipeService rs;

    @MockBean
    RecipeRepository rp;


    @Test
    void testCreateRecipe() {
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

        when(rp.save(Mockito.any(Recipe.class))).thenReturn(recipe);

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

        r = rs.createRecipe(r);

        assertEquals(r.getId(), recipe.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe.getMedia().size());
        assertEquals(r.getUser().getId(), recipe.getUser().getId());
    }

    @Test
    public void testDeleteRecipe() {
        User user = new User();
        user.setId(1);
        Recipe recipe = new Recipe();
        recipe.setUser(user);

        rs.deleteRecipe(recipe.getRecipe_id());
        Mockito.verify(rp).deleteById(recipe.getRecipe_id());
    }

    @Test
    public void testGetRecipeById() {
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

        when(rp.findById(1)).thenReturn(Optional.of(recipe));

        RecipeDTO r = rs.getRecipeById(1);

        assertEquals(r.getId(), recipe.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe.getMedia().size());
        assertEquals(r.getUser().getId(), recipe.getUser().getId());
    }

    @Test
    public void testGetAll() {
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

        when(rp.findAll()).thenReturn(recipes);

        RecipeDTO r = rs.getAll().get(0);

        assertEquals(r.getId(), recipe.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe.getMedia().size());
        assertEquals(r.getUser().getId(), recipe.getUser().getId());
    }

    @Test
    public void testUpdateRecipe() {
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

        when(rp.save(Mockito.any(Recipe.class))).thenReturn(recipe);

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

        r = rs.updateRecipe(r);

        assertEquals(r.getId(), recipe.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe.getMedia().size());
        assertEquals(r.getUser().getId(), recipe.getUser().getId());
    }

    @Test
    public void testSearch() {
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

        Recipe recipe2 = new Recipe();
        recipe2.setRecipe_id(2);
        recipe2.setName("udon");
        recipe2.setDescription("yummy");
        recipe2.setCook_time("40 min");
        recipe2.setServings(4);
        recipe2.setApproved(true);
        recipe2.setInstruction(new ArrayList<>());
        recipe2.setMedia(new ArrayList<>());
        recipe2.setIngredient(new ArrayList<>());
        recipe2.setUser(user);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        recipes.add(recipe2);

        user.setRecipes(recipes);

        when(rp.findAll()).thenReturn(recipes);

        List<RecipeDTO> reps = rs.search("udon");

        RecipeDTO r = reps.get(0);

        assertEquals(r.getId(), recipe2.getRecipe_id());
        assertEquals(r.getIngredients().size(), recipe2.getIngredient().size());
        assertEquals(r.getInstructions().size(), recipe2.getInstruction().size());
        assertEquals(r.getMedia().size(), recipe2.getMedia().size());
        assertEquals(r.getUser().getId(), recipe2.getUser().getId());
    }
}
