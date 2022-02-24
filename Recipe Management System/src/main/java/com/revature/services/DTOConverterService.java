package com.revature.services;

import com.revature.models.Recipe;
import com.revature.models.RecipeDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class DTOConverterService {

    public Recipe convertDTO(RecipeDTO recipe) {
        Recipe r = new Recipe();
        r.setRecipe_id(recipe.getId());
        r.setName(recipe.getName());
        r.setDescription(recipe.getDescription());
        r.setCook_time(recipe.getCookTime());
        r.setServings(recipe.getServings());
        r.setLikes(recipe.getLikes());
        r.setDislikes(recipe.getDislikes());
        r.setApproved(recipe.isApproved());
        r.setDisapproved(recipe.isDisapproved());

        User u = new User();
        u.setId(recipe.getUser().getId());
        u.setBanned(recipe.getUser().isBanned());
        u.setAdmin(recipe.getUser().isAdmin());
        u.setFirstName(recipe.getUser().getFirstName());
        u.setLastName(recipe.getUser().getLastName());
        u.setUsername(recipe.getUser().getUsername());

        r.setInstruction(recipe.getInstructions());
        r.setMedia(recipe.getMedia());
        r.setIngredient(recipe.getIngredients());
        r.setUser(u);

        return r;
    }

    // If the user enters an invalid date we set it to 0 (the front end should validate the format)
    // Since User doesn't cascade we can use an empty list for recipes. This method is only used for
    // creating new users and updating their fields.
    public User convertDTO(UserDTO user) {
        User u = new User();
        u.setId(user.getId());
        u.setBanned(user.isBanned());
        u.setAdmin(user.isAdmin());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());

        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfBirth, registrationDate;
        try {
            dateOfBirth = format.parse(user.getDateOfBirth());
        } catch (ParseException e) {
            dateOfBirth = new Date(0);
        }
        u.setDateOfBirth(dateOfBirth.getTime());

        try {
            registrationDate = format.parse(user.getRegistrationDate());
        } catch (ParseException e) {
            registrationDate = new Date(0);
        }
        u.setRegistrationDate(registrationDate.getTime());

        u.setEmail(user.getEmail());
        u.setPhone(user.getPhone());
        u.setRecipes(new ArrayList<>());

        return u;
    }

    /**
     * This method is used to build a data transfer object for users. We use this method
     * to resolve the User -> Recipe relationships.
     *
     * @param user                      the user to reconstruct
     * @return                          a data transfer object with the user data
     */
    public UserDTO buildUserDTO(User user) {
        return buildUserDTO(user, true);
    }

    /**
     * This method is used to build a data transfer object for recipes. We use this method
     * to resolve the Recipe -> User relationships.
     *
     * @param recipe                    the recipe to reconstruct
     * @return                          the data transfer object with the recipe data
     */
    public RecipeDTO buildRecipeDTO(Recipe recipe) {
        return buildRecipeDTO(recipe, null);
    }

    /**
     * This is a helper method that does the actual work. Has a flag to decide
     * whether to completely resolve the lazy fetch relationship.
     *
     * @param user                      the user to reconstruct
     * @param RESOLVE_FLAG              whether to resolve the relationship
     * @return                          the data transfer object with the user data
     */
    private UserDTO buildUserDTO(User user, boolean RESOLVE_FLAG) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setAdmin(user.isAdmin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setDateOfBirth(dateFormat.format(new Date(user.getDateOfBirth())));
        userDTO.setRegistrationDate(dateFormat.format(new Date(user.getRegistrationDate())));
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());

        List<RecipeDTO> recipeDTOs = new ArrayList<>();

        if(RESOLVE_FLAG) {
            List<Recipe> recipes = user.getRecipes();
            if(recipes == null) {
                userDTO.setRecipes(new ArrayList<>());
                return userDTO;
            }

            for (Recipe recipe: recipes) {
                recipeDTOs.add(buildRecipeDTO(recipe, userDTO));
            }
            userDTO.setRecipes(recipeDTOs);

        } else {
            userDTO.setRecipes(recipeDTOs);
        }

        return userDTO;
    }

    /**
     * This is a helper method that does the actual work. Has a flag to decide
     * whether to completely resolve the lazy fetch relationship.
     *
     * @param recipe                    the recipe to reconstruct
     * @param userDTO                   the author user, if null we resolve
     * @return                          the data transfer object that holds the recipe data
     */
    private RecipeDTO buildRecipeDTO(Recipe recipe, UserDTO userDTO) {
        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDTO.setId(recipe.getRecipe_id());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setCookTime(recipe.getCook_time());
        recipeDTO.setServings(recipe.getServings());
        recipeDTO.setApproved(recipe.isApproved());
        recipeDTO.setInstructions(recipe.getInstruction());
        recipeDTO.setIngredients(recipe.getIngredient());
        recipeDTO.setMedia(recipe.getMedia());

        if(userDTO == null) {
            recipeDTO.setUser(buildUserDTO(recipe.getUser(), false));
        } else {
            UserDTO u = new UserDTO();
            u.setId(userDTO.getId());
            recipeDTO.setUser(u);
        }

        return recipeDTO;
    }
}
