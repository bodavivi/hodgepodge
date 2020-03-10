package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.ingredients.Ingredient;
import java.util.HashMap;

public abstract class Merchant {

  private HashMap<Ingredient, Integer> ingredients;

  public HashMap<Ingredient, Integer> getIngredients() {
    return ingredients;
  }

  public void setIngredients(
      HashMap<Ingredient, Integer> ingredients) {
    this.ingredients = ingredients;
  }
}
