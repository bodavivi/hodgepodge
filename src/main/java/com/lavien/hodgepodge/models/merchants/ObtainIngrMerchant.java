package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.ingredients.Ingredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class ObtainIngrMerchant extends Merchant {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
