package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.coins.Coin;
import com.lavien.hodgepodge.models.merchants.Merchant;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
public class Alchemist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  private Game game;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "alchemist_ingredient",
      joinColumns = @JoinColumn(name = "alchemist_id"),
      inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private List<Ingredient> ingredients;

  @ManyToMany(mappedBy = "alchemists")
  private List<Mixture> mixtures;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "played_merchant_alchemist",
      joinColumns = @JoinColumn(name = "alchemist_id"),
      inverseJoinColumns = @JoinColumn(name = "merchant_id"))
  private List<Merchant> playedMerchants;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "hand_merchant_alchemist",
  joinColumns = @JoinColumn(name = "alchemist_id"),
  inverseJoinColumns = @JoinColumn(name = "merchant_id"))
  private List<Merchant> merchantsInHand;

  @Transient
  private List<Merchant> inHand;

  @Transient
  private HashMap<Coin, Integer> coins;

  public Alchemist() {

  }

  public Alchemist(Game game) {
    this.game = game;
    this.ingredients = new ArrayList<>();
    this.mixtures = new ArrayList<>();
    this.playedMerchants = new ArrayList<>();
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

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(
      List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public List<Mixture> getMixtures() {
    return mixtures;
  }

  public void setMixtures(List<Mixture> mixtures) {
    this.mixtures = mixtures;
  }

  public List<Merchant> getPlayedMerchants() {
    return playedMerchants;
  }

  public void setPlayedMerchants(List<Merchant> playedMerchants) {
    this.playedMerchants = playedMerchants;
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
