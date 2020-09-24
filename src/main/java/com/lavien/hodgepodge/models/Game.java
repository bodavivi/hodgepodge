package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.merchants.Merchant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String gameCode;

  @ManyToMany(mappedBy = "gamesWhereUnavailable")
  private List<Merchant> unavailableMerchants;

  @ManyToMany(mappedBy = "gamesWhereAvailable")
  private List<Merchant> availableMerchants;

  @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
  private List<Alchemist> alchemists;

  @ManyToMany(mappedBy = "gamesWhereUnavailable")
  private List<Mixture> availableMixtures;

  @ManyToMany(mappedBy = "gamesWhereAvailable")
  private List<Mixture> unavailableMixtures;

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

  public void addAlchemist(Alchemist alchemist) {
    alchemists.add(alchemist);
    alchemist.setGame(this);
  }

  public void removeAlchemist(Alchemist alchemist) {
    alchemists.remove(alchemist);
    alchemist.setGame(null);
  }

}
