package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.game.Game;
import com.lavien.hodgepodge.models.merchants.Merchant;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "alchemists")
public class Alchemist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  private Game game;

  @ManyToMany(mappedBy = "alchemists")
  private List<Mixture> mixtures;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "played_merchant_alchemist",
      joinColumns = @JoinColumn(name = "alchemist_id"),
      inverseJoinColumns = @JoinColumn(name = "merchant_id"))
  private List<Merchant> playedMerchants;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "hand_merchant_alchemist",
      joinColumns = @JoinColumn(name = "alchemist_id"),
      inverseJoinColumns = @JoinColumn(name = "merchant_id"))
  private List<Merchant> merchantsInHand;

  private int ingrRoot;
  private int ingrMushroom;
  private int ingrFeather;
  private int ingrChickenLeg;

  private int goldCoins;
  private int silverCoins;

  public Alchemist(Game game) {
    this.game = game;
    this.mixtures = new ArrayList<>();
    this.playedMerchants = new ArrayList<>();
    this.merchantsInHand = new ArrayList<>();
    this.ingrRoot = 0;
    this.ingrMushroom = 0;
    this.ingrFeather = 0;
    this.ingrChickenLeg = 0;
    this.goldCoins = 0;
    this.silverCoins = 0;
  }

}
