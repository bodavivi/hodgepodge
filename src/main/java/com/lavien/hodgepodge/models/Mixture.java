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

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "mixture_ingredient",
  joinColumns = @JoinColumn(name = "mixture_id"),
  inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private List<Ingredient> ingredients;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "mixture_alchemist",
      joinColumns = @JoinColumn(name = "mixture_id"),
  inverseJoinColumns = @JoinColumn(name = "alchemist_id"))
  private List<Alchemist> alchemists;

  private int point;

  public Mixture() {
  }

  public Mixture(int point, List<Ingredient> ingredients) {
    this.gamesWhereUnavailable = new ArrayList<>();
    this.gamesWhereAvailable = new ArrayList<>();
    this.ingredients = ingredients;
    this.alchemists = new ArrayList<>();
    this.point = point;
  }
}
