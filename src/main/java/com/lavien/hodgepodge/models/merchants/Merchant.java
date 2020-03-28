package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.ingredients.Ingredient;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public abstract class Merchant {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Transient
  @ManyToMany
  private HashMap<Ingredient, Integer> ingredients;

  public HashMap<Ingredient, Integer> getIngredients() {
    return ingredients;
  }

  public void setIngredients(HashMap<Ingredient, Integer> ingredients) {
    this.ingredients = ingredients;
  }
}
