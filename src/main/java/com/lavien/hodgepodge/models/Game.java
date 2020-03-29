package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.merchants.Merchant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String gameCode;

  @ManyToMany(mappedBy = "gamesWhereUnavailable")
  private List<Merchant> unavailableMerchants;

  @ManyToMany(mappedBy = "gamesWhereAvailable")
  private List<Merchant> availableMerchants;

  @Transient
  private ArrayList<Alchemist> alchemists;
  @Transient
  private ArrayList<Mixture> availableMixtures;
  @Transient
  private ArrayList<Mixture> unavailableMixtures;

  public Game() {
    this.unavailableMerchants = new ArrayList<>();
    this.availableMerchants = new ArrayList<>();
    this.alchemists = new ArrayList<>();
    this.availableMixtures = new ArrayList<>();
    this.unavailableMixtures = new ArrayList<>();
  }

  public Game(String gameCode) {
    this.gameCode = gameCode;
    this.unavailableMerchants = new ArrayList<>();
    this.availableMerchants = new ArrayList<>();
    this.alchemists = new ArrayList<>();
    this.availableMixtures = new ArrayList<>();
    this.unavailableMixtures = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGameCode() {
    return gameCode;
  }

  public void setGameCode(String gameCode) {
    this.gameCode = gameCode;
  }

  public List<Merchant> getUnavailableMerchants() {
    return unavailableMerchants;
  }

  public void setUnavailableMerchants(List<Merchant> unavailableMerchants) {
    this.unavailableMerchants = unavailableMerchants;
  }

  public List<Merchant> getAvailableMerchants() {
    return availableMerchants;
  }

  public void setAvailableMerchants(List<Merchant> availableMerchants) {
    this.availableMerchants = availableMerchants;
  }

  public ArrayList<Alchemist> getAlchemists() {
    return alchemists;
  }

  public void setAlchemists(ArrayList<Alchemist> alchemists) {
    this.alchemists = alchemists;
  }

  public ArrayList<Mixture> getAvailableMixtures() {
    return availableMixtures;
  }

  public void setAvailableMixtures(ArrayList<Mixture> availableMixtures) {
    this.availableMixtures = availableMixtures;
  }

  public ArrayList<Mixture> getUnavailableMixtures() {
    return unavailableMixtures;
  }

  public void setUnavailableMixtures(ArrayList<Mixture> unavailableMixtures) {
    this.unavailableMixtures = unavailableMixtures;
  }
}
