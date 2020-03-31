package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.merchants.Merchant;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;

@Entity
public class Alchemist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  private Game game;

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

  private int ingrRoot;
  private int ingrMushroom;
  private int ingrFeather;
  private int ingrChickenLeg;

  private int goldCoins;
  private int silverCoins;

  public Alchemist() {

  }

  public Alchemist(Game game) {
    this.game = game;
    this.mixtures = new ArrayList<>();
    this.playedMerchants = new ArrayList<>();
    this.merchantsInHand = new ArrayList<>();
    this.ingrRoot = 0;
    this.ingrMushroom = 0;
    this.ingrFeather = 0;
    this.ingrChickenLeg = 0;
    this.goldCoins = 0;
    this.silverCoins = 0;
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

  public List<Merchant> getMerchantsInHand() {
    return merchantsInHand;
  }

  public void setMerchantsInHand(List<Merchant> merchantsInHand) {
    this.merchantsInHand = merchantsInHand;
  }

  public int getIngrRoot() {
    return ingrRoot;
  }

  public void setIngrRoot(int ingrRoot) {
    this.ingrRoot = ingrRoot;
  }

  public int getIngrMushroom() {
    return ingrMushroom;
  }

  public void setIngrMushroom(int ingrMushroom) {
    this.ingrMushroom = ingrMushroom;
  }

  public int getIngrFeather() {
    return ingrFeather;
  }

  public void setIngrFeather(int ingrFeather) {
    this.ingrFeather = ingrFeather;
  }

  public int getIngrChickenLeg() {
    return ingrChickenLeg;
  }

  public void setIngrChickenLeg(int ingrChickenLeg) {
    this.ingrChickenLeg = ingrChickenLeg;
  }

  public int getGoldCoins() {
    return goldCoins;
  }

  public void setGoldCoins(int goldCoins) {
    this.goldCoins = goldCoins;
  }

  public int getSilverCoins() {
    return silverCoins;
  }

  public void setSilverCoins(int silverCoins) {
    this.silverCoins = silverCoins;
  }
}
