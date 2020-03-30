package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;
import com.lavien.hodgepodge.models.Ingredient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public abstract class Merchant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "available_merchant_game",
      joinColumns = @JoinColumn(name = "merchant_id"),
      inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<Game> gamesWhereAvailable;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "unavailable_merchant_game",
      joinColumns = @JoinColumn(name = "merchant_id"),
      inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<Game> gamesWhereUnavailable;

  @ManyToMany(mappedBy = "playedMerchants")
  private List<Alchemist> alchemistsWhoPlayed;

  @ManyToMany(mappedBy = "merchantsInHand")
  private List<Alchemist> alchemistsInHand;

  @Transient
  @ManyToMany
  private HashMap<Ingredient, Integer> ingredients;

  public Merchant() {
    this.gamesWhereAvailable = new ArrayList<>();
    this.gamesWhereUnavailable = new ArrayList<>();
    this.ingredients = new HashMap<>();
    this.alchemistsWhoPlayed = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Game> getGamesWhereAvailable() {
    return gamesWhereAvailable;
  }

  public void setGamesWhereAvailable(List<Game> gamesWhereAvailable) {
    this.gamesWhereAvailable = gamesWhereAvailable;
  }

  public List<Game> getGamesWhereUnavailable() {
    return gamesWhereUnavailable;
  }

  public void setGamesWhereUnavailable(List<Game> gamesWhereUnavailable) {
    this.gamesWhereUnavailable = gamesWhereUnavailable;
  }

  public HashMap<Ingredient, Integer> getIngredients() {
    return ingredients;
  }

  public void setIngredients(HashMap<Ingredient, Integer> ingredients) {
    this.ingredients = ingredients;
  }

  public List<Alchemist> getAlchemistsWhoPlayed() {
    return alchemistsWhoPlayed;
  }

  public void setAlchemistsWhoPlayed(List<Alchemist> alchemistsWhoPlayed) {
    this.alchemistsWhoPlayed = alchemistsWhoPlayed;
  }
}
