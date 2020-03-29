package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.merchants.Merchant;

import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity(name = "Game")
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NaturalId
  private String gameCode;

  @ManyToMany(mappedBy = "gamesWhereAvailable")
  private List<Merchant> availableMerchants;

  @ManyToMany(mappedBy = "gamesWhereUnavailable")
  private List<Merchant> unavailableMerchants;

  public Game() {
    this.availableMerchants = availableMerchants;
    this.unavailableMerchants = unavailableMerchants;
  }

  public Game(String gameCode) {
    this.gameCode = gameCode;
    this.availableMerchants = availableMerchants;
    this.unavailableMerchants = unavailableMerchants;
  }
}
