package com.revature.repositories;

import com.revature.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * RecipeRepository extends CrudRepository to perform CRUD Operations on the Database
 * CRUD Methods
 * save() -> will create new data and also used to update existing data in the database
 * findAll() -> retrieves the list of all data in the database
 * findById() -> retrieves data by parsed ID from the database
 * deleteById() -> deletes data in the database with the parsed ID
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String username, String password);
}
