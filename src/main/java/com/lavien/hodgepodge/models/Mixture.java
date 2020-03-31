package com.lavien.hodgepodge.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mixture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "unavailable_mixture_game",
      joinColumns = @JoinColumn(name = "mixture_id"),
      inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<Game> gamesWhereUnavailable;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "available_mixture_game",
      joinColumns = @JoinColumn(name = "mixture_id"),
      inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<Game> gamesWhereAvailable;

  private int ingrRoot;
  private int ingrMushroom;
  private int ingrFeather;
  private int ingrChickenLeg;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "mixture_alchemist",
      joinColumns = @JoinColumn(name = "mixture_id"),
      inverseJoinColumns = @JoinColumn(name = "alchemist_id"))
  private List<Alchemist> alchemists;

  private int point;

  private int goldCoin;
  private int silverCoin;

  public Mixture() {
    this.gamesWhereUnavailable = new ArrayList<>();
    this.gamesWhereAvailable = new ArrayList<>();
    this.alchemists = new ArrayList<>();
    this.goldCoin = 0;
    this.silverCoin = 0;
  }

  public Mixture(int point, int ingrRoot, int ingrMushroom, int ingrFeather, int ingrChickenLeg) {
    this.gamesWhereUnavailable = new ArrayList<>();
    this.gamesWhereAvailable = new ArrayList<>();
    this.alchemists = new ArrayList<>();
    this.point = point;
    this.ingrRoot = ingrRoot;
    this.ingrMushroom = ingrMushroom;
    this.ingrFeather = ingrFeather;
    this.ingrChickenLeg = ingrChickenLeg;
    this.goldCoin = 0;
    this.silverCoin = 0;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Game> getGamesWhereUnavailable() {
    return gamesWhereUnavailable;
  }

  public void setGamesWhereUnavailable(List<Game> gamesWhereUnavailable) {
    this.gamesWhereUnavailable = gamesWhereUnavailable;
  }

  public List<Game> getGamesWhereAvailable() {
    return gamesWhereAvailable;
  }

  public void setGamesWhereAvailable(List<Game> gamesWhereAvailable) {
    this.gamesWhereAvailable = gamesWhereAvailable;
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

  public List<Alchemist> getAlchemists() {
    return alchemists;
  }

  public void setAlchemists(List<Alchemist> alchemists) {
    this.alchemists = alchemists;
  }

  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }

  public int getGoldCoin() {
    return goldCoin;
  }

  public void setGoldCoin(int goldCoin) {
    this.goldCoin = goldCoin;
  }

  public int getSilverCoin() {
    return silverCoin;
  }

  public void setSilverCoin(int silverCoin) {
    this.silverCoin = silverCoin;
  }
}
