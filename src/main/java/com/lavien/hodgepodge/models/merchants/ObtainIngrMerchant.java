package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.Ingredient;

import java.util.List;

public class ObtainIngrMerchant extends Merchant {

  private List<Ingredient> ingredientsToGet;

  public ObtainIngrMerchant(List<Ingredient> ingredientsToGet) {
    this.ingredientsToGet = ingredientsToGet;
  }

  public List<Ingredient> getIngredientsToGet() {
    return ingredientsToGet;
  }

  public void setIngredientsToGet(List<Ingredient> ingredientsToGet) {
    this.ingredientsToGet = ingredientsToGet;
  }

}
