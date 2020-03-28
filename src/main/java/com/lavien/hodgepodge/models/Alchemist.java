package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.coins.Coin;
import com.lavien.hodgepodge.models.ingredients.Ingredient;
import com.lavien.hodgepodge.models.merchants.Merchant;

import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;

public class Alchemist {

  @Transient
  @ManyToMany
  private HashMap<Ingredient, Integer> ingredients;
  @Transient
  private List<Mixture> mixtures;
  @Transient
  private List<Merchant> played;
  @Transient
  private List<Merchant> inHand;
  @Transient
  private HashMap<Coin, Integer> coins;

  public Alchemist(
      HashMap<Ingredient, Integer> ingredients,
      List<Mixture> mixtures, List<Merchant> played,
      List<Merchant> inHand,
      HashMap<Coin, Integer> coins) {
    this.ingredients = ingredients;
    this.mixtures = mixtures;
    this.played = played;
    this.inHand = inHand;
    this.coins = coins;
  }

  public HashMap<Ingredient, Integer> getIngredients() {
    return ingredients;
  }

  public void setIngredients(
      HashMap<Ingredient, Integer> ingredients) {
    this.ingredients = ingredients;
  }

  public List<Mixture> getMixtures() {
    return mixtures;
  }

  public void setMixtures(List<Mixture> mixtures) {
    this.mixtures = mixtures;
  }

  public List<Merchant> getPlayed() {
    return played;
  }

  public void setPlayed(List<Merchant> played) {
    this.played = played;
  }

  public List<Merchant> getInHand() {
    return inHand;
  }

  public void setInHand(List<Merchant> inHand) {
    this.inHand = inHand;
  }

  public HashMap<Coin, Integer> getCoins() {
    return coins;
  }

  public void setCoins(HashMap<Coin, Integer> coins) {
    this.coins = coins;
  }
}
