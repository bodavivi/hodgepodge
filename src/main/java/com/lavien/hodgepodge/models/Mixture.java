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

  private int ingRoot;
  private int ingMushroom;
  private int ingFeather;
  private int ingChickenLeg;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "mixture_alchemist",
      joinColumns = @JoinColumn(name = "mixture_id"),
      inverseJoinColumns = @JoinColumn(name = "alchemist_id"))
  private List<Alchemist> alchemists;

  private int point;

  public Mixture() {
  }

  public Mixture(int point, int ingRoot, int ingMushroom, int ingFeather, int ingChickenLeg) {
    this.gamesWhereUnavailable = new ArrayList<>();
    this.gamesWhereAvailable = new ArrayList<>();
    this.alchemists = new ArrayList<>();
    this.point = point;
    this.ingRoot = ingRoot;
    this.ingMushroom = ingMushroom;
    this.ingFeather = ingFeather;
    this.ingChickenLeg = ingChickenLeg;
  }
}
