package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.ingredients.Ingredient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Mixture {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @Transient
  private HashMap<Ingredient, Integer> ingredients;
  private int point;

  public Mixture() {
  }

  public Mixture(int point) {
    this.gamesWhereUnavailable = new ArrayList<>();
    this.gamesWhereAvailable = new ArrayList<>();
    this.ingredients = new HashMap<>();
    this.point = point;
  }
}
