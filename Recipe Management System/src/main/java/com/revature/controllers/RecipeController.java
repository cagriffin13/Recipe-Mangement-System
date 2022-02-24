package com.revature.controllers;

import com.revature.models.Recipe;
import com.revature.models.RecipeDTO;
import com.revature.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    /**
     * This method serves the search endpoint. It requires a query parameter and uses
     * those parameters to search. Currently only supports the name substring.
     *
     * @param params            parameter map of the query parameters of the request
     * @return                  returns a response body list of recipes
     */
    @GetMapping("/recipes/search")
    public List<RecipeDTO> searchRecipe(@RequestParam Map<String, String> params) {
        String searchSubstring = params.get("name");
        return recipeService.search(searchSubstring);
    }



    /**
     * Method generates all approved recipes in the database
     *
     * @return      The list of all approved recipes in the database
     */
    @GetMapping("/recipes")
    public List<RecipeDTO> allRecipes() {
        return recipeService.getAll();
    }



    /**
     * Method to create and add Recipes into the database
     * @param recipe   Accepts a recipe to add in the Database
     * @return        The added Recipe with a generated ID.
     */
    @PostMapping(value = "/recipes", consumes = "application/json", produces = "application/json")
    public RecipeDTO addRecipe(@RequestBody RecipeDTO recipe) {
        return recipeService.createRecipe(recipe);
    }


    /**
     * Method to update an existing recipe
     * @param recipe  Takes the updated recipe to persist update in database
     * @return        Returns a DTO of the updated recipe.
     */
    @PutMapping(value = "/recipes", consumes = "application/json", produces = "application/json")
    public RecipeDTO updateRecipe(@RequestBody RecipeDTO recipe) {
        return  recipeService.updateRecipe(recipe);
    }

    /**
     *  Method deletes a Recipe with the parsed ID
     * @param id  Recipe ID to delete from the DataBase
     */
    @DeleteMapping(value = "/recipes/{id}")
    public void deleteRecipe(@PathVariable String id){
        recipeService.deleteRecipe(Integer.parseInt(id));
    }

    /**
     * Method retrieves a recipe from the database/backend by an id.
     *
     * @param id    The id of Recipe to be retrieved
     * @return      Returns the retrieved Recipe data from the database.
     */
    @GetMapping("/recipes/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable String id) {
        RecipeDTO recipe = recipeService.getRecipeById(Integer.parseInt(id));
        return recipe != null ? new ResponseEntity<>(recipe, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
