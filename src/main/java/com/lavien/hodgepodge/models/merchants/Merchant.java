package com.lavien.hodgepodge.models.merchants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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

  @JsonIgnore
  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "available_merchant_game",
      joinColumns = @JoinColumn(name = "merchant_id"),
      inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<Game> gamesWhereAvailable;

  @JsonIgnore
  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "unavailable_merchant_game",
      joinColumns = @JoinColumn(name = "merchant_id"),
      inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<Game> gamesWhereUnavailable;

  @JsonIgnore
  @ManyToMany(mappedBy = "playedMerchants")
  private List<Alchemist> alchemistsWhoPlayed;

  @JsonIgnore
  @ManyToMany(mappedBy = "merchantsInHand")
  private List<Alchemist> alchemistsInHand;

  public Merchant() {
    this.gamesWhereAvailable = new ArrayList<>();
    this.gamesWhereUnavailable = new ArrayList<>();
    this.alchemistsWhoPlayed = new ArrayList<>();
    this.alchemistsInHand = new ArrayList<>();
  }

}
