package com.revature.integrations;

import com.revature.models.*;
import com.revature.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.revature.driver.Application.class)
@Transactional
public class RecipeIntegrationTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void testFindRecipeById() {
        Optional<Recipe> recipe = recipeRepository.findById(1);

        assertTrue(recipe.isPresent());

        Recipe r = recipe.get();

        assertEquals(r.getRecipe_id(), 1);
        assertEquals(r.getInstruction().size(), 1);
        assertEquals(r.getMedia().size(), 0);
        assertEquals(r.getIngredient().size(), 2);
    }

    @Test
    public void testFindAllRecipes() {
        List<Recipe> recipes = (List<Recipe>) recipeRepository.findAll();

        assertEquals(recipes.size(), 2);
    }

    @Test
    public void testCreateRecipe() {
        User u = new User();
        u.setId(1);
        Recipe r = new Recipe();
        r.setName("new recipe");
        r.setCook_time("3 hrs");
        r.setDescription("my new recipe is tasty");
        r.setUser(u);
        r.setServings(4);
        r.setInstruction(new ArrayList<>());
        r.setMedia(new ArrayList<>());
        r.setIngredient(new ArrayList<>());

        Recipe newRecipe = recipeRepository.save(r);

        assertNotEquals(newRecipe.getRecipe_id(), 0);
        assertEquals(newRecipe.getName(), r.getName());
    }

    @Test
    public void testCreateRecipeWithInstructions() {
        User u = new User();
        u.setId(1);
        Recipe r = new Recipe();
        r.setName("new recipe");
        r.setCook_time("3 hrs");
        r.setDescription("my new recipe is tasty");
        r.setUser(u);
        r.setServings(4);
        r.setInstruction(new ArrayList<>());
        r.setMedia(new ArrayList<>());
        r.setIngredient(new ArrayList<>());

        r.getInstruction().add(new Instruction(0, 1, "do all the things"));

        Recipe newRecipe = recipeRepository.save(r);

        assertNotEquals(newRecipe.getRecipe_id(), 0);
        assertEquals(newRecipe.getInstruction().size(), 1);
        assertNotEquals(newRecipe.getInstruction().get(0).getId(), 0);
    }

    @Test
    public void testCreateRecipeWithIngredients() {
        User u = new User();
        u.setId(1);
        Recipe r = new Recipe();
        r.setName("new recipe");
        r.setCook_time("3 hrs");
        r.setDescription("my new recipe is tasty");
        r.setUser(u);
        r.setServings(4);
        r.setInstruction(new ArrayList<>());
        r.setMedia(new ArrayList<>());
        r.setIngredient(new ArrayList<>());

        Ingredient ingredient = new Ingredient(0, "2 lbs", new Foodstuff(0, "beef"));
        r.getIngredient().add(ingredient);

        Recipe newRecipe = recipeRepository.save(r);

        assertNotEquals(newRecipe.getRecipe_id(), 0);
        assertNotEquals(newRecipe.getIngredient().get(0).getId(), 0);
    }

    @Test
    public void testCreateRecipeWithMedia() throws IOException {
        User u = new User();
        u.setId(1);
        Recipe r = new Recipe();
        r.setName("new recipe");
        r.setCook_time("3 hrs");
        r.setDescription("my new recipe is tasty");
        r.setUser(u);
        r.setServings(4);
        r.setInstruction(new ArrayList<>());
        r.setMedia(new ArrayList<>());
        r.setIngredient(new ArrayList<>());

        File f = (new ClassPathResource("test.jpg")).getFile();
        Media media = new Media();
        media.setTitle("ribs");
        media.setType("jpg");
        media.setData(Files.readAllBytes(f.toPath()));

        r.getMedia().add(media);

        Recipe newRecipe = recipeRepository.save(r);

        assertNotEquals(newRecipe.getRecipe_id(), 0);
        assertEquals(newRecipe.getMedia().size(), 1);
        assertNotEquals(newRecipe.getMedia().get(0).getId(), 0);
    }

    @Test
    public void testDeleteById() {
        recipeRepository.deleteById(1);
        assertFalse(recipeRepository.findById(1).isPresent());
    }

    @Test
    public void testRemoveInstruction() {
        User u = new User();
        u.setId(1);
        Recipe r = new Recipe();
        r.setName("new recipe");
        r.setCook_time("3 hrs");
        r.setDescription("my new recipe is tasty");
        r.setUser(u);
        r.setServings(4);
        r.setInstruction(new ArrayList<>());
        r.setMedia(new ArrayList<>());
        r.setIngredient(new ArrayList<>());

        r.getInstruction().add(new Instruction(0, 1, "first instruction"));
        r.getInstruction().add(new Instruction(0, 2, "second instruction"));

        Recipe newRecipe = recipeRepository.save(r);

        assertNotEquals(newRecipe.getRecipe_id(), 0);
        assertEquals(newRecipe.getInstruction().size(), 2);
        assertNotEquals(newRecipe.getInstruction().get(0).getId(), 0);
        assertNotEquals(newRecipe.getInstruction().get(1).getId(), 0);

        Instruction removedInstruction = newRecipe.getInstruction().remove(1);

        recipeRepository.save(newRecipe);

        Optional<Recipe> postRemoval = recipeRepository.findById(newRecipe.getRecipe_id());

        assertTrue(postRemoval.isPresent());
        Recipe postRemovalRecipe = postRemoval.get();
        assertEquals(postRemovalRecipe.getRecipe_id(), newRecipe.getRecipe_id());
        assertEquals(newRecipe.getInstruction().size(), 1);
        assertNotEquals(newRecipe.getInstruction().get(0).getId(), removedInstruction.getId());
    }
}
