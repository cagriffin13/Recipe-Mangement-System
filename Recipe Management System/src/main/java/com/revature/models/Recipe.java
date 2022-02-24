package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int recipe_id;


    @Column(length = 50)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 15)
    private String cook_time;

    private int servings, likes, dislikes;
    private boolean approved;
    private boolean disapproved;


    /**
     * Retrieves User data along with the Recipe data
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    /**
     * Retrieves all instructions associated with the Recipe
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private List<Instruction> instruction;


    /**
     * Retrieves all media associated with the Recipe
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private List<Media> media;


    /**
     * Retrieves Ingredients list required for the Recipe
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private List<Ingredient> ingredient;
}
