package com.revature.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Recipe;
import com.revature.models.RecipeDTO;
import com.revature.models.User;
import com.revature.services.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.driver.Application.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    public void testSearchByName() throws Exception {
        RecipeDTO rice = new RecipeDTO();
        rice.setName("Rice pilaf");
        List<RecipeDTO> riceRecipes = new ArrayList<>();
        riceRecipes.add(rice);
        when(recipeService.search("rice")).thenReturn(riceRecipes);

        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/recipes/search?name=rice"));

        ra.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$[0].name").value("Rice pilaf")
        );
    }

    @Test
    void addRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setRecipe_id(1);
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(1);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(recipeDTO);

        when(recipeService.createRecipe(recipeDTO)).thenReturn(recipeDTO);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/recipes")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        );

    }

    @Test
    void updateRecipe() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setRecipe_id(1);
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(1);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(recipeDTO);

        when(recipeService.updateRecipe(recipeDTO)).thenReturn(recipeDTO);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.put("/recipes")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        ra.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        );
    }

    @Test
    void deleteRecipe() throws Exception {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setId(1);

        recipeService.deleteRecipe(recipe.getId());
        Mockito.verify(recipeService).deleteRecipe(recipe.getId());

        ResultActions rs = mvc.perform(MockMvcRequestBuilders.delete("/recipes/1"));

        rs.andExpect(
                MockMvcResultMatchers.status().isOk()
        );


    }


    @Test
    void getAllRecipes() throws Exception {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setName("Rice bun");
        List<RecipeDTO> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        when(recipeService.getAll()).thenReturn(recipeList);

        ResultActions rs = mvc.perform(MockMvcRequestBuilders.get("/recipes"));
        rs.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getRecipeById() throws Exception {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setId(1);

        when(recipeService.getRecipeById(1)).thenReturn(new RecipeDTO());

        ResultActions rs = mvc.perform(MockMvcRequestBuilders.get("/recipes/1"));
        rs.andExpect(MockMvcResultMatchers.status().isOk());

    }
}
