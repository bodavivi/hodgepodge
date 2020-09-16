package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "merchant")
@Table(name = "merchants")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Source: https://bit.ly/38WoJyi
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

  public Merchant() {
    this.gamesWhereAvailable = new ArrayList<>();
    this.gamesWhereUnavailable = new ArrayList<>();
    this.alchemistsWhoPlayed = new ArrayList<>();
    this.alchemistsInHand = new ArrayList<>();
  }

}
