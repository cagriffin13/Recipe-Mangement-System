package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id", updatable = false)
    private int id;

    @Column(length = 50)
    private String amount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="foodstuff_id")
    private Foodstuff item;

    // This method allows us to use Ingredient
    // as if it had this field
    public String getName() {
        return item.getName();
    }

    // This method allows us to use Ingredient
    // as if it had this field
    public void setName(String name) {
        item.setName(name);
    }
}
