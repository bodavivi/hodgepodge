package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.ingredients.Ingredient;
import java.util.HashMap;

public class Mixture {

  private HashMap<Ingredient, Integer> ingredients;
  private int point;

  public Mixture(
      HashMap<Ingredient, Integer> ingredients, int point) {
    this.ingredients = ingredients;
    this.point = point;
  }

  public HashMap<Ingredient, Integer> getIngredients() {
    return ingredients;
  }

  public void setIngredients(
      HashMap<Ingredient, Integer> ingredients) {
    this.ingredients = ingredients;
  }

  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }
}
