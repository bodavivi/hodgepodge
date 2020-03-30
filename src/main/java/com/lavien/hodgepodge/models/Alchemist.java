package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.coins.Coin;
import com.lavien.hodgepodge.models.merchants.Merchant;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;

@Entity
public class Alchemist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  private Game game;

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

  public Alchemist() {

  }

  public Alchemist(Game game) {
    this.game = game;
    this.ingredients = new HashMap<>();
    this.mixtures = new ArrayList<>();
    this.played = new ArrayList<>();
    this.inHand = new ArrayList<>();
    this.coins = new HashMap<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
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
