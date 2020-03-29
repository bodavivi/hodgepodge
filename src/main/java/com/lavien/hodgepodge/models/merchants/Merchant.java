package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.Game;

import java.util.List;
import javax.persistence.*;

@Entity(name = "Merchant")
public abstract class Merchant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "available_merchant_game", joinColumns = @JoinColumn(name = "merchant_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<Game> gamesWhereAvailable;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "unavailable_merchant_game", joinColumns = @JoinColumn(name = "merchant_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<Game> gamesWhereUnavailable;

  public Merchant() {

  }
}
