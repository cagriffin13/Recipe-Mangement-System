# Recipe Management System - Backend

# [Project Description]
Toastr is a web application for managing user created recipes. Users are able to manage their accounts and recipes through a responsive UI. They have an account page where they manage their account infomration and a recipe page where they manage their recipes. They can also access other users' recipes for viewing, commenting, liking and disliking; through a search page which allows them to search for recipes by name.

Admin users have the ability to ban users and approve recipes, and recipes are not viewable until they have been approved. Recipes contain a variety of information, including media, a set of instructions, and a set of ingredients. Toastr also makes use of the spoonacular api to display extra information about each ingredient.


## Technologies Used
* Java 8
* Spring Framework
* Apache Maven
* Mockito
* PostgreSQL
* AWS (RDS and EC2)
* Postman
* H2 Database
* Jenkins
* Git SCM (on GitHub)

## Features

### MVP User Stories

* As a user I can create an account
* As a user I can login and logout
* As a user I can add and modify my recipes
* As a user I can see all the recipes I've made
* As an admin I can approve recipes
* As an admin I can (un)ban users
* As a user I want my recipes to display ingredient information
* As a user I can search for other users' recipes
* As a user I can upload media related to my recipe
* As a user I can sort search results

### Planned/Stretched Goals

* As a user I want to be able to filter my searches
* As a user I want to be able to download the recipe
* As a user I want to be able to rate other people's recipes
* As a user I want to be able to comment on other people's recipes
* As a user I want to be able to see the average rating of a recipe
* As a user I want to be able to share my recipe on social media

## Getting Started
> To run the back-end system, first clone the repository. 

> Open the application in your prefered IDE

> Copy the all the files in the test/resources(application.propeties, data.sql, and test.jpg) directory 
> and paste them in the java/resources directory
>  
> Open and run the Application.java file in the driver package to start the spring application

Once that is done, go to: http://localhost:8080/  and access the endpoints below.  


### Endpoints
 * ~/login/
 * ~/register/
 * ~/recipes/
 * ~/recipes/{id}
 * ~/search/{recipe_substring}
 * ~/users/
 * ~/users/{id}

The following describes data requests between front and back:
#### /login/
Two scenarios:
 * User does not authenticate:
   * Status code: 401 + message: "No account found."
   * Status code: 401 + message: "User has been banned."
 * User does authenticate:
   * User is admin:
     * Account information
     * All Recipes
     * All Users
   * User is not admin:
     * Account information
     * Recipes' title, description, thumbnail, and id (React manages this)

#### /register/
 * On success same as /login/
 * On failure, relevant status code + message
   * For example username is already taken, we can return "Username already exists".

#### /recipes/
This endpoint takes GET and POST requests.
 * POST: used to create new recipes, takes json for a post
 * GET used to get all recipes (only sent by admin accounts)

#### /recipes/{id}
Takes PUT, DELETE, and GET.
 * GET is used by recipe page to view recipe details
 * PUT is used to update a recipe that is being viewed by its creator (or admin approving it)
 * DELETE is used to delete the recipe

#### /recipes/search
Only serves GET requests with query parameters
 * Returns json array of recipes that have been filtered to have a named that contains recipe_substring

#### /users/
Only serves GET
 * POST used to edit users (admins only)
 * GET used to get all users (only sent by admin accounts)

#### /users/{id}
Takes PUT and GET requests.
 * PUT is used to update account information
 * GET is used to get account information (possibly only admins use this)


---






## Demo
This is the back-end system for the front-end web application
https://github.com/RichardSimpson235/simpson-gomez-griffin-sadat-recipe-management-p3-front/tree/main


## Contributors

* Abdul Samad Sadat:   https://github.com/Milanoboy20
* Alejandro Gomez:     https://github.com/alxgoz
* Clyde Griffin:       https://github.com/cagriffin13
* Richard Simpson:     https://github.com/RichardSimpson235

