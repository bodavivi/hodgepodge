package com.lavien.hodgepodge.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mixtures")
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

}
