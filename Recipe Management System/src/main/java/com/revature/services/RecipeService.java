package com.revature.services;

import com.revature.models.Recipe;
import com.revature.models.RecipeDTO;

import java.util.List;


public interface RecipeService {

    /**
     *
     * @param recipe  Takes a recipe to add/persist into database.
     * @return        The newly added/created Recipe object/data.
     */
    public RecipeDTO createRecipe(RecipeDTO recipe);

    /**
     *
     * @param id      Takes a Recipe id to delete from the database.
     *
     */
    public void deleteRecipe(int id);

    /**
     *
     * @param id      Takes a Recipe id to retrieve from the database.
     * @return        Returns the retrieved Recipe data as an object.
     */
    public RecipeDTO getRecipeById(int id);

    /**
     *
     * @return      Returns the list of all Recipes retrieved from the Database.
     */
    public List<RecipeDTO> getAll();

    /**
     *
     * @param recipe        The recipe object to be updated in the Database.
     * @return              returns the updated Recipe object.
     */
    public RecipeDTO updateRecipe(RecipeDTO recipe);

    /**
     *
     * @param nameSubstring     A string that matches a Recipe(s) in the Database.
     * @return                  The list of all matching Recipe(s).
     */
    public List<RecipeDTO> search(String nameSubstring);
}
